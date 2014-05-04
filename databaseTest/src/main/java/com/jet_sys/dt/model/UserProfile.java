package com.jet_sys.dt.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="user_profile")
@NamedQuery(name="UserProfile.findAll", query="SELECT u FROM UserProfile u")
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id", unique=true, nullable=false, length=20)
	private String userId;

	@Column(name="email_address", nullable=false, length=100)
	private String emailAddress;

	@Column(name="first_name", length=20)
	private String firstName;

	@Column(name="last_name", length=20)
	private String lastName;

	public UserProfile() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}