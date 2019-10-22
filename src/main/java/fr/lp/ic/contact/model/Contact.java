package fr.lp.ic.contact.model;

@SuppressWarnings("ALL")
public class Contact {

	@SuppressWarnings("checkstyle:FileTabCharacter")
	private String name;
	private String phone;
	private String email;
	
	public String getName() {
		return name;
	}
	@SuppressWarnings("CheckStyle")
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
