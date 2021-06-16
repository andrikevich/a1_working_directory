package by.a1.andrikevich.dao;

import by.a1.andrikevich.entity.SimCard;

import java.util.List;

public interface SimCardDao {

	SimCard findById(String theId);

	SimCard findByIccid(String theId);

	SimCard findByMsIsdn(String msIsdn);

	void save(SimCard simcard);

	List<SimCard> findAllSimsByParam(String param);
}