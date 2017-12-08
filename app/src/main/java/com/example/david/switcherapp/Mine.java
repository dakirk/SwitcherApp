package com.example.david.switcherapp;

public class Mine extends GameObject {

	private static int numHoles;
	private Model gameModel;

	public Mine(CartPoint inLoc, Model inModel) {
		super(inLoc, ++numHoles, 'm');
		state = 'i'; //c for covered
		gameModel = inModel;
		System.out.println("Mine constructed");
	}

	public boolean update() {

		boolean returnVal = false;

		Orc doomedOrc = (Orc)gameModel.getOrc(location);
		if (doomedOrc != null && doomedOrc.getType() != 'b') { //if orc exists and isn't a brute
			doomedOrc.die();
			returnVal = true;
			state = 'd';
			location.x = -1;
			location.y = -1;
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
		if (state == 'i') {
			returnStr += " is intact";
		} else {
			returnStr += " is destroyed";
		}

		return returnStr;
	}

}