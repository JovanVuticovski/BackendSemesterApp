package com.example.backendsemesterapp.jwtUtil;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    // Secret key value
    private static final String SECRET_KEY ="614E645267556B58703273357638792F423F4428472B4B6250655368566D597133743677397A244326462948404D635166546A576E5A7234753778214125442A472D4B614E645267556B58703273357638792F423F4528482B4D6251655368566D597133743677397A24432646294A404E635266556A576E5A72347537782141";




    // EXTRACT Claims
    // Claims contains all data about specified JWT Token and User information

    public String extractUsername(String jwtToken) { // Method gets username out of JWT Token
        return extractSingleClaim(jwtToken,Claims::getSubject); // Claims::getSubject takes out the username(email) of  JWT Token
    }



    // Getting single claim from JWT Token
    // Function<Claims, T>
    // Claims = type   ClaimResolver = type of returns
    public <T> T extractSingleClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken); // Getting all claims

        return claimsResolver.apply(claims);  //Return all claims(data) from JWT Token
    }



    // Getting claims(data) about User from JWT Token
    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())    // Signing secret key for JWT Token
                .build()
                .parseClaimsJws(jwtToken)  // decoding Jwt claims(values)
                .getBody();// Extracting all claims in JWT token
    }


    // Decode signed SECRET_KEY
    private Key getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // Decode JWT Token values
        return Keys.hmacShaKeyFor(keyBytes);  // hmacShaKeyFor = algorithm to get Secret Key
    }




//--------------------------------------------------------------------------------------------------------------------------------------------------------------

    // GENERATE TOKENS



    // Generate JWT Token without Extra Claims
    public String generateJwtToken(UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails);
    }



    //Method creates new JWT Token
    // Extra Claims is authorities or extra information to be stored withing JWT Token
    public String generateJwtToken(
            Map<String, Object> extraClaims,  // Contains a map(list) of extra claims(data) about JWT token
            UserDetails userDetails  // User details get a user from database
    ) {
        return Jwts
                //Building new JWT Token
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // Username = email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))  // Token Expire in 24 Hours

                .signWith(getSigningKey(), // Signing with decoded Secret key
                        SignatureAlgorithm.HS256) // Choosing HS256 as signature algorithm
                .compact(); // Created JWT Token
    }



    //---------------------------------------------------------------------------------------------------------------------------------------

    // JWT TOKEN VALIDATION
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {

        final String username = extractUsername(jwtToken); // Take out Username(email) from JWT Token


        // Check if Username(email) within JWT Token matches Username(email) of the request
        // Also check if JWT Token expired or not
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }


    // Token Expired
    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date()); // Checks that JWT Token expired date has passed current date
    }



    private Date extractExpiration(String jwtToken) {
        return extractSingleClaim(jwtToken,Claims::getExpiration); // Gets time left before JWT Token expires

    }

}
