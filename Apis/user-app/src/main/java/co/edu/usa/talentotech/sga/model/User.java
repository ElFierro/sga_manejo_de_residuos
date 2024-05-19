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

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@JsonInclude(JsonInclude.Include.NON_NULL)

@Document(value = "user")
public class User extends Records<User> implements java.io.Serializable{
	private static final long serialVersionUID = 5L;
	@Id
	private String id;
	
	private String name;
	
	@NotNull(message = ResponseMessages.ERROR_EMAIL_REQUIRED)
	@NotBlank(message = ResponseMessages.ERROR_EMAIL_REQUIRED)
	@Email
	private String email;
	
	private String city;
	
	@NotNull(message = ResponseMessages.ERROR_PASSWORD_REQUIRED)
	@NotBlank(message = ResponseMessages.ERROR_PASSWORD_REQUIRED)
	private String password;
	
	private String clientId;
	
	private String clientSecret;
	
	@Getter(AccessLevel.NONE)
	private String encriptionKey;
	
    @CreatedDate
    private Date createdDate;
    
    @LastModifiedDate
    private Date lastModifiedDate;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    public enum Rol{
    	ADMIN, EMPLOYE, USER;
    }
}
