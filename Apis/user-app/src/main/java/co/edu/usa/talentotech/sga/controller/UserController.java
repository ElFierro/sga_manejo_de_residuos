package co.edu.usa.talentotech.sga.controller;

import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.AuthTools;
import co.edu.usa.sga.utilities.constans.ResponseMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.usa.talentotech.sga.model.User;
import co.edu.usa.talentotech.sga.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController implements UserControllerApi{

    @Autowired
    private UserService service;

	@Override
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

	@Override
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

	@Override
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

    @Override
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
    

}
