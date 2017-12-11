package com.example.david.switcherApp;

/**
 * This is a very basic trap. It is indestructible, immovable (as defined in Wizard's magicSwap
 * method), and currently has an infinite capacity.
 *
 * @see Wizard#magicSwap(CartPoint, CartPoint)
 * @author David Kirk
 */
public class Hole extends GameObject {

	private static int numHoles;
	private Model gameModel;

	/**
	 * Basic constructor
	 * @param inLoc the location of the Hole
	 * @param inModel the Model that holds the Hole
	 */
	public Hole(CartPoint inLoc, Model inModel) {
		super(inLoc, ++numHoles, 'H');
		state = 'c'; //c for covered
		gameModel = inModel;
		System.out.println("Hole constructed");
	}

	/**
	 * More specific constructor for controlling initial Hole state
	 * @param inLoc the location of the Hole
	 * @param inModel the Model that holds the Hole
	 * @param isCovered whether the Hole starts out covered or uncovered
	 */
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

	/**
	 * Updates Hole's state to detect if an Orc has fallen in
	 * @return true if an Orc fell in, false otherwise
	 */
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
	 * @return GameObject's toString concatenated with Hole's status (covered or uncovered)
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