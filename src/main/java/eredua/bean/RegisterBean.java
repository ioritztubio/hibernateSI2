package eredua.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;

public class RegisterBean {
	private BLFacade f;
	private String clientName;
	private String cPass;
	private String admin;
	private String colorMessages;
	
	public String getColorMessages() {
		return colorMessages;
	}

	public void setColorMessages(String colorMessages) {
		this.colorMessages = colorMessages;
	}

	private boolean mode;

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

	public RegisterBean() {
		this.f = FacadeBean.getBusinessLogic();

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

	public String registerUser() {

		if (clientName.equals(null) || clientName.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error: Please, type your user name."));
			this.setColorMessages("red");
		} else if (cPass.equals(null) || cPass.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: Please, type your user password."));
			this.setColorMessages("red");
		} else if (admin.equals("select")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: Please, select your type of register."));
			this.setColorMessages("red");
		} else if (f.isLogin(clientName)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error: This username already exists."));
			this.setColorMessages("red");
		} else {
			if (admin.equals("admin")) {
				this.mode = true;
			} else {
				this.mode = false;
			}

			System.out.println("ASDFASDFASDFASDFASDf" + clientName + " " + cPass);
			f.register(clientName, cPass, mode);

			System.out.println(mode);
			if (mode) {
				System.out.println("LogedAdminMain");
				return "LogedAdminMain";
			} else {
				return "LogedMain";
			}
		}
		return "Register";

	}
}
