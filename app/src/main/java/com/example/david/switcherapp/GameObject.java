package com.example.david.switcherapp;

/**
 * This class is the parent for all objects that appear on the game board. So far, these are the
 * Wizard (Wizard), Orcs (Orc, OrcBrute, OrcSmart, and OrcWary), and obstacles/traps (Wall and Hole,
 * and in the future, Bomb and Mine).
 *
 * @author David Kirk
 */
public abstract class GameObject {

	protected char displayCode;
	protected int id;
	protected char state;
	protected CartPoint location;

	/**
	 * This constructor uses a user-defined display code, and sets its ID to 1, state to 's', and
	 * location to (0, 0)
	 * @param inCode the GameObject's display code (its "type")
	 */
	public GameObject(char inCode) {
		displayCode = inCode;
		id = 1;
		state = 's';
		location = new CartPoint();
		//System.out.println("GameObject constructed.");
	}

	/**
	 * This constructor uses a user-defined location, and sets its display code to 'g', its ID to
	 * 1, and its state to 's'
	 * @param inLoc the GameObject's location, represented as a CartPoint
	 */
	public GameObject(CartPoint inLoc) {
		displayCode = 'g';
		id = 1;
		state = 's';
		location = inLoc;
		//System.out.println("GameObject constructed.");
	}

	/**
	 * This constructor allows the user the most control, providing parameters for location, ID, and
	 * display code. This is the constructor used by most of GameObject's children.
	 * @param inLoc the GameObject's location, represented as a CartPoint
	 * @param inId the GameObject's ID number
	 * @param inCode the GameObject's display code (its "type")
	 */
	public GameObject(CartPoint inLoc, int inId, char inCode) {
		location = inLoc;
		id = inId;
		displayCode = inCode;
		state = 's';
		//System.out.println("GameObject constructed.");
	}

	/**
	 * Getter for the CartPoint representing this object's location
	 * @return the CartPoint representing the location
	 */
	public CartPoint getLocation() {
		return location;
	}

	/**
	 * Getter for this object's ID number
	 * @return the GameObject's ID number
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for this object's current state
	 * @return the object's current state
	 */
	public char getState() {
		return state;
	}

	/**
	 * Getter for this object's display code (its "type"), used to determine how to cast it
	 * @return the object's type
	 */
	public char getType() {
		return displayCode;
	}

	/**
	 * Setter for this object's location. Has the effect of "teleporting" it across the board. Used
	 * by Orc's updateLocation and GameObject's swap methods
	 * @param newLoc this object's new location
	 */
	public void setLocation(CartPoint newLoc) {
		location = newLoc;
	}

	/**
	 * Swaps the location of this GameObject and another GameObject. Used by Wizard's magicSwap
	 * method.
	 * @param obj the other object to be swapped with
	 */
	public void swap(GameObject obj) {
		CartPoint tempLoc = obj.getLocation();
		obj.setLocation(location);
		location = tempLoc;
	}

	/**
	 * Overrides Object's toString method
	 * @return the string representing this GameObject's current status
	 */
	@Override
	public String toString() {
		return Character.toString(displayCode) + id + " at " + location;
	}

	/**
	 * Abstract version of the update function to ensure that it works for all GameObjects
	 */
	abstract boolean update();
}