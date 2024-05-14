package co.edu.usa.talentotech.sga.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import co.edu.usa.sga.models.Response;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import co.edu.usa.sga.utilities.constans.Scheme;
import org.springframework.validation.FieldError;

@Service
public class ErrorService {
	public Response getErrorValidation(BindingResult ex) {
          SingleResponse response = new SingleResponse();
        Optional<ObjectError> errorLength = ex.getAllErrors().stream().filter(filterLength -> 
                filterLength.getCode().equals(Scheme.LENGTH)
                || filterLength.getCode().equals(Scheme.DIGITS)
                || filterLength.getCode().equals(Scheme.DECIMAL_MAX)).findFirst();
        Optional<ObjectError> invalidValue = ex.getAllErrors().stream().filter(filterLength -> 
                filterLength.getCode().equals(Scheme.PATTERN)
                || filterLength.getCode().equals(Scheme.MIN)
                || filterLength.getCode().equals(Scheme.MAX)
                || filterLength.getCode().equals(Scheme.TYPE_MISMATCH)).findFirst();
        if(errorLength.isPresent()) {
            response.getResponseDetails().setCode(ResponseMessages.CODE_462);
            response.getResponseDetails().setMessage(String.format
            (ResponseMessages.ERROR_462, ((FieldError) errorLength.get()).getField()));
        }else if(invalidValue.isPresent()) {
            response.getResponseDetails().setCode(ResponseMessages.CODE_463);
            response.getResponseDetails().setMessage(String.format
            (ResponseMessages.ERROR_463, ((FieldError) invalidValue.get()).getField()));
        }else{
            Optional<ObjectError> error = ex.getAllErrors().stream().findFirst();
            response.getResponseDetails().setCode(ResponseMessages.CODE_460);
            response.getResponseDetails().setMessage(String.format
            (ResponseMessages.ERROR_460, error.isPresent() ?
               ((FieldError) error.get()).getField() : Scheme.MISSING_FIELD));
        }
        return response;
    }
}
