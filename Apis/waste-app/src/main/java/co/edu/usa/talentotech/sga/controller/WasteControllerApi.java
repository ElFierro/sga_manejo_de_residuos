package co.edu.usa.talentotech.sga.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.talentotech.sga.model.Waste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@Tag(name = "Waste", description = "Administracion de residuos")

public interface WasteControllerApi {
    @Operation(summary = "Registra el ingreso de residuos",
    		   description = "Crea un registro del ingreso de residuos, donde el tipo de residuo, clasificacion y el peso"
    		   		+ " son valores obligatorios, el id se genera automaticamente por lo que no es necesario enviarlo en "
    		   		+ "el cuerpo del mensaje."
    		   	)
    
    @RequestBody(required = true,
    content = @Content( 
    			examples = {
    			 @ExampleObject(value = "{\"typeWaste\": \"Domesticos\","
           						+ "\"classification\": \"Carton\","
           						+ "\"recyclable\": true,"
           						+ "\"weight\": \"15.0\","
           						+ "\"finalDisposition\": \"Planta recicladora\"}")}))
    
    @ApiResponses(value = { 
        	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito",
        		content = { 
        			@Content(mediaType = "*/*",examples = {
                       @ExampleObject( 
                    		   value = "{\"data\" : "
	                       				+ "{\"id\": \"664a7ec44572e6301667df0b\","
	                       				+ "\"typeWaste\": \"Domesticos\","
	                       				+ "\"classification\": \"Carton\","
	                       				+ "\"recyclable\": true,"
	                       				+ "\"weight\": 15.0,"
	                       				+ "\"finalDisposition\": \"Planta recicladora\""
	                       				+ "},\"responseDetails\" : "
	                       					+ "{\"code\": \"200\","
	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
      		  @ApiResponse(responseCode = "400", description = "El id del registro de residuo no debe ser parte del cuerpo de la petición:",
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error de esquema en el mensaje XML.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
      		  @ApiResponse(responseCode = "400-1", description = "No se pueden guardar valores negativos en el peso de los residuos:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"400\","
  	      				  		+ "\"message\": \"El peso de los residuos debe ser un valor positivo.\","
  	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-2", description = "Es obligatorio registrar el peso de los residuos:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Especifique el peso del residuo ingresado.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-3", description = "Es requerido registrar la clasificacion de residuos:",
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"400\","
  	      				  		+ "\"message\": \"La clasificacion de residuos es obligatoria.\","
  	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-4", description = "Es necesario indicar el tipo de residuo:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Indique el tipo de residuo.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		@ApiResponse(responseCode = "400", content = @Content),
     		 @ApiResponse(responseCode = "474", description = "Malas peticiones:",
     		 content = @Content(examples = {@ExampleObject(
  				  value = "{\"data\":null,"
    				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error en el servidor, contacte con un administrador.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")}))
      })
    @PostMapping(value = "/", produces = { "application/json" })
    public SingleResponse saveWaste( @Valid @RequestBody Waste waste,BindingResult bindingResult) throws ResponseDetails;
	
    
    @Operation(summary = "Obtener todos los registros de residuos",
 		   description = "Devuelve una lista de todos los registros de residuos existentes, en caso que la coleccion no contenga "
 		   		+ "registros devuelve un mensaje de error."
 		   	)
    @ApiResponses(value = { 
        	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito.",
        		content = { 
        			@Content(mediaType = "*/*",examples = {
                       @ExampleObject( 
                    		   value = "{\"data\" : "
	                       				+ "[{\"id\": \"66495f42101be45fce9411e6\","
	                       				+ "\"typeWaste\": \"Residuos aprovechables\","
	                       				+ "\"classification\": \"plastico\","
	                       				+ "\"recyclable\": true,"
	                       				+ "\"weight\": 5.566,"
	                       				+ "\"rolUser\": \"2\","
	                       				+ "\"dateAdmission\": \"2024-05-19T02:09:06.205+00:00\","
	                       				+ "\"finalDisposition\": \"Fabricas\"},"
	                       				+ "{\"id\": \"6647af72159d2f5272cf7534\","
	                       				+ "\"typeWaste\": \"Residuos organicos\","
	                       				+ "\"classification\": \"Alimentos\","
	                       				+ "\"recyclable\": true,"
	                       				+ "\"weight\": 15.8,"
	                       				+ "\"clientSecret\": \"dd1f417a7259cce4a8299178c8e2e387\","
	                       				+ "\"dateAdmission\": \"2024-05-17T19:26:42.667+00:00\","
	                       				+ "\"finalDisposition\": \"Compostaje\"}],"
	                       				+ "\"responseDetails\" : "
	                       					+ "{\"code\": \"200\","
	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
      		  @ApiResponse(responseCode = "404", description = "En caso de que la colección no tenga registros:",
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"404\","
	      				  		+ "\"message\": \"No existen registros.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
      		@ApiResponse(responseCode = "400", content = @Content),
      		 @ApiResponse(responseCode = "474", description = "Malas peticiones:",
      		 content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error en el servidor, contacte con un administrador.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")}))
      })
    @GetMapping("/")
    public MultipleResponse findAllWastes(@RequestParam(required = false) String typeWaste) throws ResponseDetails;
    
    
    @Operation(summary = "Buscar el registro de residuos por id",
  		   description = "Obtiene todos los datos del registro de un residuo especifico, en el caso que dicho registro no exista "
  		   		+ "recibira un mensaje de error."
  		   	)
     
     @ApiResponses(value = { 
         	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito.",
         		content = { 
         			@Content(mediaType = "*/*",examples = {
                        @ExampleObject( 
                     		   value = "{\"data\" : "
 	                       				+ "{\"id\": \"66495f42101be45fce9411e6\","
 	                       				+ "\"typeWaste\": \"Residuos aprovechables\","
 	                       				+ "\"classification\": \"plastico\","
 	                       				+ "\"recyclable\": true,"
 	                       				+ "\"weight\": \"1.5\","
 	                       				+ "\"dateAdmission\": \"2024-05-19T02:09:06.205+00:00\","
 	                       				+ "\"finalDisposition\": \"Fabricas\"},"
 	                       				+ "\"responseDetails\" : "
 	                       					+ "{\"code\": \"200\","
 	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
 	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
       		  @ApiResponse(responseCode = "404", description = "En caso de que el registro no exista:",
       		  content = @Content(examples = {@ExampleObject(
       				  value = "{\"data\":null,"
       				  		+ "\"responseDetails\" : "
 	      				  		+ "{\"code\": \"404\","
 	      				  		+ "\"message\": \"El servidor no pudo encontrar el contenido solicitado.\","
 	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
       		@ApiResponse(responseCode = "400", content = @Content)
      
       })
    @GetMapping("/{id}")
    public SingleResponse getWasteById( @PathVariable String id) throws ResponseDetails;
    
    
    @Operation(summary = "Actualizar el ingreso de residuos",
 		   description = "Actualiza un registro de residuos, donde el tipo de residuo, clasificacion y el peso"
 		   		+ " son valores obligatorios,ademas es requerido enviar el id en el cuerpo del mensaje."
 		   	)
 
 @RequestBody(required = true,
 content = @Content( 
 			examples = {
 			 @ExampleObject(value = "{\"id\": \"6647a4e5c8b6710ae763f63d\","
 					 			+ "\"typeWaste\": \"Domesticos\","
        						+ "\"classification\": \"Carton\","
        						+ "\"recyclable\": true,"
        						+ "\"weight\": \"15.0\","
        						+ "\"finalDisposition\": \"Planta recicladora\"}")}))
 
 @ApiResponses(value = { 
     	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito",
     		content = { 
     			@Content(mediaType = "*/*",examples = {
                    @ExampleObject( 
                 		   value = "{\"data\" : "
	                       				+ "{\"id\": \"6647a4e5c8b6710ae763f63d\","
	                       				+ "\"typeWaste\": \"Domesticos\","
	                       				+ "\"classification\": \"Carton\","
	                       				+ "\"recyclable\": true,"
	                       				+ "\"weight\": 15.0,"
	                       				+ "\"finalDisposition\": \"Planta recicladora\""
	                       				+ "},\"responseDetails\" : "
	                       					+ "{\"code\": \"200\","
	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
   		  @ApiResponse(responseCode = "400", description = "El id del registro de residuo debe ser parte del cuerpo de la petición:",
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error de esquema en el mensaje XML.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
   		  @ApiResponse(responseCode = "400-1", description = "No se pueden guardar valores negativos en el peso de los residuos:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"El peso de los residuos debe ser un valor positivo.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-2", description = "Es obligatorio registrar el peso de los residuos:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Especifique el peso del residuo ingresado.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-3", description = "Es requerido registrar la clasificacion de residuos:",
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"La clasificacion de residuos es obligatoria.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-4", description = "Es necesario indicar el tipo de residuo:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Indique el tipo de residuo.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		 @ApiResponse(responseCode = "404", description = "El usuario debe existir en la colección:", 
  		  content = @Content(examples = {@ExampleObject(
  				  value = "{\"data\":null,"
  				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"404\","
	      				  		+ "\"message\": \"El servidor no pudo encontrar el contenido solicitado.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		@ApiResponse(responseCode = "400", content = @Content),
  		 @ApiResponse(responseCode = "474", description = "Malas peticiones:",
  		 content = @Content(examples = {@ExampleObject(
				  value = "{\"data\":null,"
 				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"474\","
	      				  		+ "\"message\": \"Error en el servidor, contacte con un administrador.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")}))
   })
    public SingleResponse updateWaste( @RequestBody Waste waste) throws ResponseDetails;
    
    
    @Operation(summary = "Eliminar un registro de residuos por id",
   		   description = "Elimina un registro de residuos especifico, en el caso que dicho registro no exista recibira un mensaje de error."
   		   	)
      
      @ApiResponses(value = { 
          	@ApiResponse(responseCode = "200", description = "El usuario se elimino exitosamente.",
          		content = { 
          			@Content(mediaType = "*/*",examples = {
                         @ExampleObject( 
                        		 value = "{\"data\" : "
 	                       				+ "{\"id\": \"6647a4e5c8b6710ae763f63d\","
 	                       				+ "\"typeWaste\": \"Domesticos\","
 	                       				+ "\"classification\": \"Carton\","
 	                       				+ "\"recyclable\": true,"
 	                       				+ "\"weight\": 15.0,"
 	                       				+ "\"finalDisposition\": \"Planta recicladora\""
 	                       				+ "},\"responseDetails\" : "
 	                       					+ "{\"code\": \"200\","
 	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
 	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
        		  @ApiResponse(responseCode = "404", description = "En caso de que el registro no exista:",
        		  content = @Content(examples = {@ExampleObject(
        				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"404\","
  	      				  		+ "\"message\": \"El servidor no pudo encontrar el contenido solicitado.\","
  	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
        		@ApiResponse(responseCode = "400", content = @Content)
        })
    @DeleteMapping("/{id}")
    public SingleResponse deleteWasteById( @PathVariable String id) throws ResponseDetails;
    

}
