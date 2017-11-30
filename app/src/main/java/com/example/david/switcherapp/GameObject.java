package com.example.david.switcherapp;

public abstract class GameObject {

	protected char displayCode;
	protected int id;
	protected char state;
	protected CartPoint location;

	//"default" constructor (not actually default, but simplest)
	public GameObject(char inCode) {
		displayCode = inCode;
		id = 1;
		state = 's';
		location = new CartPoint();
		//System.out.println("GameObject constructed.");
	}

	public GameObject(CartPoint inLoc) {
		displayCode = 'g';
		id = 1;
		state = 's';
		location = inLoc;
		//System.out.println("GameObject constructed.");
	}

	//more precise constructor
	public GameObject(CartPoint inLoc, int inId, char inCode) {
		location = inLoc;
		id = inId;
		displayCode = inCode;
		state = 's';
		//System.out.println("GameObject constructed.");
	}

	//getter for location
	public CartPoint getLocation() {
		return location;
	}

	//getter for id
	public int getId() {
		return id;
	}

	//getter for state
	public char getState() {
		return state;
	}

	public char getType() {
		return displayCode;
	}

	public void setLocation(CartPoint newLoc) {
		location = newLoc;
	}

	//swaps location of two GameObjects
	public void swap(GameObject obj) {
		CartPoint tempLoc = obj.getLocation();
		obj.setLocation(location);
		location = tempLoc;
	}

	//returns string describing object
	public String toString() {
		return Character.toString(displayCode) + id + " at " + location;
	}

	abstract boolean update();
}