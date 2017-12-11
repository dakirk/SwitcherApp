package com.example.david.switcherApp;

/**
 * GameObject representing the player character. Cannot move, but has the ability to move certain
 * other objects, namely Orcs and traps, except for holes. If the Wizard dies, then the game is
 * over.
 *
 * @author David Kirk
 */
public class Wizard extends GameObject{

	private static Model wizardVision;

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
	 * @param p1 first point to swap
	 * @param p2 second point to swap
	 * @see CartPoint
	 */
	public static boolean magicSwap(CartPoint p1, CartPoint p2) {

		//System.out.println("CartPoint p1" + p1);
		//System.out.println("CartPoint p2"+p2);
		GameObject obj1 = wizardVision.getGameObject(p1);
		//System.out.println(obj1);
		GameObject obj2 = wizardVision.getGameObject(p2);
		//System.out.println(obj2);
		String excludeChars = "HhwWP"; //list of unteleportable objects
		boolean flag = true;

		if(obj1 != null && obj2 != null) { //if both objects exist
			if (excludeChars.indexOf(obj1.getType()) != -1 || excludeChars.indexOf(obj2.getType()) != -1) { //if type of either object is a wall or wizard
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
				flag = false;
			} else {
				obj1.swap(obj2);
				if (obj1.isOrc())
					((Orc)obj1).pause(); //prevent orcs from moving this turn
				if (obj2.isOrc())
					((Orc)obj2).pause(); //prevent orcs from moving this turn
				flag = true;
			}

		} else if (obj1 == null && obj2 != null) { //if first object doesn't exist
			if (excludeChars.indexOf(obj2.getType()) == -1) {
				obj2.setLocation(p1); //move to other location
				if (obj2.isOrc()) {
					((Orc)obj2).pause();
				}
				flag = true;
			} else {
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
				flag = false;
			}
		} else if (obj1 != null && obj2 == null) { //if second object doesn't exist
			System.out.println("excludeChars.indexOf(obj1.getType())"+excludeChars.indexOf(obj1.getType()));
			if (excludeChars.indexOf(obj1.getType()) == -1) {
				obj1.setLocation(p2); //move to other location
				if (obj1.isOrc()) { //prevent Orc from moving in next turn
					((Orc)obj1).pause();
				}
				flag = true;
			} else {
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
				flag = false;
			}
		} else { //if neither object exists
			System.out.println("Nothing to swap!");
			flag = false;
		}

		if (flag == true) {
			System.out.println("Swap performed!");


		}
		return flag;
	}

	/**
	 * Updates this Wizard to see if alive or dead
	 * @return true if state changed, false otherwise
	 */
	public boolean update() {
		if (state == 'a' && wizardVision.getOrc(location) != null) {
			die();
			return true;
		} else {
			return false;
		}
	}

	public void die() {
		state = 'd';
		displayCode = 'X';
		location.x = -1;
		location.y = -1;
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