package businessLogic;

//hola
import java.util.Date;
import java.util.ResourceBundle;

import org.hibernate.cfg.Configuration;

import java.util.List;

import dataAccess.DataAccess;

import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */

public class BLFacadeImplementation implements BLFacade {
	private DataAccess dbManager;

	public BLFacadeImplementation() {
		dbManager = new DataAccess();
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		String hbm = config.getProperty("hibernate.hbm2ddl.auto");
		
		if("create".equals(hbm)) {
			dbManager.initializeDB();
		}else {
			System.out.println("DB IS OPEN");
		}
		
		dbManager.close();
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */

	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */

	public List<Event> getEvents(Date date) {
		List<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		List<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		// DataAccess dB4oManager=new DataAccess(false);

		// dB4oManager.close();
		dbManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */

	public void initializeBD() {
		dbManager.initializeDB();
		dbManager.close();
	}
	
	public boolean isLogin(String u) {
		boolean loged = dbManager.isLogin(u);
		dbManager.close();
		return loged;
		
	}

	public void register(String u, String p, boolean a) {
		dbManager.register(u, p, a);
		dbManager.close();
		
	}
	
	public boolean isAdmin(String u) {
		boolean isAdmin = dbManager.isAdmin(u);
		dbManager.close();
		return isAdmin;
		
		
	}
	
	/**
	 * This method verify user logs password.
	 * @param userName
	 * @param userPass
	 * @return signIn (true / false)
	 */
	public boolean tryLog (String userName, String userPass) {
		boolean login = dbManager.tryLog(userName, userPass);
		dbManager.close();
		return login;
	}
	
	public boolean createEvent(String u, Date d) {
		boolean create = dbManager.createEvent(u, d);
		dbManager.close();
		return create;
	}
}
