package com.abonnement.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agent")
public class LoginAgent implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="usernameAgent")
    private String loginAgent;
	
	@Column(name="passwordAgent")
    private String passwordAgent;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return loginAgent;
	}
	public void setLogin(String login) {
		this.loginAgent = login;
	}
	public String getPassword() {
		return passwordAgent;
	}
	public void setPassword(String password) {
		this.passwordAgent = password;
	}
	
    
}
