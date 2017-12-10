//TODO: add explosion class so that mines are not re-explodable? behavior is buggy
//TODO: change Mine behavior to have a "primed" and "unprimed" state--"primed" is default, while "unprimed" requires priming by stepping on it (in other words, unprimed mines require two triggers to go off)
//TODO: add bombs with similar behavior to mines, but visible to wary orcs and with a radius

package com.example.david.switcherapp;

import java.util.ArrayList;

public class Bomb extends GameObject {

	private static int numHoles;
	private Model gameModel;
	private double radius;

	public Bomb(CartPoint inLoc, Model inModel, int inRadius) {
		super(inLoc, ++numHoles, 'r'); //default is small
		state = 'i'; //c for covered
		gameModel = inModel;
		radius = inRadius;
		if (radius > 1) {
			displayCode = 'R'; //for large bombs
		}
		System.out.println("Mine constructed");
	}

	public boolean update() {

		boolean returnVal = false;

		Orc doomedOrc = (Orc)gameModel.getOrc(location);
		if (doomedOrc != null && state == 'i') { //if orc exists and isn't a brute
			explode();
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

	private void explode() {
		ArrayList<GameObject> destroyedObjects = new ArrayList<GameObject>();
		destroyedObjects = gameModel.getObjectsInRadius(location, radius);
		for (GameObject obj : destroyedObjects) {
			if (obj.isOrc()) {
				((Orc)obj).die();
			} else if (obj.getType() == 'P') {
				((Wizard)obj).die();
			}
		}
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