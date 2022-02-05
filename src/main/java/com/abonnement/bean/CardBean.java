package com.abonnement.bean;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.*;



@Entity
@Table(name="cards")
public class CardBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="numeroCarte")
    private String numeroCarte;
    
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	@Column(name="DernierChiff")
	private String DernierChiff;
	
	@Column(name="Solde")
	private int Solde;
	
	public int getSolde() {
		return Solde;
	}

	public void setSolde(int solde) {
		Solde = solde;
	}

		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDernierChiff() {
		return DernierChiff;
	}

	public void setDernierChiff(String dernierChiff) {
		DernierChiff = dernierChiff;
	}
}
