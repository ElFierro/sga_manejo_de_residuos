package co.edu.usa.talentotech.sga.model;

import co.edu.usa.sga.models.Records;
import co.edu.usa.sga.utilities.constans.ResponseMessages;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = {
	    "createdDate",
	    "lastModifiedDate"
	})

@JsonInclude(JsonInclude.Include.NON_NULL)

@Document(value = "User")
public class User extends Records<User> implements java.io.Serializable{
	private static final long serialVersionUID = 5L;
	@Id
	private String id;
	
	private String name;
	
	@NotNull(message = ResponseMessages.ERROR_NAMEUSER_NULL)
	@NotBlank(message = ResponseMessages.ERROR_NAMEUSER_NULL)
	private String nameUser;
	
	@NotNull(message = ResponseMessages.ERROR_EMAIL_NULL)
	@NotBlank(message = ResponseMessages.ERROR_EMAIL_NULL)
	@Email
	private String email;
	
	private String country;
	
	private String city;
	
	private String address;
	
	@NotNull(message = ResponseMessages.ERROR_PASSWORD_NULL)
	@NotBlank(message = ResponseMessages.ERROR_PASSWORD_NULL)
	private String password;
	
	@NotNull(message = ResponseMessages.ERROR_ROL_NULL)
	@NotBlank(message = ResponseMessages.ERROR_ROL_NULL)
	private String rolUser;
	
	private Long phone;
	
	@NotNull(message = ResponseMessages.ERROR_400)
	@NotBlank(message = ResponseMessages.ERROR_400)
	private String clientId;
	
	@NotNull(message = ResponseMessages.ERROR_400)
	@NotBlank(message = ResponseMessages.ERROR_400)
	
	private String clientSecret;
	
	@Getter(AccessLevel.NONE)
	private String encriptionKey;
	
    @CreatedDate
    private Date createdDate;
    
    @LastModifiedDate
    private Date lastModifiedDate;
}
