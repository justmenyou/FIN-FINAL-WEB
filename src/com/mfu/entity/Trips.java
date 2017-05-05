package com.mfu.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Trips implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	private String date;
	private String userKey;
	private String suggestedTripsKey;
	private String note;

	public String getKeyString() {
		return KeyFactory.keyToString(key);
	}

	public void setKeyString(String key) {
		this.key = KeyFactory.stringToKey(key);

	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getSuggestedTripsKey() {
		return suggestedTripsKey;
	}

	public void setSuggestedTripsKey(String suggestedTripsKey) {
		this.suggestedTripsKey = suggestedTripsKey;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
