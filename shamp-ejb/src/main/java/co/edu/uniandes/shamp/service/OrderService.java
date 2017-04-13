package co.edu.uniandes.shamp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.data.OrderStampShirtRepository;
import co.edu.uniandes.shamp.data.ShampRepository;
import co.edu.uniandes.shamp.data.ShirtRepository;
import co.edu.uniandes.shamp.data.StampShirtRepository;
import co.edu.uniandes.shamp.data.UserOrderRepository;
import co.edu.uniandes.shamp.data.UserRepository;
import co.edu.uniandes.shamp.dto.DetailOrderDto;
import co.edu.uniandes.shamp.dto.OrderDto;
import co.edu.uniandes.shamp.dto.ShirtDto;
import co.edu.uniandes.shamp.model.OrderShirt;
import co.edu.uniandes.shamp.model.Shirt;
import co.edu.uniandes.shamp.model.Stamp;
import co.edu.uniandes.shamp.model.StampShirt;
import co.edu.uniandes.shamp.model.User;
import co.edu.uniandes.shamp.model.UserOrder;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class OrderService {

  @Inject
  private Logger loggger;

  @Inject
  private OrderStampShirtRepository orderStampShirtRepository;

  @Inject
  private StampShirtRepository repository;

  @Inject
  private ShampRepository shampRepository;

  @Inject
  private ShirtRepository shirtRepository;

  @Inject
  private UserOrderRepository userOrderRepository;

  @Inject
  private UserRepository userRepository;

  public List<OrderDto> getOrderByuserId(final int user_id) throws BusinessException {

    final List<OrderDto> listOrderDto = new ArrayList<OrderDto>();
    final List<UserOrder> listUserOrder = this.userOrderRepository.findByUserId(user_id);

    for (final UserOrder userOrder : listUserOrder) {
      final OrderDto orderDto = new OrderDto();
      orderDto.setId(userOrder.getId());
      orderDto.setUser_id(user_id);

      final DetailOrderDto detailOrderDto = new DetailOrderDto();
      detailOrderDto.setCity(userOrder.getCity());
      detailOrderDto.setContact_phone(userOrder.getContactPhone());
      detailOrderDto.setDelivery_address(userOrder.getDeliveryAddress());
      detailOrderDto.setCountry(userOrder.getCountry());

      orderDto.setOrder(detailOrderDto);

      final List<OrderShirt> listProduct =
          this.orderStampShirtRepository.findOrderStampByUserOrderId(userOrder.getId());

      final List<ShirtDto> products = new ArrayList<ShirtDto>();

      for (final OrderShirt product : listProduct) {

        final StampShirt stampShirt =
            this.orderStampShirtRepository.findStampShirtById(product.getStampShirt().getId());

        final ShirtDto shirtDto = new ShirtDto();
        shirtDto.setQuantity(product.getShirtQuantity().intValue());
        shirtDto.setLocation(stampShirt.getStampLocation());
        shirtDto.setShirt_id(stampShirt.getShirt().getId());
        shirtDto.setStamp_id(stampShirt.getStamp().getId());
        shirtDto.setSize(stampShirt.getStampSize());

        products.add(shirtDto);
        orderDto.setProducts(products);
      }


      listOrderDto.add(orderDto);
    }
    return listOrderDto;
  }

  public UserOrder register(final OrderDto orderDto) throws BusinessException {

    final User user = this.userRepository.findId(orderDto.getUser_id());

    final UserOrder userOrder = this.setterUserOrder(orderDto, user);
    this.userOrderRepository.persist(userOrder);

    for (final ShirtDto shirtDto : orderDto.getProducts()) {
      final StampShirt stampShirt = this.setterStampShirt(orderDto, shirtDto);
      this.repository.persist(stampShirt);

      final OrderShirt orderShirt =
          this.setterOrderShirt(orderDto, userOrder, shirtDto, stampShirt);
      this.orderStampShirtRepository.persist(orderShirt);
    }
    return userOrder;
  }

  private OrderShirt setterOrderShirt(final OrderDto orderDto, final UserOrder userOrder,
      final ShirtDto shirtDto, final StampShirt stampShirt) {
    final OrderShirt orderShirt = new OrderShirt();
    orderShirt.setActive(true);
    orderShirt.setCreationDate(new Date());
    orderShirt.setShirtQuantity((double) shirtDto.getQuantity());
    orderShirt.setName(String.valueOf(orderDto.getUser_id()) + shirtDto.getLocation());
    orderShirt.setOrder(userOrder);
    orderShirt.setStampShirt(stampShirt);
    return orderShirt;
  }

  private StampShirt setterStampShirt(final OrderDto orderDto, final ShirtDto shirtDto) {
    final Shirt shirt = this.shirtRepository.findId(shirtDto.getShirt_id());
    final Stamp stamp = this.shampRepository.findId(shirtDto.getStamp_id());
    final StampShirt stampShirt = new StampShirt();
    stampShirt.setActive(true);
    stampShirt.setCreationDate(new Date());
    stampShirt.setName(String.valueOf(orderDto.getUser_id()));
    stampShirt.setStampLocation(shirtDto.getLocation());
    stampShirt.setStampSize(shirtDto.getSize());
    stampShirt.setStamp(stamp);
    stampShirt.setShirt(shirt);
    return stampShirt;
  }

  private UserOrder setterUserOrder(final OrderDto orderDto, final User user) {
    final UserOrder userOrder = new UserOrder();
    userOrder.setActive(true);
    userOrder.setCreationDate(new Date());
    userOrder.setName(String.valueOf(orderDto.getUser_id()));
    userOrder.setOrderStatus("En carro de compras");
    userOrder.setUser(user);
    userOrder.setContactPhone(orderDto.getOrder().getContact_phone());
    userOrder.setCountry(orderDto.getOrder().getCountry());
    userOrder.setDeliveryAddress(orderDto.getOrder().getDelivery_address());
    userOrder.setCity(orderDto.getOrder().getCity());
    return userOrder;
  }



}
