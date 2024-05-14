/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.sga.utilities.constans;

/**
 *
 * @author Cristian Fierro
 */
public class Scheme {
    /**
     * Constante que define el algoritmo de encriptacion
     */
    public static final String ALGORITHM = "MD5";

    /**
     * Constante que define el nombre del sistema
     */
    public static final String SYSTEM_NAME = "Tienda";
    
    /**
     * Constente Formato Fecha
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Constante Formato Fecha Hora
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Constante Formato Hora
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * Constente Espacio
     */
    public static final String SPACE = " ";


    /**
     * Constante posicion del token
     */
    public static final Integer TOKEN_POSITION = 7;

    /**
     * Constante bearer token
     */
    public static final String BEARER = "Bearer ";

    /**
     * Constante basic auth
     */
    public static final String BASIC = "Basic";

    /**
     * Constante autorizacion
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /**
	 * Cabecera para el usuario.
	 */
	public static final String HEADER_USER = "username";

	/**
	 * Cabecera para la contraseña.
	 */
	public static final String HEADER_PASSWORD = "password";

	/**
	 * Cabecera para el tipo de contenido.
	 */
	public static final String HEADER_TYPE_CONTENT = "Content-Type";

    /**
	 * Cabecera para clientId.
	 */
	public static final String HEADER_CLIENT_ID = "clientId";

    /**
     * Cabecera para el clientSecret.
     */
    public static final String HEADER_CLIENT_SECRET = "clientSecret";

    /**
	 * Valor del tipo de contenido JSON UTF8.
	 */
	public static final String HEADER_VALUE_CONTENT_TYPE = "application/json; charset=UTF-8";
	
    /**
     * Valor del tipo de contenido JSON.
     */
	public static final String HEADER_VALUE_CONTENT_TYPE_JSON = "application/json";

    /**
	 * Formato de fecha "YYYY-MM-DD"
	 */	
	public static final String SIMPLE_DATE_FORMAT="YYYY-MM-DD";


    /**
	 * Valor caracter UTF8
	 */
	public static final String CHARSET_UTF8="UTF8";


    /**
     * missingField
     */
    public static final String MISSING_FIELD = "missingField";

    /**
     * Pattern
     */
    public static final String PATTERN="Pattern";

    /**
     * Digits
     */
    public static final String DIGITS = "Digits";

    /**
     * Length
     */
    public static final String LENGTH = "Length";

    /**
     * DecimalMax
     */
    public static final String DECIMAL_MAX = "DecimalMax";

    /**
     * Max
     */
    public static final String MAX = "Max";

    /**
     * Min
     */
    public static final String MIN = "Min";

    /**
     * typeMismatch
     */
    public static final String TYPE_MISMATCH = "typeMismatch";


    /**
     * Constante para el estado de usuario
     */
    public static final String USER_STATUS = "estadoUsuario";

    /**
     * Constante para el permiso de usuario
     */
    public static final String PERMISSION = "permiso";

    /**
     * Constante para el rol de usuario
     */
    public static final String ROLE = "rol";

    /**
     * Constante para el usuario
     */
    public static final String USER = "usuario";

    /**
     * Constante para el estado producto
     */
    public static final String PRODUCT_STATUS = "estadoProducto";

    /**
     * Constante para el estado venta
     */
    public static final String SALES_STATUS = "estadoVenta";

    /**
     * Constante para la categoria
     */
    public static final String CATEGORY = "categoria";

    /**
     * Constante para el producto
     */
    public static final String PRODUCT = "producto";

    /**
     * Constante para la venta
     */
    public static final String SALE = "venta";

    /**
     * Constante para la imagen
     */
    public static final String IMAGE = "imagen";

    /**
     * Constante cero
     */
    public static final Long ZERO = 0L;

    /**
     * Constante uno
     */
    public static final Long ONE = 1L;


    /**
     * Constante para el estado de usuario
     */
    public static final Long INACTIVE_USER_STATE_ID = 2L;

    /**
     * Constante para el estado de usuario
     */
    public static final Long USER_ROLE_ID = 2L;

    /**
     * Constante para CODIFICACION_ISO8859
     */
    public static String CODIFICATION_ISO8859 = "iso-8859-1";

    /** 
     * Separador de credenciales
     */
    public static String SEPARATOR = ":";

    /**
     * Constante para limite de credenciales
     */
    public static Integer LIMIT_BASIC = 2;

    /**
     * Constante Secret KEY
     */
    public static String SECRE_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKH";


    public static int PASSWORD_SIZE = 8;
}
