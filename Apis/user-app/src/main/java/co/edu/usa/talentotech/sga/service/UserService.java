package co.edu.usa.talentotech.sga.service;

import co.edu.usa.sga.models.*;
import co.edu.usa.sga.utilities.constans.ResponseMessages;

import org.springframework.stereotype.Service;
import co.edu.usa.talentotech.sga.model.User;
import co.edu.usa.talentotech.sga.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class UserService implements EncriptService {

	@Autowired
	private UserRepository userRespository;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	/**
	 * creates a new user with the encrypted password where the email, clientId and
	 * client secret must be unique values, a valid roleUser must be sent
	 * 
	 * @param token
	 * @param user
	 * @return Response
	 * @throws ResponseDetails
	 */
	public SingleResponse createUser(User user) throws ResponseDetails {
		try {
			// validate that the id is not present in the body of the request
			userIdExists(user.getId());
			// validate if email, clientId, ClientSecret already exists
			validateEmail(user.getEmail());
			validateClientId(user.getClientId());
			validateClientSecret(user.getClientSecret());
			// encrypt password
			user.setPassword(encrypPassword(user.getPassword()));
			//Create user
			userRespository.save(user);
			//Create successful response
			return singleResponseUser(user,ResponseMessages.CODE_200, ResponseMessages.USER_CREATED);
		} catch (ResponseDetails e) {
			if (e.getCode().isEmpty() || e.getCode().isEmpty()) {
				e.setCode(ResponseMessages.CODE_400);
				e.setMessage(ResponseMessages.ERROR_400);
			}
			log.error(e.getCode(), e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * 
	 * validates if there are records in the data collection and returns a list of
	 * all users
	 * 
	 * @param token
	 * @return Response
	 * @throws ResponseDetails
	 */
	public MultipleResponse fidAllUsers() throws ResponseDetails {
		try {
			// run the search for all users
			List<User> users = userRespository.findAll();
			// validate that user collection contains data
			if (users == null || users.isEmpty()) {
				throw new ResponseDetails(ResponseMessages.CODE_404, ResponseMessages.ERROR_NO_RECORDS);
			} 
			// create successful response
			return multipleReponsUser(users,ResponseMessages.CODE_200,ResponseMessages.ERROR_200);
		} catch (ResponseDetails e) {
			log.error(e.getCode(), e.getMessage(), e);
			throw e;
		}
	}
	
	
	/**
	 * searches if a user exists with a specific id, and returns its data
	 * 
	 * @param token
	 * @param id
	 * @return Response
	 * @throws ResponseDetails
	 */
	public SingleResponse getUserById(String id) throws ResponseDetails {
		try {
			// run the search for the specific user
			Optional<User> user = userRespository.findById(id);
			// Validate if the user with that id exists
			userIdIsEmpty(user.isEmpty());
			// create successful response
			return singleResponseUser(user.get(),ResponseMessages.CODE_200, ResponseMessages.ERROR_200);
		} catch (ResponseDetails e) {
			log.error(e.getCode(), e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * validates if the user exists, updates the user's name, city, email and role
	 * 
	 * @param token
	 * @param user
	 * @return Response
	 * @throws ResponseDetails
	 */
	public SingleResponse updateUser(User user) throws ResponseDetails {
		try {
			// Validates if the user ID exists in the request
			userIdIsNull(user.getId());
			Optional<User> existingUser = userRespository.findById(user.getId());
			// validates if a user exists with that id
			ValidateUserIsEmpty(existingUser);
			// validate if there is a change in the email
			if (!existingUser.get().getEmail().equals(user.getEmail())) {
				// validate if the new email does not exist in the database
				validateEmail(user.getEmail());
			}
			// Create the user with the data to be updated
			user = createUpdateUser(existingUser.get(), user);
			//Update user
			userRespository.save(user);
			// create successful response
			return singleResponseUser(user,ResponseMessages.CODE_200, ResponseMessages.USER_UPDATE);
		} catch (ResponseDetails e) {
			if (e.getCode().isEmpty() || e.getCode().isEmpty()) {
				e.setCode(ResponseMessages.CODE_400);
				e.setMessage(ResponseMessages.ERROR_400);
			}
			log.error(e.getCode(), e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * validates if the user exists, and removes it from the collection
	 * 
	 * @param token
	 * @param idUser
	 * @return Response
	 * @throws ResponseDetails
	 */
	public SingleResponse deleteUserById(String idUser) throws ResponseDetails {
		try {
			userIdIsNull(idUser);
			Optional<User> existingUser = userRespository.findById(idUser);
			ValidateUserIsEmpty(existingUser);
			userRespository.deleteById(idUser);
			return singleResponseUser(existingUser.get(),ResponseMessages.CODE_200, ResponseMessages.USER_DELETE);
		} catch (ResponseDetails e) {
			if (e.getCode().isEmpty() || e.getCode().isEmpty()) {
				e.setCode(ResponseMessages.CODE_400);
				e.setMessage(ResponseMessages.ERROR_400);
			}
			log.error(e.getCode(), e.getMessage(), e);
			throw e;
		}
	}

	public MultipleResponse multipleReponsUser(List<User> data, String code, String message) throws ResponseDetails {
		MultipleResponse responseUsers = new MultipleResponse();
		responseUsers.setData(data);
		responseUsers.getResponseDetails().setCode(code);
		responseUsers.getResponseDetails().setMessage(message);
		return responseUsers;
	}
	
	public SingleResponse singleResponseUser(User data, String code, String message) throws ResponseDetails {
		SingleResponse responseUser = new SingleResponse();
		responseUser.setData(data);
		responseUser.getResponseDetails().setCode(code);
		responseUser.getResponseDetails().setMessage(message);
		return responseUser;
	}
	
	public void userIdExists(String userId) throws ResponseDetails {
		if (userId != null) {
			throw new ResponseDetails(ResponseMessages.CODE_400, ResponseMessages.ERROR_400);
		}
	}

	public void userIdIsEmpty(Boolean userisEmpty) throws ResponseDetails {
		if (userisEmpty) {
			throw new ResponseDetails(ResponseMessages.CODE_404, ResponseMessages.ERROR_NON_EXISTING_USER);
		}
	}

	public void userIdIsNull(String userId) throws ResponseDetails {
		if (userId == null) {
			throw new ResponseDetails(ResponseMessages.CODE_400, ResponseMessages.ERROR_400);
		}
	}

	public void validateEmail(String email) throws ResponseDetails {
		if (userRespository.existsByEmail(email)) {
			throw new ResponseDetails(ResponseMessages.CODE_400,
					ResponseMessages.ERROR_EMAIL_EXISTING.replace("email", email));
		}
	}

	public void validateClientId(String clientId) throws ResponseDetails {
		if (userRespository.existsByClientId(clientId)) {
			throw new ResponseDetails(ResponseMessages.CODE_400, ResponseMessages.ERROR_USER_CREATED);
		}
	}

	public void validateClientSecret(String clientSecret) throws ResponseDetails {
		if (userRespository.existsByClientSecret(clientSecret)) {
			throw new ResponseDetails(ResponseMessages.CODE_400, ResponseMessages.ERROR_USER_CREATED);
		}
	}
	
	
	public void ValidateUserIsEmpty(Optional<User> user) throws ResponseDetails {
		if (user.isEmpty()) {
			throw new ResponseDetails(ResponseMessages.CODE_404, ResponseMessages.ERROR_NON_EXISTING_USER);
		}
	}


	public User createUpdateUser(User existingUser, User newUser) {

		if (newUser.getCity() != null) {
			existingUser.setCity(newUser.getCity());
		}
		if (newUser.getName() != null) {
			existingUser.setName(newUser.getName());
		}
		if (newUser.getEmail() != null) {
			existingUser.setEmail(newUser.getEmail());
		}
		
		return existingUser;
	}

	@Override
	public String encrypPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean verifyPassword(String originalPassword, String hashPassword) {
		// (verifyPassword(user.getPassword(),existingUser.get().getPassword())
		return BCrypt.checkpw(originalPassword, hashPassword);
	}
}
