package com.tecnova.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "tecnova";
    private static final long EXPIRATION_TIME = 86400000L;  // 1 día en milisegundos

    /**
     * Method to generate JWT
     * @param username
     * @return token
     */
    public static String generateToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return token;
    }

    /**
     * Method for get user name from token
     * @param token
     * @return user name
     */
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Method for validate token expired
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    /**
     * Method for get expiration date from token
     * @param token
     * @return
     */
    private static Date getExpirationDateFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
