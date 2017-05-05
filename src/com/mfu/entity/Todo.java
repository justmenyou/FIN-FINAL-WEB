package com.mfu.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Todo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	private String name;
	private String tripKey;
	private String suggestToDoKey;
	private String status;

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

	public String getTripKey() {
		return tripKey;
	}

	public void setTripKey(String tripsKey) {
		this.tripKey = tripsKey;
	}

	public String getKeyString() {
		return KeyFactory.keyToString(key);
	}

	public void setKeyString(String key) {
		this.key = KeyFactory.stringToKey(key);

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuggestToDoKey() {
		return suggestToDoKey;
	}

	public void setSuggestToDoKey(String suggestToDoKey) {
		this.suggestToDoKey = suggestToDoKey;
	}

}
