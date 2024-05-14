package co.edu.usa.talentotech.sga.service;

public interface EncriptService {

	String encrypPassword (String password);
	
	boolean verifyPassword(String originalPassword, String hashPassword); 
}
