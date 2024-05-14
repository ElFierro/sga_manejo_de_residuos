/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.sga.models;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Cristian Fierro
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {
	    "createUser",
	    "updateUser"
	})
public abstract class Records<T> implements Serializable {
 
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 6953519719643583932L;
	private T createUser;
    private T updateUser;
    
    
}