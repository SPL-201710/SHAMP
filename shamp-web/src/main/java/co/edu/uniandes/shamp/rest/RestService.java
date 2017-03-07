package co.edu.uniandes.shamp.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.nimbusds.jwt.JWTClaimsSet;

import co.edu.uniandes.shamp.dto.Pageable;
import co.edu.uniandes.shamp.dto.Successful;
import co.edu.uniandes.shamp.util.AuthUtils;
import co.edu.uniandes.shamp.util.exception.BusinessException;
import net.minidev.json.JSONObject;

@Produces({MediaType.APPLICATION_JSON})
public abstract class RestService {

    @Context
    protected HttpHeaders headers;

    @Inject
    protected Logger logger;

    @Context
    protected UriInfo uriInfo;

    public Successful createSuccessful(final String message) {
        final Successful successful = new Successful();
        successful.setMessage(message);
        successful.setTitle(message);
        return successful;
    }

    public Successful createSuccessful(final String message, final Object data) {
        final Successful successful = new Successful();
        successful.setMessage(message);
        successful.setTitle(message);
        successful.setData(data);
        return successful;
    }

    public Successful createSuccessful(final String message, final String title) {
        final Successful successful = new Successful();
        successful.setMessage(message);
        successful.setTitle(title);
        return successful;
    }

    public Successful createSuccessful(final String message, final String title, final Object data) {
        final Successful successful = new Successful();
        successful.setMessage(message);
        successful.setTitle(title);
        successful.setData(data);
        return successful;
    }

    protected Pageable getPageable(final MultivaluedMap<String, String> queryParameters) {
        Integer page = 0;
        Integer size = 10;
        if (queryParameters.containsKey("page")) {
            page = Integer.parseInt(queryParameters.getFirst("page"));
        }
        if (queryParameters.containsKey("size")) {
            size = Integer.parseInt(queryParameters.getFirst("size"));
        }
        final Pageable pageable = new Pageable(page, size);
        return pageable;
    }

    protected Long getTenandId() throws BusinessException {
        String authorization = this.headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        final JWTClaimsSet claimSet = AuthUtils.decodeToken(authorization);
        JSONObject user = (JSONObject) claimSet.getClaim("user");
        return (Long) user.get("tenantId");
    }

}


