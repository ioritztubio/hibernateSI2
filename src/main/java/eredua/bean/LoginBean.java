package eredua.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean {
	
	public String moveToMain(){
		return "Main";
	}

}
