package co.edu.usa.talentotech.sga.service;

import co.edu.usa.sga.models.*;
import co.edu.usa.sga.utilities.constans.ResponseMessages;

import org.springframework.stereotype.Service;

import co.edu.usa.talentotech.sga.model.User;
import co.edu.usa.talentotech.sga.repository.RolesRepository;
import co.edu.usa.talentotech.sga.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class UserService implements EncriptService{
	
    @Autowired
    private UserRepository repository;
    
    @Autowired 
    private RolesRepository repositoryRoles;
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * Create a new user
     * @param token
     * @param user
     * @return Response
     * @throws ResponseDetails
     */
    public Response save(User user) throws ResponseDetails{
        try {
            if(user.getId()!=null){
              //validate that the id is not present in the body of the request
              throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
            }else{
            	//validate if email, user name, clientId, ClientSecret already exists
            	validateEmail(user.getEmail());
            	validateNameUser(user.getNameUser());
            	validateClientId(user.getClientId());
            	validateClientSecret(user.getClientSecret());
            	//Validate if the role is valid
            	validateRol(user.getRolUser());
            	//encrypt password
            	String pass = encrypPassword(user.getPassword());
                user.setPassword(pass);
                //create user
                user = repository.save(user);
                SingleResponse response = new SingleResponse();
                //create successful response
                response.setData(userResponse(user));
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.USER_CREATED);
                return response;
            }
        } catch (ResponseDetails e) {
        	if(e.getCode().isEmpty() || e.getCode().isEmpty()) {
        		e.setCode(ResponseMessages.CODE_400);
        		e.setMessage(ResponseMessages.ERROR_400);
        	}
            log.error(e.getCode(),e.getMessage(),e);
            throw e;
        }
    }

    /**
     * search all existing users
     * @param token
     * @return Response
     * @throws ResponseDetails
     */
    public Response findAll() throws ResponseDetails {
        MultipleResponse response = new MultipleResponse();
        try {
        	//run the search for all users
            response.setData(repository.findAll());
            //validate that user collection contains data
            if (response.getData() == null || response.getData().isEmpty()) {
                throw new ResponseDetails(ResponseMessages.CODE_404, ResponseMessages.ERROR_NO_RECORDS);
            } else {
            	//create successful response
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
            }
            return response;
        } catch (ResponseDetails e) {
            log.error(e.getCode(), e.getMessage(), e);
            throw e;
        }
    }

    
    /**
     * Search for a specific user
     * @param token
     * @param id
     * @return Response
     * @throws ResponseDetails
     */
    public Response findById(String id) throws ResponseDetails{
    	SingleResponse response = new SingleResponse();
        try {
        	//run the search for the specific user
        	Optional<User> user = repository.findById(id);
        	//Validate if the user with that id exists
            if(user.isEmpty()){
              throw new ResponseDetails(ResponseMessages.CODE_404,ResponseMessages.ERROR_NON_EXISTING_USER);
            }else{
            	//create successful response
                response.setData(user.get());
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
                return response;
            }
        } catch (ResponseDetails e) {
            log.error(e.getCode(),e.getMessage(),e);
            throw e;
        }
    }
    
    /**
     * update data for an existing user
     * @param token
     * @param user
     * @return Response
     * @throws ResponseDetails
     */
    public Response update(User user) throws ResponseDetails{
        try {
        	//Validates if the user ID exists in the request
            if(user.getId()==null){
              throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
            }else{
            	Optional<User> existingUser = repository.findById(user.getId());
            	//validates if a user exists with that id
            	ValidateUserIsEmpty(existingUser);
            	//validate if there is a change in the email
            	if(!existingUser.get().getEmail().equals(user.getEmail())) {
            		//validate if the new email does not exist in the database
            		validateEmail(user.getEmail());
            	}
            	//validate if there is a user name change
            	if(!existingUser.get().getNameUser().equals(user.getNameUser())) {
            		//Validates if the new user name is not registered
            		validateNameUser(user.getNameUser());
            	}           	
            	/*creates a single object based on the existing user and the request user to avoid
            	saving nulls and not changing unique values*/
            	user = createUpdateUser(existingUser.get(), user);
            	repository.save(user);
            	SingleResponse response = new SingleResponse();
                //create successful response
                response.setData(userResponse(user));
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.USER_UPDATE);
                return response;
            }
        } catch (ResponseDetails e) {
        	if(e.getCode().isEmpty() || e.getCode().isEmpty()) {
        		e.setCode(ResponseMessages.CODE_400);
        		e.setMessage(ResponseMessages.ERROR_400);
        	}
            log.error(e.getCode(),e.getMessage(),e);
            throw e;
        }
    }
    
    /**
     * delete a user by their id
     * @param token
     * @param idUser
     * @return Response
     * @throws ResponseDetails
     */
    public Response deleteById(String idUser) throws ResponseDetails{
    	 try {
             if(idUser==null){
               throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
             }else{
             	Optional<User> existingUser = repository.findById(idUser);
             	ValidateUserIsEmpty(existingUser);
             	repository.deleteById(idUser);
                SingleResponse response = new SingleResponse();
                response.setData(existingUser.get());
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.USER_DELETE);
                return response;
             }
         } catch (ResponseDetails e) {
        	 if(e.getCode().isEmpty() || e.getCode().isEmpty()) {
         		e.setCode(ResponseMessages.CODE_400);
         		e.setMessage(ResponseMessages.ERROR_400);
         	}
             log.error(e.getCode(),e.getMessage(),e);
             throw e;
         }
    }
    
    public void validateEmail(String email) throws ResponseDetails {
    	if(repository.existsByEmail(email)) {
    		throw new ResponseDetails(ResponseMessages.CODE_400,
    				ResponseMessages.ERROR_EMAIL_EXISTING.replace("email", email));
    	}
    }
    
    public void validateNameUser(String nameUser) throws ResponseDetails {
    	if(repository.existsByNameUser(nameUser)) {
    		throw new ResponseDetails(ResponseMessages.CODE_400,
    				ResponseMessages.ERROR_USERNAME_EXISTING.replace("nameUser", nameUser));
    	}
    }
    
    public void validateClientId(String clientId) throws ResponseDetails {
    	if(repository.existsByClientId(clientId)) {
    		throw new ResponseDetails(ResponseMessages.CODE_400,
    				ResponseMessages.ERROR_USER_CREATED);
    	}
    }
    
    public void validateClientSecret(String clientSecret) throws ResponseDetails {
    	if(repository.existsByClientSecret(clientSecret)) {
    		throw new ResponseDetails(ResponseMessages.CODE_400,
    				ResponseMessages.ERROR_USER_CREATED);
    	}
    }
    
    public void validateRol(String idRol) throws ResponseDetails {
    	if(repositoryRoles.findById(idRol).isEmpty()) {
    		throw new ResponseDetails(ResponseMessages.CODE_400,
    				ResponseMessages.ERROR_VALID_ROL);
    	}
    }
    
    public User createUpdateUser(User existingUser, User newUser) {
		if(newUser.getPhone() != null) {
			existingUser.setPhone(newUser.getPhone());
		}
		if(newUser.getCity() != null) {
			existingUser.setCity(newUser.getCity());
		}
		if(newUser.getCountry() != null) {
			existingUser.setCountry(newUser.getCountry());
		}
		if(newUser.getAddress() != null) {
			existingUser.setAddress(newUser.getAddress());
		}
		if(newUser.getName() != null) {
			existingUser.setName(newUser.getName());
		}
		if(newUser.getNameUser() != null) {
			existingUser.setNameUser(newUser.getNameUser());
		}
		if(newUser.getEmail() != null) {
			existingUser.setEmail(newUser.getEmail());
		}
	 return existingUser;
    }
    
    public User userResponse(User user) {
    	User userResponse = new User();
		if(user.getPhone() != null) {
			userResponse.setPhone(user.getPhone());
		}
		if(user.getCity() != null) {
			userResponse.setCity(user.getCity());
		}
		if(user.getCountry() != null) {
			userResponse.setCountry(user.getCountry());
		}
		if(user.getAddress() != null) {
			userResponse.setAddress(user.getAddress());
		}
		if(user.getName() != null) {
			userResponse.setName(user.getName());
		}
		if(user.getNameUser() != null) {
			userResponse.setNameUser(user.getNameUser());
		}
		if(user.getEmail() != null) {
			userResponse.setEmail(user.getEmail());
		}
	 return userResponse;
    }

    public void ValidateUserIsEmpty(Optional<User> user)throws ResponseDetails  {
	    if(user.isEmpty()) {
	 		throw new ResponseDetails(ResponseMessages.CODE_404,ResponseMessages.ERROR_NON_EXISTING_USER);
	 	} 
    }

	@Override
	public String encrypPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean verifyPassword(String originalPassword, String hashPassword) {
		//(verifyPassword(user.getPassword(),existingUser.get().getPassword())
		return BCrypt.checkpw(originalPassword, hashPassword);
	}
}
