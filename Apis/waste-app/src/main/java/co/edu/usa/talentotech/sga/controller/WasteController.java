package co.edu.usa.talentotech.sga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import co.edu.usa.talentotech.sga.model.Waste;
import co.edu.usa.talentotech.sga.service.WasteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/waste")
@CrossOrigin(origins = "*")
public class WasteController implements WasteControllerApi {

	    @Autowired
	    private WasteService service;

	    @PostMapping("/")
	    public SingleResponse saveWaste(@Valid @RequestBody Waste waste,BindingResult bindingResult) throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        if(bindingResult.hasErrors()) {
	        	throw new ResponseDetails(ResponseMessages.CODE_400,bindingResult.getFieldError().getDefaultMessage());
	        }
	        try {
	            response = service.saveWaste(waste);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
	    } 
	    
	    @GetMapping("/")
	    public MultipleResponse findAllWastes(@RequestParam(required = false) String typeWaste) throws ResponseDetails {
	        MultipleResponse response = new MultipleResponse();
	        try {
	            response = service.findAllWastes(typeWaste);
	        } catch (ResponseDetails e) {
	        	response.setResponseDetails(e);
	        }
	        return response;
	    }
	    
	    @GetMapping("/{id}")
	    public SingleResponse getWasteById( @PathVariable String id) 
	            throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        try {
	            response = service.findWasteById(id);
	        } catch (ResponseDetails e) {
	        	response.setResponseDetails(e);
	        }
	        return response;
	    }
	    
	    @DeleteMapping("/{id}")
	    public SingleResponse deleteWasteById( @PathVariable String id) throws ResponseDetails {
	        SingleResponse response = new SingleResponse();
	        try {
	            response = service.deleteWasteById(id);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
	    }    
	    
	    @PutMapping("/")
	    public SingleResponse updateWaste( @RequestBody Waste waste) throws ResponseDetails {
	            SingleResponse response = new SingleResponse();
	        try {
	            response = (SingleResponse) service.updateWaste(waste);
	        } catch (ResponseDetails e) {
	            response.setResponseDetails(e);
	        }
	        return response;
	    }    
	    
}
