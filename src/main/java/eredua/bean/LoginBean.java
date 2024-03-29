
package eredua.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;

public class LoginBean {
	private BLFacade f;
	private String clientName;
	private String cPass;
	private String admin;
	private String colorMessages;
	private String logStatus;

	public LoginBean() {
		this.f = FacadeBean.getBusinessLogic();
		this.logStatus = "Main";

	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getColorMessages() {
		return colorMessages;
	}

	public void setColorMessages(String colorMessages) {
		this.colorMessages = colorMessages;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getClientName() {
		System.out.print(clientName);
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String moveToMain() {
		return "Main";
	}

	public String getcPass() {
		System.out.print(cPass);
		return cPass;
	}

	public void setcPass(String cPass) {
		System.out.print(cPass);
		this.cPass = cPass;
	}

	public String moveToRegister() {
		return "Register";
	}

	public String setlogStatus(String logStatus) {
		return this.logStatus = logStatus;
	}
	
	public String logStatus() {
		return this.logStatus;
	}
	
	public String logOut() {
		this.setlogStatus("Main");
		return this.logStatus;
	}

	public String logUser() {

		if (clientName.equals(null) || clientName.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Please, type your user name."));
		} else if (cPass.equals(null) || cPass.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: Please, type your user password."));
			this.setColorMessages("red");

		} else if (!f.isLogin(clientName)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: This username doesn't exists already. Please, sign up."));
			this.setColorMessages("red");
		} else if (!f.tryLog(clientName, cPass)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: User name okay, but password incorrect."));
			this.setColorMessages("red");
		} else {

			if (f.isAdmin(clientName)) {

				this.setlogStatus("LogedAdminMain");
				return "LogedAdminMain";
			} else {
				this.setlogStatus("LogedMain");
				return "LogedMain";
			}
		}
		return "Login";

	}

}
