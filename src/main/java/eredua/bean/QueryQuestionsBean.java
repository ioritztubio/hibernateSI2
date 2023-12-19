package eredua.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;

public class QueryQuestionsBean {

	private BLFacade f;
	private List<domain.Question> questions;
	private domain.Event selectedEvent;
	private domain.Question question;

	public QueryQuestionsBean() {
		this.f = FacadeBean.getBusinessLogic();

	}

	public domain.Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(domain.Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public List<domain.Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<domain.Question> questions) {
		this.questions = questions;

	}

	public domain.Question getQuestion() {
		return question;
	}

	public void setQuestion(domain.Question question) {
		this.question = question;

	}

	public void onEventSelect() {
		this.questions = selectedEvent.getQuestions();

	}

}
