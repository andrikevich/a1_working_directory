package by.a1.andrikevich.service;

import java.util.List;

import by.a1.andrikevich.exception.NoSuchSimCardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.a1.andrikevich.dao.SimCardDao;
import by.a1.andrikevich.entity.SimCard;

@Service
public class SimCardServiceImpl implements SimCardService {

	private SimCardDao simCardDao;


	@Autowired
	public SimCardServiceImpl(SimCardDao simCardDao) {
		this.simCardDao = simCardDao;
	}

	@Override
	@Transactional
	public List<SimCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<SimCard> findAllByParam(String param) {

		List<SimCard> allSimsByParam = simCardDao.findAllSimsByParam(param);
		return allSimsByParam;
	}

	@Override
	@Transactional
	public SimCard findById(String theId) {
		SimCard simCard = simCardDao.findById(theId);
		return simCard;
	}

	@Override
	@Transactional
	public void save(SimCard theSimCard) {
		simCardDao.save(theSimCard);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public SimCard findByIccid(String theId) {
		SimCard simCard = simCardDao.findByIccid(theId);
		return simCard;
	}

	@Override
	@Transactional
	public SimCard findByMsIsdn(String theId) {
		SimCard simCard = simCardDao.findByMsIsdn(theId);
		return simCard;
	}
}