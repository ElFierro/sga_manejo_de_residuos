/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.usa.sga.utilities;

import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import co.edu.usa.sga.utilities.constans.Scheme;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 *
 * @author Cristian Fierro
 */
public class AuthTools {

    /**
     * Metodo que encripta una cadena de texto
     *
     * @param text
     * @return
     */
    public static String MD5Encoder(String text) {

        try {
            MessageDigest md = MessageDigest.getInstance(Scheme.ALGORITHM);
            byte[] b = md.digest(text.getBytes());
            int size = b.length;
            StringBuilder h = new StringBuilder(size);
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0").append(Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
            return h.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

    /**
     * Retorna el usuario y contrasena
     *
     * @param auth
     * @return
     */
    public static String[] getCredentialsBasic(String auth) {
        String base64Credentials = auth.substring(Scheme.BASIC.length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        return credentials.split(Scheme.SEPARATOR, Scheme.LIMIT_BASIC);
    }

    /**
     * Metodo que valida si una cadena de texto es igual a otra
     *
     * @param text
     * @param encryptedText
     * @return
     */
    public static Boolean MD5Valid(String text, String encryptedText) {
        return MD5Encoder(text).equals(encryptedText);
    }

    /**
     * Metodo para generar contraseña aleatoria
     *
     * @return
     */
    public static String GeneratePassword() {
        String values = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int x = 0; x < Scheme.PASSWORD_SIZE; x++) {
            int indiceAleatorio = (int) (Math.random() * values.length());
            password.append(values.charAt(indiceAleatorio));
        }
        return password.toString();
    }

    /**
     * Metodo para Generar clientId
     *
     * @return
     */
    public static String GenerarClientId() {
        // TODO Auto-generated constructor stub
        return MD5Encoder("" + ((int) (Math.random() * 1000000) + 1 + (int) (Math.random() * 1000000) + 1));
    }

    /**
     * Metodo para Generar clientSecret
     * @return 
     */
    public static String GenerarClientSecret() {
        // TODO Auto-generated constructor stub
        return MD5Encoder("" + ((int) (Math.random() * 1000000) + 1 + (int) (Math.random() * 1000000) + 1));
    }

    /**
     * De base64 a string
     * @param text
     * @return 
     */
    public static String Base64ToString(String text) {
        // TODO Auto-generated constructor stub
        byte[] decodedBytes;
        decodedBytes = Base64.getDecoder().decode(text);
        return new String(decodedBytes);
    }

    /**
     * De string a base64
     * @param text
     * @return 
     */
    public static String StringToBase64(String text) {
        // TODO Auto-generated constructor stub
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static Object[] decodeJWT(String jwt, String secret_key, String clientId) {
        CharSequence charJwt = jwt;
        //This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningSecretKey(secret_key, clientId))
                    .build()
                    .parseSignedClaims(charJwt).getPayload();
            return new Object[]{claims.getSubject(), claims.get("clientId").toString(), claims.getIssuedAt(), claims.getExpiration()};
        } catch (JwtException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        //return claims;
        //return claims;
    }

    public static String createJWT(String clientId, String issuer, String secret, String subject, long ttlMillis, String encriptionKey) {

        //The JWT signature algorithm we will be using to sign the token
        return Jwts.builder()
                .header()
                //Generar UUID
                .keyId(new UID().toString())
                .and()
                .audience().add(issuer).and()
                .subject((subject))
                .claim("clientId", clientId)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + ttlMillis))
                .signWith(getSigningKey(secret, clientId))
                .compact();
    }

    private static Key getSigningKey(String secret, String clientID) {
        String key = StringToBase64(secret + clientID);
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static Key getSigningKeyAlt(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private static SecretKey getSigningKey() {
        return Jwts.SIG.HS256.key().build();
    }

    public static SecretKey getSigningSecretKey(String secret, String clientId) {
        String key = StringToBase64(secret + clientId);
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String ObtenerTokenBearer(String token) {
        return token.substring(Scheme.TOKEN_POSITION).trim();
    }
}
