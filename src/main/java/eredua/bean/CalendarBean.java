package eredua.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;


import businessLogic.BLFacade;

public class CalendarBean {

	private BLFacade f = FacadeBean.getBusinessLogic();
	private Date selectedDate;
	private List<domain.Event> eventList;

	public CalendarBean() {
		this.eventList = new ArrayList<domain.Event>();
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<domain.Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<domain.Event> eventList) {
		this.eventList = eventList;
	}

	public void onDateSelect() {
		System.out.print(this.selectedDate);
		List<domain.Event> events = this.f.getEvents((Date) this.selectedDate);
		this.setEventList(events);
	
	}

}
