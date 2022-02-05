package com.abonnement.bean;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name="login")
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
    private String login;
	
	@Column(name="password")
    private String password;
	
	@Column(name="numero")
    private String numero;
	
	
	
	@Column(name="montant")
	private int montant;
	
	@Column(name="email")
	private String email;
	
	
	
	@Column(name="status")
	private int status;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
  
}
