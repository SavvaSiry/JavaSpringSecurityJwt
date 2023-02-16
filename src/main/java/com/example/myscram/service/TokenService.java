//package com.example.myscram.service;
//
//import com.example.myscram.entity.User;
//import com.example.myscram.model.TokenDto;
//import org.springframework.stereotype.Service;
//
//import java.text.ParseException;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//
//@Service
//public class TokenService {
//
//    private final JwtEncoder jwtEncoder;
//
//    public TokenService(JwtEncoder encoder) {
//        this.jwtEncoder = encoder;
//    }
//
//    public TokenDto generateToken(User usrDetails) {
//        return new TokenDto(generateAccessToken(usrDetails), generateRefreshToken(usrDetails));
//    }
//
//    public String generateAccessToken(User usrDetails) {
//        Instant now = Instant.now();
//
////        String scope = usrDetails.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.joining(" "));
//
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(now)
//                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
//                .subject(usrDetails.getUsername())
////                .claim("scope", scope)
//                .build();
//
//        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }
//
//    public String generateRefreshToken(User usrDetails) {
//        Instant now = Instant.now();
//
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(now)
//                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
//                .subject(usrDetails.getUsername())
//                .build();
//
//        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }
//
//    public String parseToken(String token) {
//        try {
//            SignedJWT decodedJWT = SignedJWT.parse(token);
//            String subject = decodedJWT.getJWTClaimsSet().getSubject();
//            return subject;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
