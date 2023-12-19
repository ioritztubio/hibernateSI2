package eredua.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {

	private BLFacade f;
	private List<domain.Question> questions;
	private domain.Event selectedEvent;
	private domain.Question question;
	private String questionDescription;
	private float minimumBet;

	public CreateQuestionBean() {
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

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public float getMinimumBet() {
		return minimumBet;
	}

	public void setMinimumBet(float minimumBet) {
		this.minimumBet = minimumBet;
	}

	public void createNewQuestion() {

		if (selectedEvent == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You must select an Event."));
		} else if (this.questionDescription.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You must type a description."));
		} else if (this.minimumBet <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: Minimum bet should be higher than 0."));
		} else {
			BLFacade facadeBL = FacadeBean.getBusinessLogic();
			try {
				int i = 0;
				List<Question> evQuestions = selectedEvent.getQuestions();
				boolean create = true;
				while(i<evQuestions.size() && create){
					if(evQuestions.get(i).getQuestion().equals(this.questionDescription)) {
						create = false;
					}
					i++;
				}
				if(create)
				facadeBL.createQuestion(this.selectedEvent, this.questionDescription, this.minimumBet);
				else
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Question is already created."));
			} catch (EventFinished e) {
				e.printStackTrace();
			} catch (QuestionAlreadyExist e) {
				e.printStackTrace();
			}

		}
	}
	public void onEventSelect() {
		this.questions = selectedEvent.getQuestions();

	}

}
