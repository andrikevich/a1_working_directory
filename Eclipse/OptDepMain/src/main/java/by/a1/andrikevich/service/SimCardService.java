package by.a1.andrikevich.service;

import java.util.List;

import by.a1.andrikevich.entity.SimCard;
import by.a1.andrikevich.exception.NoSuchSimCardException;


public interface SimCardService {

	public List<SimCard> findAll();

	public List<SimCard> findAllByParam(String param);

	public SimCard findById(String theId);

	public void save(SimCard theSimCard);

	public void deleteById(int theId);

	SimCard findByMsIsdn(String msisdn);

	SimCard findByIccid(String iccid);
}