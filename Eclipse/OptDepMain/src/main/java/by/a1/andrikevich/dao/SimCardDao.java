package by.a1.andrikevich.dao;

import by.a1.andrikevich.entity.SimCard;

public interface SimCardDao {

	SimCard findById(String theId);
	
	SimCard findByIccid (String theId);
	
	SimCard findByMsIsdn (String msIsdn);

	void save(SimCard theSimCard);

	

}
