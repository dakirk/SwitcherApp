package com.example.david.switcherApp;

import java.util.ArrayList;

/**
 * This is a movable trap with an adjustable kill radius. Like the hole and the mine, it goes off
 * when an orc steps on it, and like the mine, it is destroyed when used, but unlike the mine, bombs
 * can destroy objects in neighboring tiles. For the purposes of this game only bombs with radii of
 * 1 (its own tile only) and 2 (its own tile and the neighboring tiles) will be used, and
 * these will be represented as the small black bombs and the large red bombs, respectively. A bomb
 * with a radius greater than 1 can also kill the wizard if he is within its radius.
 *
 * @author David Kirk
 */
public class Bomb extends GameObject {

	private static int numHoles;
	private Model gameModel;
	private double radius;

	/**
	 * Constructor for the Bomb. Creates a bomb with the given radius at the given location.
	 * @param inLoc location for the bomb to start at
	 * @param inModel model that contains the bomb
	 * @param inRadius bomb's explosion radius
	 */
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

	/**
	 * Updates the bomb to determine whether it needs to explode or not, and whether to render the
	 * explosion sprite or not
	 * @return true if the bomb has exploded
	 */
	@Override
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

	/**
	 * Gets all the GameObjects within the bomb's radius, and if they're alive (Orcs or Wizards),
	 * it kills them.
	 */
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