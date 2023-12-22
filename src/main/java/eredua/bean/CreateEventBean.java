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

public class CreateEventBean {

	private BLFacade f;
	private String eventDescription;

	private Date selectedDate;

	private String colorMessages;

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getColorMessages() {
		return colorMessages;
	}

	public void setColorMessages(String colorMessages) {
		this.colorMessages = colorMessages;
	}

	public CreateEventBean() {
		this.f = FacadeBean.getBusinessLogic();

	}

	public void createNewEvent() {
		System.out.println(this.eventDescription);
		Date dataGaur = new Date();
		if (this.eventDescription.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: You should type a description."));
			this.setColorMessages("red");

		} else if (this.selectedDate == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: Please, select a date by clicking on it."));
			this.setColorMessages("red");

		} else if (selectedDate.compareTo(dataGaur) < 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: You should select a date from tomorrow onwards."));
			this.setColorMessages("red");
		} else {

			boolean create = f.createEvent(this.eventDescription, this.selectedDate);
			if (create) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Event created successfully!"));
				this.setColorMessages("green");

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error: Question is already created."));
				this.setColorMessages("red");
			}

		}
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

}
