package com.example.david.switcherapp;

public class Wall extends GameObject {

	private static int numWalls = 1;

	public Wall(CartPoint inLoc) {
		super(inLoc, numWalls, 'W'); //W is a solid wall, w is a broken wall
		state = 'i'; //i for intact
		numWalls++;
		System.out.println("Wall constructed");
	}

	//placeholder that does nothing, because all GameObjects need to be updateable (at some point I may change this for wall breaking)
	public boolean update() {
		return false;
	}

	public char getType() {
		return displayCode;
	}

	public void smash() { //will be called by OrcBrute to smash down wall
		state = 'd';
		displayCode = 'w';
	}

	public String toString() {
		String returnStr = super.toString();
		return returnStr + " is intact";
	}

}