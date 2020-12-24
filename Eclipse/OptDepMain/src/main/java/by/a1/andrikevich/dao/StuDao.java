package by.a1.andrikevich.dao;

import java.util.List;

import by.a1.andrikevich.entity.Stu;

public interface StuDao {

	public List<Stu> findAll();

	public Stu findById(int theId);

	public void save(Stu theStu);

	public void deleteById(int theId);

	public List<Stu> findByCity(String city);
	
	

}
