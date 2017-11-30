package com.example.david.switcherapp;

public class Wall extends GameObject {

	private static int numWalls = 1;

	/**
	 * Constructor
	 * @param inLoc	the location the wall will be placed at
	 */
	public Wall(CartPoint inLoc) {
		super(inLoc, numWalls, 'W'); //W is a solid wall, w is a broken wall
		state = 'i'; //i for intact
		numWalls++;
		System.out.println("Wall constructed");
	}

	/** 
	 * Placeholder that does nothing, because all GameObjects need to be updateable (at some point I may change this for wall breaking)
	 * @return always false for now
	 */
	public boolean update() {
		return false;
	}

	/**
	 * Sets wall's state to destroyed. Will be use by OrcBrute.
	 */
	public void smash() { //will be called by OrcBrute to smash down wall
		state = 'd';
		displayCode = 'w';
	}

	/**
	 * Sums up object's state as a string
	 * @return GameObject's toString concatenated with Wall's status (intact or destroyed)
	 * @see GameObject
	 */
	@Override
	public String toString() {
		String returnStr = super.toString();
		return returnStr + " is intact";
	}

}