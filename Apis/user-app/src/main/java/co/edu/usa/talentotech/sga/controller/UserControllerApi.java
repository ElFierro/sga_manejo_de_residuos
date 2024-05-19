package co.edu.usa.talentotech.sga.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import co.edu.usa.sga.models.MultipleResponse;
import co.edu.usa.sga.models.ResponseDetails;
import co.edu.usa.sga.models.SingleResponse;
import co.edu.usa.talentotech.sga.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;


public interface UserControllerApi {

    @Operation(summary = "Crear un nuevo usuario",
    		   description = "Crea un usuario donde los valores del correo electronico, clientId y clientSecret deben ser valores unicos "
    		   		+ "(es decir no estar registrado en el sistema). El id se genera automaticamente por lo que no es necesario "
    		   		+ "enviarlo en el cuerpo del mensaje, adicionalmente el correo debe tener un formato correcto. Este microservicio"
    		   		+ " encrypta la contraseña y valida que no se envien algunos valores nulos o vacios."
    		   	)
    
    @RequestBody(required = true,
    content = @Content( 
    			examples = {
    			 @ExampleObject(value = "{\"name\": \"Carlos Beltran\","
           						+ "\"email\": \"CarlosBeltran@gmail.com\","
           						+ "\"city\": \"Bogota\","
           						+ "\"rolUser\": \"2\","
           						+ "\"password\": \"12345xse\"}")}))
    
    @ApiResponses(value = { 
        	@ApiResponse(responseCode = "200", description = "El usuario se creo exitosamente.",
        		content = { 
        			@Content(mediaType = "*/*",examples = {
                       @ExampleObject( 
                    		   value = "{\"data\" : "
	                       				+ "{\"id\": \"66495f42101be45fce9411e6\","
	                       				+ "\"name\": \"Carlos Beltran\","
	                       				+ "\"email\": \"CarlosBeltran@gmail.com\","
	                       				+ "\"city\": \"Bogota\","
	                       				+ "\"password\": \"$2a$10$sCLO2rGzBiVnwQRfcJfkH.QCAdOhVcmxpWraLP5WmBUrdrzKgHPm.\","
	                       				+ "\"rolUser\": \"2\","
	                       				+ "\"clientId\": \"4606eb988c80c12f97912352c30f9c13\","
	                       				+ "\"clientSecret\": \"c60609eb1c4443db61e1b09587ed1971\","
	                       				+ "\"createdDate\": \"2024-05-19T02:09:06.205+00:00\","
	                       				+ "\"lastModifiedDate\": \"2024-05-19T02:09:06.205+00:00\""
	                       				+ "},\"responseDetails\" : "
	                       					+ "{\"code\": \"200\","
	                       					+ "\"message\": \"El usuario se creo exitosamente.\","
	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
      		  @ApiResponse(responseCode = "400", description = "El valor id de usuario no debe ser parte del cuerpo de la petición:",
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error de esquema en el mensaje XML.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
      		  @ApiResponse(responseCode = "400-1", description = "El correo electrónico no debe estar vinculado a otro usuario existente:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"400\","
  	      				  		+ "\"message\": \"El correo electronico: 'CarlosBeltran@gmail.com' ya esta registrado.\","
  	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-2", description = "El correo electrónico debe tener un formato correcto:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Debe ser una dirección de correo electrónico con formato correcto.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-3", description = "El correo electrónico no puede ser nulo o vacío:",
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"400\","
  	      				  		+ "\"message\": \"El correo electronico es requerido.\","
  	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		  @ApiResponse(responseCode = "400-4", description = "El rol id debe ser valido, 1 para administradores, 2 para empleados y 3 para usuarios:", 
      		  content = @Content(examples = {@ExampleObject(
      				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Este rol de usuario no es valido.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		 @ApiResponse(responseCode = "400-5", description = "El rol id no puede ser nulo o vacío:",
     		  content = @Content(examples = {@ExampleObject(
     				  value = "{\"data\":null,"
       				  		+ "\"responseDetails\" : "
 	      				  		+ "{\"code\": \"400\","
 	      				  		+ "\"message\": \"Este rol de usuario no es valido.\","
 	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		 @ApiResponse(responseCode = "400-6", description = "En rol id debe de enviarse siempre:",
    		  content = @Content(examples = {@ExampleObject(
    				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Especifique el rol del usuario.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		 @ApiResponse(responseCode = "400-7", description = "La contraseña no debe de ser vacía o nula:",
    		  content = @Content(examples = {@ExampleObject(
    				  value = "{\"data\":null,"
      				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Debe especificar un valor para la contraseña.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
      		 @ApiResponse(responseCode = "474", description = "Malas peticiones:",
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"474\","
	      				  		+ "\"message\": \"Error en el servidor, contacte con un administrador.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")}))
      })
    @PostMapping(value = "/", produces = { "application/json" })
    public SingleResponse createUser(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData,
            @Valid @RequestBody User newUser,BindingResult bindingResult) throws ResponseDetails ;
	
    
    @Operation(summary = "Obtener todos los usuarios",
 		   description = "Devuelve una lista de todos los usuarios existentes, en caso que la coleccion no contenga "
 		   		+ "registros devuelve un mensaje de error."
 		   	)
    @ApiResponses(value = { 
        	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito.",
        		content = { 
        			@Content(mediaType = "*/*",examples = {
                       @ExampleObject( 
                    		   value = "{\"data\" : "
	                       				+ "[{\"id\": \"66495f42101be45fce9411e6\","
	                       				+ "\"name\": \"Carlos Beltran\","
	                       				+ "\"email\": \"CarlosBeltran@gmail.com\","
	                       				+ "\"city\": \"Bogota\","
	                       				+ "\"password\": \"$2a$10$sCLO2rGzBiVnwQRfcJfkH.QCAdOhVcmxpWraLP5WmBUrdrzKgHPm.\","
	                       				+ "\"rolUser\": \"2\","
	                       				+ "\"clientId\": \"4606eb988c80c12f97912352c30f9c13\","
	                       				+ "\"clientSecret\": \"c60609eb1c4443db61e1b09587ed1971\","
	                       				+ "\"createdDate\": \"2024-05-19T02:09:06.205+00:00\","
	                       				+ "\"lastModifiedDate\": \"2024-05-19T02:09:06.205+00:00\"},"
	                       				+ "{\"id\": \"6647af72159d2f5272cf7534\","
	                       				+ "\"name\": \"Martin Mayda\","
	                       				+ "\"email\": \"Martinoly@gmail.com\","
	                       				+ "\"city\": \"Bogota\","
	                       				+ "\"password\": \"$2a$10$G4BkGJ.TgvEPenOiNwQlHufBFZsyrBySrfNg4C4w92NsumyjTL6AC\","
	                       				+ "\"rolUser\": \"1\","
	                       				+ "\"clientId\": \"c8d16bb87fc2a42a61412e2c21fbb68f\","
	                       				+ "\"clientSecret\": \"dd1f417a7259cce4a8299178c8e2e387\","
	                       				+ "\"createdDate\": \"2024-05-17T19:26:42.667+00:00\","
	                       				+ "\"lastModifiedDate\": \"2024-05-17T19:26:42.667+00:00\"}],"
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
    public MultipleResponse fidAllUsers(@RequestHeader("authorization") String token,
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData) throws ResponseDetails;
    
    
    @Operation(summary = "Buscar por el id del usuario",
  		   description = "Obtiene todos los datos de un usuario especifico, en el caso que dicho usuario no exista "
  		   		+ "recibira un mensaje de error."
  		   	)
     
     @ApiResponses(value = { 
         	@ApiResponse(responseCode = "200", description = "La solicitud ha tenido éxito.",
         		content = { 
         			@Content(mediaType = "*/*",examples = {
                        @ExampleObject( 
                     		   value = "{\"data\" : "
 	                       				+ "{\"id\": \"66495f42101be45fce9411e6\","
 	                       				+ "\"name\": \"Carlos Beltran\","
 	                       				+ "\"email\": \"CarlosBeltran@gmail.com\","
 	                       				+ "\"city\": \"Bogota\","
 	                       				+ "\"password\": \"$2a$10$sCLO2rGzBiVnwQRfcJfkH.QCAdOhVcmxpWraLP5WmBUrdrzKgHPm.\","
 	                       				+ "\"rolUser\": \"2\","
 	                       				+ "\"clientId\": \"4606eb988c80c12f97912352c30f9c13\","
 	                       				+ "\"clientSecret\": \"c60609eb1c4443db61e1b09587ed1971\","
 	                       				+ "\"createdDate\": \"2024-05-19T02:09:06.205+00:00\","
 	                       				+ "\"lastModifiedDate\": \"2024-05-19T02:09:06.205+00:00\"},"
 	                       				+ "\"responseDetails\" : "
 	                       					+ "{\"code\": \"200\","
 	                       					+ "\"message\": \"La solicitud ha tenido éxito.\","
 	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
       		  @ApiResponse(responseCode = "404", description = "En caso de que el usuario no exista:",
       		  content = @Content(examples = {@ExampleObject(
       				  value = "{\"data\":null,"
       				  		+ "\"responseDetails\" : "
 	      				  		+ "{\"code\": \"404\","
 	      				  		+ "\"message\": \"Este usuario no existe.\","
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
    @GetMapping("/{id}")
    public SingleResponse getUserById(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, @PathVariable String id) 
            throws ResponseDetails;
    
    
    @Operation(summary = "Actualizar usuario",
 		   description = "Actualiza un usuario existente donde el correo electronico deben ser valor unico (es decir no"
 		   		+ " estar registrado en el sistema). El id debe enviarse en el cuerpo del mensaje, "
 		   		+ "adicionalmente el correo debe tener un formato correcto.")
 
 @RequestBody(required = true,
 content = @Content( 
 			examples = {
 			 @ExampleObject(value = "{\"id\": \"66495f42101be45fce9411e6\","
 			 					+ "\"name\": \"Carlos Beltran\","
        						+ "\"email\": \"CarlosBeltran@gmail.com\","
        						+ "\"city\": \"Bogota\","
        						+ "\"rolUser\": \"2\","
        						+ "\"password\": \"12345xse\"}")}))
 
 @ApiResponses(value = { 
     	@ApiResponse(responseCode = "200", description = "El usuario se actualizo exitosamente.",
     		content = { 
     			@Content(mediaType = "*/*",examples = {
                    @ExampleObject( 
                 		   value = "{\"data\" : "
	                       				+ "{\"id\": \"66495f42101be45fce9411e6\","
	                       				+ "\"name\": \"Carlos Beltran\","
	                       				+ "\"email\": \"CarlosBeltran@gmail.com\","
	                       				+ "\"city\": \"Bogota\","
	                       				+ "\"password\": \"$2a$10$sCLO2rGzBiVnwQRfcJfkH.QCAdOhVcmxpWraLP5WmBUrdrzKgHPm.\","
	                       				+ "\"rolUser\": \"2\","
	                       				+ "\"clientId\": \"4606eb988c80c12f97912352c30f9c13\","
	                       				+ "\"clientSecret\": \"c60609eb1c4443db61e1b09587ed1971\","
	                       				+ "\"createdDate\": \"2024-05-19T02:09:06.205+00:00\","
	                       				+ "\"lastModifiedDate\": \"2024-05-19T02:09:06.205+00:00\""
	                       				+ "},\"responseDetails\" : "
	                       					+ "{\"code\": \"200\","
	                       					+ "\"message\": \"El usuario se actualizo exitosamente.\","
	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
     	 @ApiResponse(responseCode = "404", description = "El id del usuario debe corresponder a un usuario existente:",
  		  content = @Content(examples = {@ExampleObject(
  				  value = "{\"data\":null,"
  				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"404\","
	      				  		+ "\"message\": \"El usuario no existe.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
   		  @ApiResponse(responseCode = "400", description = "El id del usuario es obligatorio en el cuerpo de la petición:",
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Error de esquema en el mensaje XML.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})), 
   		  @ApiResponse(responseCode = "400-1", description = "El correo electrónico no puede estar registrado con otro usuario:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"El correo electronico: 'CarlosBeltran@gmail.com' ya esta registrado.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-2", description = "El correo electrónico debe tener un formato correcto:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Debe ser una dirección de correo electrónico con formato correcto.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-3", description = "El correo electrónico no puede ser nulo o vacío:",
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
     				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"El correo electronico es requerido.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		  @ApiResponse(responseCode = "400-4", description = "El rol id debe ser valido, 1 para administradores, 2 para empleados y 3 para usuarios:", 
   		  content = @Content(examples = {@ExampleObject(
   				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Este rol de usuario no es valido.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		 @ApiResponse(responseCode = "400-5", description = "El rol id no puede ser nulo o vacío:",
  		  content = @Content(examples = {@ExampleObject(
  				  value = "{\"data\":null,"
    				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Este rol de usuario no es valido.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		 @ApiResponse(responseCode = "400-6", description = "En rol id debe de enviarse siempre:",
 		  content = @Content(examples = {@ExampleObject(
 				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Especifique el rol del usuario.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		 @ApiResponse(responseCode = "400-7", description = "La contraseña no debe de ser vacía o nula:",
 		  content = @Content(examples = {@ExampleObject(
 				  value = "{\"data\":null,"
   				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"400\","
	      				  		+ "\"message\": \"Debe especificar un valor para la contraseña.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})),
   		 @ApiResponse(responseCode = "474", description = "Malas peticiones:",
		  content = @Content(examples = {@ExampleObject(
				  value = "{\"data\":null,"
  				  		+ "\"responseDetails\" : "
	      				  		+ "{\"code\": \"474\","
	      				  		+ "\"message\": \"Error en el servidor, contacte con un administrador.\","
	      				  		+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")}))
   })
    public SingleResponse updateUser(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, HttpServletRequest requestData,
        @Valid @RequestBody User user,BindingResult bindingResult) throws ResponseDetails;
    
    
    @Operation(summary = "Eliminar usuario por id",
   		   description = "Elimina un usuario especifico, en el caso que dicho usuario no exista recibira un mensaje de error."
   		   	)
      
      @ApiResponses(value = { 
          	@ApiResponse(responseCode = "200", description = "El usuario se elimino exitosamente.",
          		content = { 
          			@Content(mediaType = "*/*",examples = {
                         @ExampleObject( 
                      		   value = "{\"data\" : "
  	                       				+ "{\"id\": \"66495f42101be45fce9411e6\","
  	                       				+ "\"name\": \"Carlos Beltran\","
  	                       				+ "\"email\": \"CarlosBeltran@gmail.com\","
  	                       				+ "\"city\": \"Bogota\","
  	                       				+ "\"password\": \"$2a$10$sCLO2rGzBiVnwQRfcJfkH.QCAdOhVcmxpWraLP5WmBUrdrzKgHPm.\","
  	                       				+ "\"rolUser\": \"2\","
  	                       				+ "\"clientId\": \"4606eb988c80c12f97912352c30f9c13\","
  	                       				+ "\"clientSecret\": \"c60609eb1c4443db61e1b09587ed1971\","
  	                       				+ "\"createdDate\": \"2024-05-19T02:09:06.205+00:00\","
  	                       				+ "\"lastModifiedDate\": \"2024-05-19T02:09:06.205+00:00\"},"
  	                       				+ "\"responseDetails\" : "
  	                       					+ "{\"code\": \"200\","
  	                       					+ "\"message\": \"El usuario se elimino exitosamente.\","
  	                       					+ "\"timestamp\": \"2024-05-18T21:09:06.3154494\"}}")})}),
        		  @ApiResponse(responseCode = "404", description = "En caso de que el usuario no exista:",
        		  content = @Content(examples = {@ExampleObject(
        				  value = "{\"data\":null,"
        				  		+ "\"responseDetails\" : "
  	      				  		+ "{\"code\": \"404\","
  	      				  		+ "\"message\": \"Este usuario no existe.\","
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
    @DeleteMapping("/{id}")
    public SingleResponse deleteUserById(@RequestHeader("authorization") String token, 
            @RequestHeader("clientId") String clientId, @PathVariable String id) throws ResponseDetails;
}
