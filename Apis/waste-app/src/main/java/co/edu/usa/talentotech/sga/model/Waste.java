package co.edu.usa.talentotech.sga.model;


import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.usa.sga.models.Records;
import co.edu.usa.sga.utilities.constans.ResponseMessages;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Document(value = "waste")
public class Waste extends Records<Waste> implements java.io.Serializable{
	private static final long serialVersionUID = 5L;
	@Id
	private String id;
	
	@NotNull(message = ResponseMessages.ERROR_TYPE_WASTE_REQUIRED)
	@NotBlank(message = ResponseMessages.ERROR_TYPE_WASTE_REQUIRED)
	private String typeWaste;
	
	@NotNull(message = ResponseMessages.ERROR_CLASSIFICATION_REQUIRED)
	@NotBlank(message = ResponseMessages.ERROR_CLASSIFICATION_REQUIRED)
	private String classification;
	
	private Boolean recyclable;
	
	@NotNull(message = ResponseMessages.ERROR_WEIGHT_REQUIRED)
	@NotBlank(message = ResponseMessages.ERROR_WEIGHT_REQUIRED)
	@DecimalMin(value = "0.0", inclusive = false)
	private double weight;
	
	@CreatedDate
	private Date dateAdmission;
	
	private String finalDisposition;
}
