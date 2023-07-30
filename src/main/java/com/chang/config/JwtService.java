package com.chang.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String SECRET_KEY = "Kk8aXlk3ww5sLeM9+L68DhcOutT06C0V1lIDM+JV7/XlZH2ztz7DMoWb2mBVsowGkR9Vs4iDIdTcC+U4ujWowYPjI1VjMZWR9KSpYl89mU83JCtwO/YHGI8k/Pz99R03bEDcBTf+pt1l2mo24FY7By2tn/YsEFNvSyMKpjVPtcDhzItN2rClwf/qfojdW2ojkYVJkqY1ckMsD7tZgXJX5wTIdYHaE9kgPerNOVqUHBatIY0p21l1rHTo8kVgWYQd0Jjkl2ieRCmmjhLg4u84dfKGGiubinf7wXQrmuO2TwuM0Hw/KnZDTJggtLjYRYxPYgDWJPHeYteZSlMib/KwJJxSkkBugGl04zadvCZ77b0=";




    public String getEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

}
