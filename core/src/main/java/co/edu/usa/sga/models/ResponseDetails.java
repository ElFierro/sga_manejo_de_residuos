/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.sga.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Cristian Fierro
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {
    "stackTrace",
    "message",
    "suppressed",
    "localizedMessage",
    "cause"
})
public class ResponseDetails extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String message;
    private LocalDateTime timeStamp;

    public ResponseDetails(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public ResponseDetails(String code, String message, Throwable cause) {
        super(message,cause);
        this.code = code;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
    
    public ResponseDetails(String code, Throwable cause) {
        super(cause);
        this.code = code;
        this.timeStamp = LocalDateTime.now();
    }

    public ResponseDetails() {
        super();
        this.timeStamp = LocalDateTime.now();
    }
    
    public ResponseDetails( Throwable cause) {
        super(cause);
        this.timeStamp = LocalDateTime.now();
    }

    public ResponseDetails(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
