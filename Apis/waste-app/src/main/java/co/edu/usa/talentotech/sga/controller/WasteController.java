package co.edu.usa.talentotech.sga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.Response;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import co.edu.usa.talentotech.sga.model.Waste;
import co.edu.usa.talentotech.sga.service.ErrorService;
import co.edu.usa.talentotech.sga.service.WasteService;

@RestController
@RequestMapping("/waste")
@CrossOrigin(origins = "*")
public class WasteController {
	 	@Autowired
	    private ErrorService errorService;

	    @Autowired
	    private WasteService service;

	    private static final Logger log = LoggerFactory.getLogger(WasteController.class);


	    @PostMapping("/")
	    public Response save(@RequestBody Waste user) throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        try {
	            response = (SingleResponse) service.save( user);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
	    } 
	    
	    @GetMapping("/")
	    public Response fidAll() throws ResponseDetails {
	        MultipleResponse response = new MultipleResponse();
	        try {
	            response = (MultipleResponse) service.findAll();
	        } catch (ResponseDetails e) {
	        	response.setResponseDetails(e);
	        }
	        return response;
	    }
	    
	    @GetMapping("/{id}")
	    public Response getById( @PathVariable String id) 
	            throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        try {
	            response = (SingleResponse)service.findById(id);
	        } catch (ResponseDetails e) {
	        	response.setResponseDetails(e);
	        }
	        return response;
	    }
	    
	    @DeleteMapping("/{id}")
	    public Response deleteById( @PathVariable String id) throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        try {
	            response = (SingleResponse) service.deleteById(id);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
	    }    
	    
	    @PutMapping("/")
	    public Response update( @RequestBody Waste waste) throws ResponseDetails {
	            SingleResponse response = new SingleResponse();
	        try {
	            response = (SingleResponse) service.update(waste);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
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
