package co.edu.usa.sga.utilities.constans;

public class ResponseMessages {

    public ResponseMessages() {
        throw new IllegalStateException("Utility class");
    }
    
// *** 200 *****//
	public static final String CODE_200 = "200";

	public static final String ERROR_200 = "La solicitud ha tenido éxito";

	// *** 404 *****//
	public static final String CODE_404 = "404";

	public static final String ERROR_404 = "El servidor no pudo encontrar el contenido solicitado";

	// *** 400 *****//
	public static final String CODE_400 = "400";

	public static final String ERROR_400 = "Error de esquema en el mensaje XML";

	// *** 460 *****//
	public static final String CODE_460 = "460";
	public static final String ERROR_460 = "El campo %s es obligatorio";

	public static final String ERROR_460_1 = "El campo ";
	public static final String ERROR_460_2 = " es obligatorio";
	// *** 461 *****//
	public static final String CODE_465 = "461";

	// *** 462 *****//
	public static final String CODE_462 = "462";
	public static final String ERROR_462 = "El campo %s no coincide con la longitud aceptada";

	public static final String ERROR_465_1 = "El campo ";
	public static final String ERROR_465_2 = " no coincide con el formato de dato aceptado";

	// *** 463 *****//
	public static final String CODE_463 = "463";
	public static final String ERROR_463 = "El campo %s no coincide con los posibles valores aceptados";

	public static final String ERROR_463_1 = "El campo ";
	public static final String ERROR_463_2 = " no coincide con los posibles valores aceptados";

	// *** 466 *****//
	public static final String CODE_466 = "466";

	public static final String ERROR_466_1 = "La clave ";
	public static final String ERROR_466_2 = " no existe en la base de datos";

	// *** 464 *****//
	public static final String CODE_464 = "464";
	public static final String ERROR_464 = "El recurso ya existe, favor ingresar uno nuevo.";

	// *** 467 *****//
	public static final String CODE_467 = "467";

	public static final String ERROR_467_1 = "El dato";
	public static final String ERROR_467_2 = " está siendo ocupado en la tabla:";

	// *** 468 *****//
	public static final String CODE_468 = "468";

	public static final String ERROR_468 = "La cantidad de intentos del Token fue superada, el usuario queda inhabilitado.";

	// *** 469 *****//
	public static final String CODE_469 = "469";

	public static final String ERROR_469 = "Error al tratar de desencriptar el usuario.";

	// *** 470 *****//
	public static final String CODE_470 = "470";

	public static final String ERROR_470 = "El token guardado se ha sido consumido previamente.";
	
	// *** 471 *****//
	public static final String CODE_471 = "471";

	public static final String ERROR_471 = "Formato de contraseña incorrecto.";

	// *** 480 *****//
	public static final String CODE_480 = "480";

	public static final String ERROR_480 = "El token no corresponde al enviado.";
	
	// *** 481 *****//
	public static final String CODE_481 = "481";

	public static final String ERROR_481 = "La duración del token ha caducado.";
	
	// *** 472 *****//
	public static final String CODE_472 = "472";

	public static final String ERROR_472 = "El perfil no se puede eliminar por que tiene usuarios activos asociados";

	// *** 473 *****//
	public static final String CODE_473 = "473";

	public static final String ERROR_473 = "La contraseña no puede ser la misma que la anterior.";
	
	// *** 474 *****//
	public static final String CODE_474 = "474";

	public static final String ERROR_474= "Error en el servidor, contacte con un administrador.";
	
	// *** 474 *****//
	public static final String CODE_475 = "475";

	public static final String ERROR_475= "Contraseña es igual a la anterior.";
	
	// *** 476 *****//
	public static final String CODE_476 = "476";

	public static final String ERROR_476 = "Contraseña invalida.";

	// *** 401 *****//
	public static final String CODE_401 = "401";

	public static final String ERROR_401 = "401 Unauthorized.";
	
	
	public static final String USER_CREATED = "El usuario se creo exitosamente.";
	public static final String ERROR_USER_CREATED = "Este usuario ya existe.";
	
	public static final String USER_UPDATE = "El usuario se actualizo exitosamente.";
	public static final String ERROR_NON_EXISTING_USER= "Este usuario no existe.";
	
	public static final String USER_DELETE = "El usuario se elimino exitosamente.";
	
	public static final String ERROR_EMAIL_EXISTING= "El correo electronico: 'email' ya esta registrado.";
	public static final String ERROR_EMAIL_REQUIRED="El correo electronico es requerido.";
	
	public static final String ERROR_PASSWORD_REQUIRED= "Debe especifique un valor para la contraseña.";
	
	public static final String ERROR_NO_RECORDS= "No existen registros.";
	
	public static final String ERROR_VALID_ROL = "Este rol de usuario no es valido";
	public static final String ERROR_ROL_REQUIRED="Especifique el rol del usuario.";
	
	public static final String ERROR_TYPE_WASTE_REQUIRED ="Indique el tipo de residuo";
	public static final String ERROR_CLASSIFICATION_REQUIRED ="La clasificacion de residuos es obligatoria";
	public static final String ERROR_WEIGHT_REQUIRED  ="Especifique el peso";
	
    // *** STATUS *****//
	public static enum STATUS {
		DELETED(0), ACTIVTED(1), BLOCKED(2), REGISTER(3);

		private int codeVal;

		STATUS(int codeVal) {
			this.codeVal = codeVal;
		}

		public int getCodeVal() {
			return codeVal;
		}
	}
}
