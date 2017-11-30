package com.example.david.switcherapp;

//NOTE: for smart Orc variants, try to implement this algorithm: http://gregtrowbridge.com/a-basic-pathfinding-algorithm/

public class Orc extends GameObject {

	protected double speed; // <-- might not use this
	protected CartPoint destination;
	protected CartPoint delta;
	protected Model orcVision;
	protected static int numOrcs = 0;

	/**
	 * Simple constructor, generates a default CartPoint as its location
	 * @param inModel Model that the Orc will be placed into
	 */
	public Orc(Model inModel) {
		super(new CartPoint(), ++numOrcs, 'o');
		speed = 1;
		orcVision = inModel;
		System.out.println("Orc constructed");
	}

	/**
	 * Constructor
	 * @param inLoc	location of Orc at creation
	 * @param inModel Model that the Orc will be placed into
	 */
	public Orc(CartPoint inLoc, Model inModel) {
		super(inLoc, ++numOrcs, 'o');
		speed = 1;
		orcVision = inModel;
		System.out.println("Orc constructed");

	}

	/**
	 * Updates the Orc's status depending on its circumstances. Determines whether orc is moving, blocked, stopped, or dead.
	 * @return true if state changed, false otherwise
	 */
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
					if (obstacle.getType() == 'o' ||
						obstacle.getType() == 'O' ||
						obstacle.getType() == 'W' ||
						obstacle.getType() == 's' ||
						obstacle.getType() == 'S') {
						state = 'b';
						System.out.println("Orc " + id + " has hit an obstacle");
						returnVal = true;
					} else { //if object isn't an obstacle
						//System.out.println("Orc is moving");
						updateLocation();
					}
				} else { //if no obstacle
					//System.out.println("Orc is moving");
					updateLocation();
				}
				break;
			
			case 'b': 
				//System.out.println("Orc " + id + " is blocked!");
				break;
			default: 
				System.out.println("No valid state");
			
		}

		return returnVal;
	}

	/**
	 * Getter for the Orc's destination
	 * @return the CartPoint representing this Orc's destination (the Wizard, usually)
	 */
	public CartPoint getDestination() {
		return destination;
	}

	/**
	 * Getter for number of Orcs in level
	 * @return number of orcs that currently exist on board
	 */
	public static int getNumOrcs() { //returns total number of orcs on board
		return numOrcs;
	}

	/** 
	 * Starts the orc moving--in practice, this will only ever be used to target Wizard
	 * @param dest the destination of the Orc (usually a Wizard)
	 */
	public void startMoving(CartPoint dest) {
		setupDestination(dest);
		//set state and print it
		state = 'm';
		System.out.println("Moving " + Character.toString(displayCode) + id + " to " + destination);
		
	}

	/** 
	 * Forces orc to stop--may not use this?
	 */
	public void stop() {
		state = 's';
		System.out.println("Orc " + id + " stopped");
	}

	/**
	 * Overrides toString method
	 * @return GameObject's toString concatenated with Orc's status (stopped, blocked, or moving, and eventually will also include dead)
	 * @see GameObject
	 */
	@Override
	public String toString() {
		String returnStr = super.toString();
		if (state == 's') {
			returnStr += " is stopped";
		} else if (state == 'b') {
			returnStr += " is blocked";
		} else if (state == 'm') {
			returnStr += (" is moving towards " + destination);
		}
		return returnStr;
	}

	/**
	 * Updates location following simple algorithm. If there is a longer vertical distance than horizontal 
	 * distance to target, move vertically--if vice-versa, do opposite, if equal, do vertical.
	 */
	protected void updateLocation() {
		if (location.equals(destination)) {
			System.out.println("Arrived at target");
		} else {
			delta = calcDelta();
			if (Math.abs(delta.x) > Math.abs(delta.y)) {
				location.x += (delta.x / Math.abs(delta.x)); //if negative, subtracts by 1, if positive, adds by 1
			} else if (Math.abs(delta.y) > Math.abs(delta.x)) {
				location.y += (delta.y / Math.abs(delta.y)); //see above
			} else {
				location.y += (delta.y / Math.abs(delta.y)); //defaults to vertical before horizontal
			}
		}
	}

	/**
	 * Gets the GameObject at the location given by the input x and y coordinates, as an alternative to the method provided by Model
	 * @param x the x-coordinate to be checked
	 * @param y the y-coordinate to be checked
	 * @return the GameObject at the given location, or null if nothing is there
	 */
	protected GameObject getObjAtPos(double x, double y) {
		return orcVision.getGameObject(new CartPoint(x, y));
	}

	/** 
	 * Sets up destination--might be a useless holdover from PA3
	 * @param dest Orc's destination, usually Wizard
	 */
	protected void setupDestination(CartPoint dest) {
		destination = dest;
		delta = calcDelta();

	}

	/**
	 * Calculates delta (direction to move in), used every tick. Speed part is not reallly used, it's a holdover from PA3
	 * @return the CartPoint representing the required "delta" to move directly to the destination, ignoring the grid
	 */
	private CartPoint calcDelta() {
		CartPoint deltaPoint = destination.subtract(location);
		double unitSpeed = speed/(CartPoint.cartDistance(destination, location));
		return deltaPoint.multiply(unitSpeed);
	}
}