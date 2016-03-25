package br.com.sidoc.model;

public class Contato {
	private long id;
	private String email;
	private String telefoneCom;
	private String telefoneRes;
	private String celular;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefoneCom() {
		return telefoneCom;
	}
	public void setTelefoneCom(String telefoneCom) {
		this.telefoneCom = telefoneCom;
	}
	public String getTelefoneRes() {
		return telefoneRes;
	}
	public void setTelefoneRes(String telefoneRes) {
		this.telefoneRes = telefoneRes;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
}
