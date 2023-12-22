package businessLogic;

import java.util.List;

import java.util.Date;

import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * Interface that specifies the business logic.
 */

/**
 * @author ioritztubio
 *
 */
public interface BLFacade {

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
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;

	/**
	 * This method retrieves the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date);

	/**
	 * This method calls the data access to initialize the database with some events
	 * and questions. It is invoked only when the option "initialize" is declared in
	 * the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeBD();

	/**
	 * This method verify if the client is login by its user name (u).
	 * 
	 * @param u
	 * @return
	 */
	public boolean isLogin(String u);

	/**
	 * This method registers a new client on the datebase.
	 * 
	 * @param u --> Username
	 * @param p --> Password
	 * @param a --> Admin (True/False)
	 */
	public void register(String u, String p, boolean a);

	/**
	 * This method is used to know user's kind of register.
	 * 
	 * @param username
	 * @return
	 */
	public boolean isAdmin(String u);

	/**
	 * This method verify user logs password.
	 * 
	 * @param userName
	 * @param userPass
	 * @return signIn (true / false)
	 */
	public boolean tryLog(String userName, String userPass);

	/**
	 * This method is used to create an event.
	 * 
	 * @param description
	 * @param eventDate
	 * @throws EventAlreadyExist
	 */
	public boolean createEvent(String u, Date d);

}
