package dataAccess;

import java.io.File;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.*;
import eredua.HibernateUtil;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectsession database
 */
public class DataAccess {

	private Query setParameter;

	public DataAccess() {
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			Client c1 = new Client("client1", "pss", false);

			session.persist(c1);

			Event ev1 = new Event("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			session.persist(q1);
			session.persist(q2);
			session.persist(q3);
			session.persist(q4);
			session.persist(q5);
			session.persist(q6);

			session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);

			session.getTransaction().commit();

			System.out.println("session initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);
		System.out.println(session + " " + event);

		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber = :evN");
		query.setParameter("evN", event.getEventNumber());
		Event ev = (Event) query.uniqueResult();

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist("ErrorQueryAlreadyExist");/*
																		 * ResourceBundle.getBundle("Etiquetas").
																		 * getString("ErrorQueryAlreadyExist"));
																		 */

		session.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// session.persist(q);
		session.persist(ev); // session.persist(q) not required when CascadeType.PERSIST is added in
								// questions
		// property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(">> DataAccess: getEvents");
		List<Event> res = new ArrayList<Event>();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate= :data");
		query.setParameter("data", date);
		List<Event> events = query.list();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		Query query = session.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :startDate AND :endDate");
		query.setParameter("startDate", firstDayMonthDate);
		query.setParameter("endDate", lastDayMonthDate);
		List<Date> dates = query.list();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public boolean existQuestion(Event event, String question) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Query q = session.createQuery("SELECT DISTINCT ev FROM Event ev WHERE ev.eventNumber=:evNumber");
		q.setParameter("evNumber", event.getEventNumber());
		Event ev = (Event) q.uniqueResult();
		return ev.DoesQuestionExists(question);

	}

	/**
	 * This method helps to know if that userName has been loged before.
	 * 
	 * @param userName
	 * @param password
	 * @return true if it's in the data base.
	 */
	public boolean isLogin(String userName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean found = false;
		Query query = session.createQuery("SELECT c.userName FROM Client c WHERE c.userName= :uName");
		query.setParameter("uName", userName);
		if (query.uniqueResult() != null) {
			found = true;
		}

		session.getTransaction();
		return found;
	}

	/**
	 * This method is used to know user's kind of register.
	 * 
	 * @param userName
	 * @return isAdmin (true / false)
	 */
	public boolean isAdmin(String userName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean admin = false;
		Query query = session.createQuery("SELECT c.admin FROM Client c WHERE c.userName= :uName");
		query.setParameter("uName", userName);
		admin = (Boolean) query.uniqueResult();
		return admin;
	}

	/**
	 * This method verify user logs password.
	 * 
	 * @param userName
	 * @param userPass
	 * @return signIn (true / false)
	 */
	public boolean tryLog(String userName, String userPass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		boolean signIn = false;
		Query query = session.createQuery("SELECT c.password FROM Client c WHERE c.userName = :uName");
		query.setParameter("uName", userName);
		System.out.println("0" + userName);
		System.out.println("1" + userPass);
		System.out.println("2" + (String) query.uniqueResult());
		if (userPass.equals((String) query.uniqueResult())) {
			System.out.println((String) query.uniqueResult());
			signIn = true;
			return signIn;
		}
		return signIn;
	}

	/**
	 * This method is used to create an event.
	 * 
	 * @param description
	 * @param eventDate
	 * @throws EventAlreadyExist
	 */
	public boolean createEvent(String description, Date eventDate) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT e FROM Event e WHERE e.description= :desc AND e.eventDate= :date");
		query.setParameter("desc", description);
		query.setParameter("date", eventDate);
		System.out.println(query.uniqueResult());
		if ((query.uniqueResult() == null)) {

			Event berria = new Event(description, eventDate);
			session.getTransaction().begin();
			session.persist(berria);
			session.getTransaction().commit();
			return true;
		} else {

			System.out.println("Gertaera sortuta bazegoen");
			return false;
		}

	}

	/**
	 * This method is used to register people in the data base.
	 * 
	 * @param userName
	 * @param password
	 */
	public void register(String userName, String password, boolean admin) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Client c = new Client(userName, password, admin);
		System.out.print(userName);

		try {

			session.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.getTransaction().commit();

	}

	public void close() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.close();
		System.out.println("DataBase closed");
	}

}
