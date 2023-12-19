package eredua.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MainBean {
	public String moveToQuery() {
		return "QueryQuestions";
	}
	
	public String moveToCreate() {
		return "CreateQuestions";
	}
	
	public String moveToLogin() {
		return "Login";
	}
}
