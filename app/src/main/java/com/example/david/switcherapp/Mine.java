package com.example.david.switcherapp;

/**
 * This was the fourth category of obstacle for the game before small bombs were implemented. I
 * (David) decided that it was too overpowered, and any puzzles that used it seemed to be trivially
 * easy. It is still fully functional and spawnable in-game (using code 'm'), but it is not used
 * in any levels.
 *
 * @author David Kirk
 */
public class Mine extends GameObject {

	private static int numHoles;
	private Model gameModel;

	/**
	 * Standard constructor for a trap, similar to hole
	 * @param inLoc location of the mine
	 * @param inModel model containing the mine
	 */
	public Mine(CartPoint inLoc, Model inModel) {
		super(inLoc, ++numHoles, 'm');
		state = 'i'; //c for covered
		gameModel = inModel;
		System.out.println("Mine constructed");
	}

	/**
	 * Determines whether the mine should "explode" or not (unlike Bomb, Mine has no explode method,
	 * so in this case explode just means kill itself and the orc standing on it)
	 * @return true if mine exploded
	 */
	@Override
	public boolean update() {

		boolean returnVal = false;

		Orc doomedOrc = (Orc)gameModel.getOrc(location);
		if (doomedOrc != null && state == 'i') { //if orc exists
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