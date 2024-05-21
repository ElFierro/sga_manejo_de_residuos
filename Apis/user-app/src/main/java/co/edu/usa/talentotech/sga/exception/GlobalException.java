package co.edu.usa.talentotech.sga.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.edu.usa.sga.models.Response;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import co.edu.usa.talentotech.sga.controller.UserController;
import co.edu.usa.talentotech.sga.service.ErrorService;

@RestControllerAdvice
public class GlobalException {
	 @Autowired
	 private ErrorService errorService;
	 
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);
	 
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
