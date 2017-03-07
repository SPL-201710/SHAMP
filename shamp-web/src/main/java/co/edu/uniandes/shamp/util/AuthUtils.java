package co.edu.uniandes.shamp.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import co.edu.uniandes.shamp.dto.Session;
import co.edu.uniandes.shamp.util.exception.BusinessException;
import co.edu.uniandes.shamp.util.exception.SystemException;

public class AuthUtils {

    public static final String AUTH_HEADER_KEY = "Authorization";
    private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
    private static final String TOKEN_SECRET = UUID.randomUUID().toString();

    public static void createToken(String host, Session session) throws JOSEException {
        final LocalDateTime now = LocalDateTime.now();
        final Date expirationTime =
                Date.from(now.plusMinutes(20).atZone(ZoneId.systemDefault()).toInstant());
        final JWTClaimsSet claim =
                new JWTClaimsSet.Builder().subject(session.getUser().getUsername()).issuer(host)
                        .issueTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                        .expirationTime(expirationTime).claim("user", session.getUser()).build();
        final JWSSigner signer = new MACSigner(TOKEN_SECRET);
        final SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
        jwt.sign(signer);
        session.setToken(jwt.serialize());
    }

    public static JWTClaimsSet decodeToken(String authHeader) throws BusinessException {
        try {
            final SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
            if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
                return signedJWT.getJWTClaimsSet();
            } else {
                throw new BusinessException("Signature verification failed",
                        Status.UNAUTHORIZED.name());
            }
        } catch (final BusinessException be) {
            throw be;
        } catch (final Exception e) {
            throw new SystemException("Signature verification failed");
        }
    }

    public static String getSerializedToken(String authHeader) {
        return authHeader.split(" ")[1];
    }

    public static String getSubject(String authHeader) throws BusinessException {
        return AuthUtils.decodeToken(authHeader).getSubject();
    }

}