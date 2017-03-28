package co.edu.uniandes.shamp.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.edu.uniandes.shamp.dto.Successful;
import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.service.ShirtService;
import co.edu.uniandes.shamp.util.exception.BusinessException;
import co.edu.uniandes.shamp.util.exception.SystemException;

@Path("/order")
@RequestScoped
public class OrderRestService extends RestService {

  @Inject
  private Logger logger;

  @Inject
  private ShirtService service;

  @GET
  public Response get() {
    try {
      final Successful successful = this.createSuccessful("", this.service.getAll());
      final Response response = Response.status(Status.CREATED).entity(successful).build();
      return response;
    } catch (final SystemException ex) {
      throw ex;
    } catch (final Exception ex) {
      this.logger.log(Level.SEVERE, ex.getMessage(), ex);
      throw new SystemException();
    }
  }

  @POST
  @PermitAll
  @Path("/register")
  public Response register(@Valid @NotNull final User user) throws BusinessException {
    try {
      this.userService.register(user);
      final Successful successful = new Successful();
      successful.setMessage("User created");
      successful.setTitle("User created");
      final Response response = Response.status(Status.OK).entity(successful).build();
      return response;
    } catch (BusinessException | SystemException ex) {
      throw ex;
    } catch (final Exception ex) {
      this.logger.log(Level.SEVERE, ex.getMessage(), ex);
      throw new SystemException();
    }
  }

}

