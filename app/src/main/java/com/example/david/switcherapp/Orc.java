package com.example.david.switcherapp;

public class Orc extends GameObject {

	protected double speed;
	protected CartPoint destination;
	protected CartPoint delta;
	protected Model orcVision;
	protected static int numOrcs = 1;

	//default constructor
	public Orc() {
		super(new CartPoint(), numOrcs, 'o');
		speed = 1; // <-- might not use this
		orcVision = new Model();
		numOrcs++;
		System.out.println("Orc default constructed");
	}

	public Orc(Model inModel) {
		super(new CartPoint(), numOrcs, 'o');
		speed = 1;
		orcVision = inModel;
		numOrcs++;
		System.out.println("Orc constructed");
	}

	public Orc(CartPoint inLoc, Model inModel) {
		super(inLoc, numOrcs, 'o');
		speed = 1;
		orcVision = inModel;
		numOrcs++;
		System.out.println("Orc constructed");

	}

	/*
	public Orc(CartPoint inLoc, int inId, Model inModel) {
		numOrcs++;
		super(inLoc, inId, 'o');
		speed = 1;
		orcVision = inModel;
		System.out.println("Orc constructed");
	}
	*/

	public boolean update() {

		boolean returnVal = false;

		switch (state) {
			case 's': 
				System.out.println("Orc is stopped");
				break;
			
			case 'm': 
				GameObject obstacle; //allocates object "obstacle" to figure out if obstacle is there

				if (location.equals(destination)) {
					System.out.println("Orc has arrived");
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
						System.out.println("Orc has hit an obstacle");
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
				System.out.println("Orc is blocked!");
				break;
			default: 
				System.out.println("No valid state");
			
		}

		return returnVal;
	}

	//overrides GameObject function of same name
	public char getType() {
		return displayCode;
	}

	public CartPoint getDestination() {
		return destination;
	}

	//starts the orc moving--in practice, this will only ever be used to target Wizard
	public void startMoving(CartPoint dest) {
		setupDestination(dest);
		//set state and print it
		state = 'm';
		System.out.println("Moving " + Character.toString(displayCode) + id + " to " + destination);
		
	}

	//forces orc to stop--may not use this?
	public void stop() {
		state = 's';
		System.out.println("Orc " + id + " stopped");
	}

	//overrides toString method
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

	//updates location following simple algorithm: if there is a longer vertical distance than horizontal 
	//distance to target, move vertically--if vice-versa, do opposite, if equal, do vertical

	//This function will be overridden in other subclasses of Orc
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

	protected GameObject getObjAtPos(double x, double y) {
		return orcVision.getGameObject(new CartPoint(x, y));
	}

	//sets up destination--might be a useless holdover from PA3
	protected void setupDestination(CartPoint dest) {
		destination = dest;
		delta = calcDelta();

	}




	//calculates delta (direction to move in), used every tick--speed part not reallly used, it's a holdover from PA3
	private CartPoint calcDelta() {
		CartPoint deltaPoint = destination.subtract(location);
		double unitSpeed = speed/(CartPoint.cartDistance(destination, location));
		return deltaPoint.multiply(unitSpeed);
	}
}