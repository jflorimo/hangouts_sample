package com.jflorimo.ft_hangouts;

/**
 * Created by jflorimo on 05/10/15.
 */
public class Contact {
	private int color;
	private int id;
	private String login;
	private String number;
	private String email;
	private String adress;

	public Contact(int color, String login, String number, String email, String adress)
	{
		this.color = color;
		this.login = login;
		this.number = number;
		this.email = email;
		this.adress = adress;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String toString()
	{
		return "id:"+ id + " login:" + login + " number:" + number + " email:" + email + " adress:" + adress;
	}
}
