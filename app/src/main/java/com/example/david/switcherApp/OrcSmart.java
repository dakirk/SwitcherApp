package com.example.david.switcherApp;

import com.example.david.switcherApp.AStarPathFinding.AStarPathFinder;
import com.example.david.switcherApp.AStarPathFinding.Mover;
import com.example.david.switcherApp.AStarPathFinding.Path;
import com.example.david.switcherApp.AStarPathFinding.PathFinder;

/**
 * This Orc uses the A* pathfinding algorithm to avoid obstacles dynamically. It will only be
 * blocked if it is surrounded on all sides and there is no way to get to its target. However, it
 * is still susceptible to obvious traps like uncovered holes and bombs. Note that this orc
 * implements the Mover template to allow it to use the A* code.
 *
 * @see GameWorld#blocked(Mover, int, int)
 * @author David Kirk
 */
public class OrcSmart extends Orc implements Mover {

	private GameWorld world;
	protected int pathLength;
	protected int stepCounter = 1;

	/**
	 * Simple constructor, generates a default CartPoint as its location
	 * @param inModel Model that the OrcSmart will be placed into
	 */
	public OrcSmart(Model inModel) {
		super(inModel);
		displayCode = 's';
		world = orcVision.getWorld();
	}

	/**
	 * Constructor
	 * @param inLoc	Location of OrcSmart at creation
	 * @param inModel Model that the OrcSmart will be placed into
	 */
	public OrcSmart(CartPoint inLoc, Model inModel) {
		super(inLoc, inModel);
		displayCode = 's';
		world = orcVision.getWorld();

	}

	/**
	 * Pretty much the same as Orc's startMoving method, but ensures that the OrcSmart version of
	 * setupDestination is called.
	 * @param dest The destination of the OrcSmart (usually a Wizard)
	 */
	@Override
	public void startMoving(CartPoint dest) {
		setupDestination(dest);
		//set state and print it
		state = 'm';
		System.out.println("Moving " + Character.toString(displayCode) + id + " to " + destination);
		
	}

	/**
	 * Similar to Orc's update method, but uses OrcSmart's startMoving method instead of Orc
	 * @return
	 */
	@Override
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

				//get a new path to see if it's blocked or not
				PathFinder pathfinder = new AStarPathFinder(world, 500, false);
				Path orcPath = pathfinder.findPath(this, (int)location.x, (int)location.y, (int)destination.x, (int)destination.y);

				if (orcPath != null) //if path not blocked, then move--this fixes an issue that prevented all smart orcs from being blocked at the same time
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

	/**
	 * Overrides Orc's updateLocation in order to use A* pathfinding. This method generates a new
	 * path every time it is called, and moves the OrcSmart to the next location in that path.
	 */
	@Override
	protected void updateLocation() { //recalculates path and advances to next step
		if (location.equals(destination)) {
			System.out.println("Arrived at target");
		} else {
			//get a new path
			PathFinder pathfinder = new AStarPathFinder(world, 500, false);
			Path orcPath = pathfinder.findPath(this, (int)location.x, (int)location.y, (int)destination.x, (int)destination.y);

			if (orcPath != null) { //if path formed correctly
				location.x = orcPath.getX(1); //use path provided by orcPath
				location.y = orcPath.getY(1); //use path provided by orcPath
			} else { //no valid path, so must be blocked
				state = 'b';
				System.out.println("Orc" + displayCode + "is blocked!");
			}
		}
	}
}