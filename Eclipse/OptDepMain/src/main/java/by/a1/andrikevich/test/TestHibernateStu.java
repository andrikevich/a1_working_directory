package by.a1.andrikevich.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import by.a1.andrikevich.entity.SimCard;
import by.a1.andrikevich.entity.Stu;

public class TestHibernateStu {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Stu.class)
								.addAnnotatedClass(SimCard.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			Stu tempStu = session.get(Stu.class, 2400);
			
			Query<Stu>  tmpQuery = session.createQuery("from Stu where id=:stuId");
			tmpQuery.setParameter("stuId", 2401);
			List <Stu> tmpListStu = tmpQuery.getResultList();
			System.out.println("====>>> STU: " + tempStu);
			//System.out.println("====>>> simCards: " + tempStu.getSimCards());
			System.out.println("\n<<<====>>>   query list"  + tmpListStu);
			
			session.getTransaction().commit();
			
		}
		
		finally {
			session.close();
			factory.close();
		}
		
	}

}
