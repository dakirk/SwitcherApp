package com.example.david.switcherapp;

/**
 * This is a more specialized form of Orc, which has the ability to smash down walls. Besides this,
 * it is behaviorally identical to the basic Orc.
 *
 * @author David Kirk
 */
public class OrcBrute extends Orc implements Mover {

	/**
	 * Simple constructor, generates a default CartPoint as its location
	 * @param inModel Model that the OrcBrute will be placed into
	 */
	public OrcBrute(Model inModel) {
		super(inModel);
		displayCode = 'b';
	}

	/**
	 * Constructor
	 * @param inLoc	Location of OrcBrute at creation
	 * @param inModel Model that the OrcBrute will be placed into
	 */
	public OrcBrute(CartPoint inLoc, Model inModel) {
		super(inLoc, inModel);
		displayCode = 'b';
	}

	/**
	 * Updates the OrcBrute's status depending on its circumstances. Determines whether brute is
	 * moving, blocked, stopped, or dead, and tells the brute when to destroy a wall.
	 * @return true if state changed, false otherwise
	 */
	@Override
	public boolean update() {

		boolean returnVal = false;

		switch (state) {
			case 's': 
				System.out.println("Orc " + id + " is stopped");
				break;
			
			case 'm': 
				GameObject obstacle; //allocates object "obstacle" to figure out if obstacle is there

				if (location.equals(destination)) {
					System.out.println("Orc " + id + " has arrived");
					state = 's';
					returnVal = true;
					obstacle = null;
					break;

				} else { //if not arrived yet
					delta = calcDelta(); //refreshes delta

					if (Math.abs(delta.x) > Math.abs(delta.y)) { //predicts next move
						obstacle = getObjAtPos(location.x + (delta.x / Math.abs(delta.x)), location.y);
					} else {
						obstacle = getObjAtPos(location.x, location.y + (delta.y / Math.abs(delta.y)));
					}

				}

				if (obstacle != null) { //if there is an obstacle

					String blockObjs = "sob"; //object types that will block the brute

					if (blockObjs.indexOf(obstacle.getType()) != -1) {
						state = 'b';
						System.out.println("Orc " + id + " has hit an obstacle");
						returnVal = true;
					} else if (obstacle.getType() == 'W') { //if reached a wall
						((Wall)obstacle).smash();
						System.out.println("A brute destroyed a wall!");
					} else { //if object isn't an obstacle
						//System.out.println("Orc is moving");
						updateLocation();
					}
				} else { //if no obstacle
					//System.out.println("Orc is moving");
					updateLocation();
				}
				break;
			
			case 'b': //attempt to move again
				startMoving(destination);
				break; 
			case 'd':
				//System.out.println("Orc " + id + " is blocked!");
				break;
			default: 
				System.out.println("No valid state");
			
		}

		return returnVal;
	}
}