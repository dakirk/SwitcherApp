//TODO: add explosion class so that mines are not re-explodable? behavior is buggy
//TODO: change Mine behavior to have a "primed" and "unprimed" state--"primed" is default, while "unprimed" requires priming by stepping on it (in other words, unprimed mines require two triggers to go off)
//TODO: add bombs with similar behavior to mines, but visible to wary orcs and with a radius

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
		if (doomedOrc != null && state == 'i') { //if orc exists and isn't a brute
			doomedOrc.die();
			returnVal = true;
			state = 'd';
			displayCode = 'e';
			//location.x = -1;
			//location.y = -1;
		} else if (state == 'd') {
			location.x = -1;
			location.y = -1;
			//doesn't return true because this is just the "animation"
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