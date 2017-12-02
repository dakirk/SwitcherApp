package com.example.david.switcherapp;

public class OrcSmart extends Orc implements Mover {

	private GameWorld world;
	//private Path orcPath;
	//private AStarPathFinder pathfinder;
	protected int pathLength;
	protected int stepCounter = 1;

	public OrcSmart(Model inModel) {
		super(inModel);
		displayCode = 's';
		world = orcVision.getWorld();
	}

	public OrcSmart(CartPoint inLoc, Model inModel) {
		super(inLoc, inModel);
		displayCode = 's';
		world = orcVision.getWorld();

	}

	public void startMoving(CartPoint dest) {
		setupDestination(dest);
		//set state and print it
		state = 'm';
		System.out.println("Moving " + Character.toString(displayCode) + id + " to " + destination);
		
	}

	public boolean update() {

		boolean returnVal = false;

		switch (state) {
			case 's': 
				System.out.println("Orc " + id + " is stopped");
				break;
			
			case 'm': 
				updateLocation();
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

	protected void updateLocation() { //recalculates path and advances to next step
		if (location.equals(destination)) {
			System.out.println("Arrived at target");
		} else {
			PathFinder pathfinder = new AStarPathFinder(world, 500, false);
			Path orcPath = pathfinder.findPath(this, (int)location.x, (int)location.y, (int)destination.x, (int)destination.y);

			if (orcPath != null) {
				location.x = orcPath.getX(1); //use path provided by orcPath
				location.y = orcPath.getY(1); //use path provided by orcPath
			} else {
				state = 'b';
			}
		}
	}
}