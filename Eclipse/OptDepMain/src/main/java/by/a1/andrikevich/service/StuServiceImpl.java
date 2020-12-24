package by.a1.andrikevich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.a1.andrikevich.dao.StuDao;
import by.a1.andrikevich.entity.Stu;

@Service
public class StuServiceImpl implements StuService {
	
	private StuDao stuDao;
	
	
	@Autowired
	public StuServiceImpl(StuDao stuDao) {
		this.stuDao = stuDao;
	}

	@Override
	@Transactional
	public List<Stu> findAll() {
		return stuDao.findAll();
	}

	@Override
	@Transactional
	public Stu findById(int theId) {
		return stuDao.findById(theId);
	}

	@Override
	@Transactional
	public void save(Stu theStu) {
		stuDao.save(theStu);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		stuDao.deleteById(theId);

	}

	@Override
	@Transactional
	public List<Stu> findByCity(String city) {
		
		return stuDao.findByCity(city);
	}

}
