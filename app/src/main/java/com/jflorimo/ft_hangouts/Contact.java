package com.jflorimo.ft_hangouts;

/**
 * Created by jflorimo on 05/10/15.
 */
public class Contact {
	private int color;
	private String login;
	private String text;

	public Contact(int color, String login, String text)
	{
		this.color = color;
		this.login = login;
		this.text = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
