package eredua.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.catalina.ha.authenticator.ClusterSingleSignOn;
import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;

public class ShowEventsBean {

	private BLFacade f;
	private Date selectedDate;
	private List<domain.Event> eventList;

	public ShowEventsBean() {
		this.f = FacadeBean.getBusinessLogic();
		
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		System.out.println(selectedDate);
		this.selectedDate = selectedDate;
	}
	public List<domain.Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<domain.Event> eventList) {
		this.eventList = eventList;
	}

	public String onDateSelect() {
		System.out.print(this.selectedDate);
		List<domain.Event> events = this.f.getEvents((Date) this.selectedDate);
		System.out.print(events);
		this.setEventList(events);
		
		return "Events";
	
	}
}