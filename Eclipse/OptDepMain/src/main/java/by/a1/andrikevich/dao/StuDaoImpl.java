package by.a1.andrikevich.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.a1.andrikevich.entity.Stu;

@Repository
public class StuDaoImpl implements StuDao {

	@Autowired
	SessionFactory factory;

	@Override
	public List<Stu> findAll() {
		Session session = factory.getCurrentSession();
		Query<Stu> query = session.createQuery("from Stu",Stu.class);
		List <Stu> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Stu findById(int theId) {
		Session session = factory.getCurrentSession();
		Stu resultStu = session.get(Stu.class, theId);
		return resultStu;
	}

	@Override
	public void save(Stu theStu) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theStu);
	}

	@Override
	public void deleteById(int theId) {
		Session session = factory.getCurrentSession();
		Query<Stu> query = session.createQuery("delete from Stu where id=:tmpId",Stu.class);
		query.setParameter("tmpId", theId);
		query.executeUpdate();

	}

	@Override
	public List<Stu> findByCity(String city) {
		Session session = factory.getCurrentSession();
		Query<Stu> query = session.createQuery("from Stu where city=:tmpCity", Stu.class);
		query.setParameter("tmpCity", city);
		List <Stu> result = query.getResultList();
		System.out.println(result);
		return result;
	}

}
