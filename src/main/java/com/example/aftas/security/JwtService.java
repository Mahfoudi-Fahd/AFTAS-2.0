package com.example.aftas.security;

import com.example.aftas.dto.auth.AuthenticationRequest;
import com.example.aftas.dto.auth.AuthenticationResponse;
import com.example.aftas.dto.auth.RefreshTokenRequest;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.repository.RoleRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {

    private static final String SECRET_KEY = "6A335255515654786E676341565752614A586D423679644E65706F31484E4A6E";
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;

    public JwtService(RoleRepository roleRepository,
                      MemberRepository memberRepository) {
        this.roleRepository = roleRepository;
        this.memberRepository = memberRepository;
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
//                .claim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthenticationResponse refreshToken(String refreshToken) {
        final Claims claims = extractAllClaims(String.valueOf(refreshToken));

        if (!isTokenExpired(String.valueOf(refreshToken))) {
            final String username = extractUserName(String.valueOf(refreshToken));
            UserDetails userDetails = getUserDetailsByUsername(username);

            return AuthenticationResponse.builder()
                    .token(generateToken(userDetails))
                    .refreshToken(generateRefreshToken(userDetails))
                    .build();
        }
        return null;
    }

    private UserDetails getUserDetailsByUsername(String username) {
        return memberRepository.findByEmail(username);
    }
}