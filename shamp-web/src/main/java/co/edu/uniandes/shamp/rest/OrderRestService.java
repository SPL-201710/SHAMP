package co.edu.uniandes.shamp.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.edu.uniandes.shamp.dto.OrderDto;
import co.edu.uniandes.shamp.dto.Successful;
import co.edu.uniandes.shamp.service.OrderService;
import co.edu.uniandes.shamp.util.exception.BusinessException;
import co.edu.uniandes.shamp.util.exception.SystemException;

@Path("/order")
@RequestScoped
public class OrderRestService extends RestService {

  @Inject
  private Logger logger;

  @Inject
  private OrderService service;


  @GET
  @Path("/{user_id}")
  public Response get(@PathParam("user_id") final int user_id) throws BusinessException {
    try {
      final List<OrderDto> orderDto = this.service.getOrderByuserId(user_id);
      final Successful successful = this.createSuccessful("", orderDto);
      final Response response = Response.status(Status.CREATED).entity(successful).build();
      return response;
    } catch (BusinessException | SystemException ex) {
      throw ex;
    } catch (final Exception ex) {
      this.logger.log(Level.SEVERE, ex.getMessage(), ex);
      throw new SystemException();
    }
  }

  @POST
  @PermitAll
  @Path("/register")
  public Response register(@NotNull final OrderDto orderDto) throws BusinessException {
    try {
      final Successful successful = this.createSuccessful("", this.service.register(orderDto));;
      successful.setMessage("Order created");
      successful.setTitle("Order created");
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

