package co.edu.usa.talentotech.sga.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import co.edu.usa.talentotech.sga.model.Waste;
import co.edu.usa.talentotech.sga.repository.WasteRepository;

@Service
public class WasteService {
    @Autowired
    private WasteRepository repository;
    
    private static final Logger log = LoggerFactory.getLogger(WasteService.class);
    
    public SingleResponse saveWaste(Waste waste) throws ResponseDetails{
        try {
            if(waste.getId()!=null){
              //validate that the id is not present in the body of the request
              throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
            }
            //save waste record
            waste = repository.save(waste);
            SingleResponse response = new SingleResponse();
            //create successful response
            response.setData(waste);
            response.getResponseDetails().setCode(ResponseMessages.CODE_200);
            response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
            return response;
            
        } catch (ResponseDetails e) {
        	if(e.getCode().isEmpty() || e.getCode().isEmpty()) {
        		e.setCode(ResponseMessages.CODE_400);
        		e.setMessage(ResponseMessages.ERROR_400);
        	}
            log.error(e.getCode(),e.getMessage(),e);
            throw e;
        }
    }
    
    public MultipleResponse findAllWastes(String typeWaste) throws ResponseDetails {
        MultipleResponse response = new MultipleResponse();
        try {
        	//search all waste records
        	if(typeWaste!=null) {
        		response.setData(repository.findByTypeWaste(typeWaste));
        	}else {
        		response.setData(repository.findAll());
        	}
            //validates if the collection of residues contains data
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

    public SingleResponse findWasteById(String id) throws ResponseDetails{
    	SingleResponse response = new SingleResponse();
        try {
        	//search for the record of a specific waste
        	Optional<Waste> waste = repository.findById(id);
        	//Validate if there is any record with that id
            if(waste.isEmpty()){
              throw new ResponseDetails(ResponseMessages.CODE_404,ResponseMessages.ERROR_404);
            }else{
            	//create successful response
                response.setData(waste.get());
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
                return response;
            }
        } catch (ResponseDetails e) {
            log.error(e.getCode(),e.getMessage(),e);
            throw e;
        }
    }
    
    public SingleResponse updateWaste(Waste waste) throws ResponseDetails{
        try {
        	//validates if the id exists in the body of the request
            if(waste.getId()==null){
              throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
            }else{
            	Optional<Waste> existingUser = repository.findById(waste.getId());
            	ValidateWasteIsEmpty(existingUser);
            	repository.save(waste);
            	SingleResponse response = new SingleResponse();
                //create successful response
                response.setData(waste);
                response.getResponseDetails().setCode(ResponseMessages.CODE_200);
                response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
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
    
    public SingleResponse deleteWasteById(String idWaste) throws ResponseDetails{
   	 try {
            if(idWaste==null){
              throw new ResponseDetails(ResponseMessages.CODE_400,ResponseMessages.ERROR_400);
            }else{
            	Optional<Waste> existingUser = repository.findById(idWaste);
            	ValidateWasteIsEmpty(existingUser);
            	repository.deleteById(idWaste);
               SingleResponse response = new SingleResponse();
               response.setData(existingUser.get());
               response.getResponseDetails().setCode(ResponseMessages.CODE_200);
               response.getResponseDetails().setMessage(ResponseMessages.ERROR_200);
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
    
    public void ValidateWasteIsEmpty(Optional<Waste> waste)throws ResponseDetails  {
	    if(waste.isEmpty()) {
	 		throw new ResponseDetails(ResponseMessages.CODE_404,ResponseMessages.ERROR_404);
	 	} 
    }
}
