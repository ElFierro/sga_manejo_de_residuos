package co.edu.usa.talentotech.sga.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Document(value = "roles")
public class Roles {
	@Id
	private String id;
	
	private String rol;
	
}
