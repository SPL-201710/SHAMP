package co.edu.uniandes.shamp.service;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.uniandes.shamp.data.ShampRepository;
import co.edu.uniandes.shamp.model.Stamp;
import co.edu.uniandes.shamp.util.exception.BusinessException;

@Stateless
public class StampService {

  @Inject
  private Logger loggger;

  @Inject
  private ShampRepository repository;

  public List<Stamp> getAll() throws BusinessException {
    this.loggger.info("mostrar todas las stamp ");
    List<Stamp> listStamp = new ArrayList<Stamp>();
    listStamp = this.repository.findAll();
    return listStamp;
  }

}
