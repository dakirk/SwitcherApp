package com.example.david.switcherapp;

public class Hole extends GameObject {

	private static int numHoles;
	private Model gameModel;

	public Hole(CartPoint inLoc, Model inModel) {
		super(inLoc, ++numHoles, 'H');
		state = 'c'; //c for covered
		gameModel = inModel;
		System.out.println("Hole constructed");
	}

	public Hole(CartPoint inLoc, Model inModel, boolean isCovered) {
		super(inLoc);
		id = ++numHoles;
		gameModel = inModel;
		if (isCovered) {
			displayCode = 'H';
			state = 'c';
		} else {
			displayCode = 'h';
			state = 'u';
		}
	}

	public boolean update() {

		boolean returnVal = false;

		Orc doomedOrc = (Orc)gameModel.getOrc(location);
		if (doomedOrc != null) {
			doomedOrc.die();
			returnVal = true;
			if (state == 'c') {
				state = 'u'; //u for uncovered
				displayCode = 'h';
			}
		}

		return returnVal;
	}

	/**
	 * Sums up object's state as a string
	 * @return GameObject's toString concatenated with Wall's status (intact or destroyed)
	 * @see GameObject
	 */
	@Override
	public String toString() {
		String returnStr = super.toString();
		if (state == 'c') {
			returnStr += " is covered";
		} else {
			returnStr += " is uncovered";
		}

		return returnStr;
	}

}