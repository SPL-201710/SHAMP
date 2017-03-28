package co.edu.uniandes.shamp.service;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.data.ShirtRepository;
import co.edu.uniandes.shamp.model.Shirt;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class ShirtService {

  @Inject
  private Logger loggger;

  @Inject
  private ShirtRepository repository;

  public List<Shirt> getAll() throws BusinessException {
    this.loggger.info("mostrar todas las shirt");
    List<Shirt> listShirt = new ArrayList<Shirt>();
    listShirt = this.repository.findAll();
    return listShirt;
  }

}
