package by.a1.andrikevich.service;

import java.util.List;

import by.a1.andrikevich.entity.SimCard;



public interface SimCardService {
	
	public List<SimCard> findAll();

	public SimCard findById(String theId);

	public void save(SimCard theSimCard);

	public void deleteById(int theId);
	
	
	public SimCard findByIccid(String theId);
	public SimCard findByMsIsdn(String theId);


	
	
}
