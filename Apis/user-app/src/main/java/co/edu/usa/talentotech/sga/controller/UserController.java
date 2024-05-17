package co.edu.usa.talentotech.sga.controller;

import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.Response;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.AuthTools;
import co.edu.usa.sga.utilities.constans.ResponseMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.usa.talentotech.sga.model.User;
import co.edu.usa.talentotech.sga.service.ErrorService;
import co.edu.usa.talentotech.sga.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private UserService service;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/")
    public SingleResponse createUser(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData,
            @Valid @RequestBody User newUser,BindingResult bindingResult) throws ResponseDetails {
        SingleResponse responseUser = new SingleResponse();
        newUser.setClientSecret(AuthTools.GenerarClientId());
        newUser.setClientId(AuthTools.GenerarClientId());
        if(bindingResult.hasErrors()) {
        	throw new ResponseDetails(ResponseMessages.CODE_400,bindingResult.getFieldError().getDefaultMessage());
        }
        try {
        	responseUser = service.createUser(newUser);
        } catch (ResponseDetails e) {
        	responseUser.setResponseDetails(e);
        }
        return responseUser;
    }    

    @GetMapping("/")
    public MultipleResponse fidAllUsers(@RequestHeader("authorization") String token,
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData) throws ResponseDetails {
        MultipleResponse responseUsers = new MultipleResponse();
        try {
        	responseUsers = service.fidAllUsers();
        } catch (ResponseDetails e) {
        	responseUsers.setResponseDetails(e);
        }
        return responseUsers;
    }


    @GetMapping("/{id}")
    public SingleResponse getUserById(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, @PathVariable String id) 
            throws ResponseDetails {
        SingleResponse responseUser = new SingleResponse();
        try {
        	responseUser = service.getUserById(id);
        } catch (ResponseDetails e) {
        	responseUser.setResponseDetails(e);
        }
        return responseUser;
    }

    @DeleteMapping("/{id}")
    public SingleResponse deleteUserById(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, @PathVariable String id) throws ResponseDetails {
        SingleResponse responseUser = new SingleResponse();
        try {
        	responseUser = service.deleteUserById(id);
        } catch (ResponseDetails e) {
        	responseUser.setResponseDetails(e);
        }
        return responseUser;
    }    

    @PutMapping("/")
    public SingleResponse updateUser(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData,
        @Valid @RequestBody User user,BindingResult bindingResult) throws ResponseDetails {
            SingleResponse responseUser = new SingleResponse();
            if(bindingResult.hasErrors()) {
            	throw new ResponseDetails(ResponseMessages.CODE_400,bindingResult.getFieldError().getDefaultMessage());
            }
        try {
        	responseUser =  service.updateUser(user);
        } catch (ResponseDetails e) {
        	responseUser.setResponseDetails(e);
        }
        return responseUser;
    }  
    
    @GetMapping("/roles")
    public MultipleResponse findAllRoles(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId) 
            throws ResponseDetails {
    	MultipleResponse responseRoles = new MultipleResponse();
        try {
        	responseRoles = service.findAllRoles();
        } catch (ResponseDetails e) {
        	responseRoles.setResponseDetails(e);
        }
        return responseRoles;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Response handleValidationException(BindException ex){
      return errorService.getErrorValidation(ex.getBindingResult());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException ex){
      return errorService.getErrorValidation(ex.getBindingResult());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseDetails.class)
    public Response handleValidationException(ResponseDetails ex){
      SingleResponse response = new SingleResponse();
      response.getResponseDetails().setCode(ex.getCode());
      response.getResponseDetails().setMessage(ex.getMessage());
      return response;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Response handleValidationException(Exception ex){
      SingleResponse response = new SingleResponse();
      response.getResponseDetails().setCode(ResponseMessages.CODE_474);
      response.getResponseDetails().setMessage(ResponseMessages.ERROR_474);
      log.error(ex.toString());
      return response;
    }
}
