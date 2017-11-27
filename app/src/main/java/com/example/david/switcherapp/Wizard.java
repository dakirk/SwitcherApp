package com.example.david.switcherapp;

public class Wizard extends GameObject{

	private Model wizardVision;

	/**
	 * Simple constructor, generates a default CartPoint as its location
	 * @param inModel Model that the Wizard will be placed into
	 */
	public Wizard(Model inModel) {
		super(new CartPoint(), 1, 'P');
		state = 'a'; //a for alive
		wizardVision = inModel;
		System.out.println("Wizard constructed");
	}

	/**
	 * Constructor
	 * @param inLoc	CartPoint of Wizard at creation
	 * @param inModel Model that the Wizard will be placed into
	 */
	public Wizard(CartPoint inLoc, Model inModel) {
		super(inLoc, 1, 'P');
		state = 'a';
		wizardVision = inModel;
		System.out.println("Wizard constructed");
	}

	/** 
	 * Performs a swap, but is smarter than that in GameObject. Excludes objects with the types included in the string excludeChars.
	 * @param p1 First point to swap
	 * @param p2 Second point to swap
	 * @see CartPoint
	 */
	public void magicSwap(CartPoint p1, CartPoint p2) {

		GameObject obj1 = wizardVision.getGameObject(p1);
		GameObject obj2 = wizardVision.getGameObject(p2);
		String excludeChars = "wWP"; //list of unteleportable objects

		if(obj1 != null && obj2 != null) { //if both objects exist
			if (excludeChars.indexOf(obj1.getType()) != -1 || excludeChars.indexOf(obj2.getType()) != -1) { //if type of either object is a wall or wizard
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
				return;
			} else {
				obj1.swap(obj2);
			}

		} else if (obj1 == null && obj2 != null) { //if first object doesn't exist
			if (excludeChars.indexOf(obj2.getType()) != -1) {
				obj1 = obj2;
				obj2 = null;
			} else {
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
			}
		} else if (obj1 != null && obj2 == null) { //if second object doesn't exist
			if (excludeChars.indexOf(obj1.getType()) != -1) {
				obj2 = obj1;
				obj1 = null;
			} else {
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
			}
		} else { //if neither object exists
			System.out.println("Nothing to swap!");
			return;
		}
	}

	/** 
	 * Updates this Wizard to see if alive or dead
	 * @return true if state changed, false otherwise
	 */
	public boolean update() {
		if (state == 'a' && wizardVision.getOrc(location) != null) {
			state = 'd'; //d for dead;
			displayCode = 'X';
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sums up object's state as a string
	 * @return GameObject's toString concatenated with Wizard's status (alive or dead)
	 * @see GameObject
	 */
	@Override
	public String toString() {
		if (state == 'a') {
			return super.toString() + " is alive";
		} else {
			return super.toString() + " is dead";
		}
	}
}