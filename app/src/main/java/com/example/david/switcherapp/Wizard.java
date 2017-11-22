package com.example.david.switcherapp;

public class Wizard extends GameObject{

	private Model wizardVision;

	public Wizard(Model inModel) {
		super(new CartPoint(), 1, 'P');
		state = 'a'; //a for alive
		wizardVision = inModel;
		System.out.println("Wizard constructed");
	}

	public Wizard(CartPoint inLoc, Model inModel) {
		super(inLoc, 1, 'P');
		state = 'a';
		wizardVision = inModel;
		System.out.println("Wizard constructed");
	}

	//performs a swap, but is smarter than that in GameObject
	public void magicSwap(CartPoint p1, CartPoint p2) {

		GameObject obj1 = wizardVision.getGameObject(p1);
		GameObject obj2 = wizardVision.getGameObject(p2);

		if(obj1 != null && obj2 != null) { //if both objects exist
			if ("wWP".indexOf(obj1.getType()) != -1 || "wWP".indexOf(obj2.getType()) != -1) { //if type of either object is a wall or wizard
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
				return;
			} else {
				obj1.swap(obj2);
			}

		} else if (obj1 == null && obj2 != null) { //if first object doesn't exist
			if ("wWP".indexOf(obj2.getType()) != -1) {
				obj1 = obj2;
				obj2 = null;
			} else {
				System.out.println("Cannot perform swap: Magic doesn't work on walls or wizards!");
			}
		} else if (obj1 != null && obj2 == null) { //if second object doesn't exist
			if ("wWP".indexOf(obj1.getType()) != -1) {
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

	public boolean update() {
		if (state == 'a' && wizardVision.getOrc(location) != null) {
			state = 'd'; //d for dead;
			displayCode = 'X';
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		if (state == 'a') {
			return super.toString() + " is alive";
		} else {
			return super.toString() + " is dead";
		}
	}
}