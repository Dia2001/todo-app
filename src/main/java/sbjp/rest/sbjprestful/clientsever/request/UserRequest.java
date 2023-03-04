package sbjp.rest.sbjprestful.clientsever.request;

public class UserRequest {
	
	private int id;
	
	private String email;
	
	private String password;
	
	private String role;

	public UserRequest() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRequest [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

	
	
}
