package by.a1.andrikevich.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.a1.andrikevich.entity.SimCard;
import by.a1.andrikevich.util.JspPageUtilities;

@Repository
public class SimCardDaoImpl implements SimCardDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public SimCard findById(String theId) {
		Session session = sessionFactory.getCurrentSession();
		SimCard theSimCard = session.get(SimCard.class, theId);
		return theSimCard;
	}


	@Override
	public SimCard findByMsIsdn(String theId) {
		Session session = sessionFactory.getCurrentSession();
		Query <SimCard> query = session.createQuery("from SimCard where msisdn like :msisdn",SimCard.class);
		query.setParameter("msisdn", "%" + theId + "%");
		SimCard theSimCard = null;
		try {
				theSimCard = (SimCard) query.getSingleResult();
		}
		//for simCard not in myDB, it will be returned some abstract simcard
		catch (NoResultException e) {
			theSimCard = new SimCard ("Isn'tInMyDB", "Isn'tInMyDB", "Isn'tInMyDB", "Isn'tInMyDB", "Isn'tInMyDB", "Isn'tInMyDB");
		}
		return theSimCard;
	}
	@Override
	public SimCard findByIccid(String theId) {
		Session session = sessionFactory.getCurrentSession();
		Query <SimCard> query = session.createQuery("from SimCard where iccid like :iccid",SimCard.class);
		query.setParameter("iccid",  "%" + theId + "%");
		SimCard theSimCard = (SimCard) query.getSingleResult();
		return theSimCard;
	}


	@Override
	public void save(SimCard theSimCard) {
		Session session = sessionFactory.getCurrentSession();
		JspPageUtilities.simCardFieldEncoding(theSimCard);
		session.saveOrUpdate(theSimCard);
		
	}





}
