package com.example.david.switcherapp;

public class GameWorld implements TileBasedMap {

	private char[][] viewMat;
	private boolean[][] visitedMat;
	private int size;

	public GameWorld() {
		size = 9;
		viewMat = new char[size][size];
		visitedMat = new boolean[size][size]; 

		System.out.println("GameWorld default constructed");
	}

	/**
	 * Get the width of the tile map. The slightly odd name is used
	 * to distiguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles across the map
	 */
	public int getWidthInTiles() {
		return size;
	}

	/**
	 * Get the height of the tile map. The slightly odd name is used
	 * to distiguish this method from commonly used names in game maps.
	 * 
	 * @return The number of tiles down the map
	 */
	public int getHeightInTiles() {
		return size;
	}

	public char getView(int x, int y) {
		return viewMat[x][y];
	}

	public boolean getVisited(int x, int y) {
		return visitedMat[x][y];
	}
	
	public void setView(int x, int y, char objType) {
		viewMat[x][y] = objType;
	}

	public void clearVisited() {
		for (int i = 0; i < getWidthInTiles(); i++) {
			for (int j = 0; j < getHeightInTiles(); j++) {
				visitedMat[i][j] = false;
			}
		}
	}

	/**
	 * Notification that the path finder visited a given tile. This is 
	 * used for debugging new heuristics.
	 * 
	 * @param x The x coordinate of the tile that was visited
	 * @param y The y coordinate of the tile that was visited
	 */
	public void pathFinderVisited(int x, int y) {
		visitedMat[x][y] = true;
	}
	
	/**
	 * Check if the given location is blocked, i.e. blocks movement of 
	 * the supplied mover.
	 * 
	 * @param mover The mover that is potentially moving through the specified
	 * tile.
	 * @param x The x coordinate of the tile to check
	 * @param y The y coordinate of the tile to check
	 * @return True if the location is blocked
	 */
	public boolean blocked(Mover mover, int x, int y) {
		if (((Orc)mover).getType() == 's') {
			String impassableObjects = "Wobs"; //smart orc obstacles
			if (impassableObjects.indexOf(viewMat[x][y]) != -1) { //if object is impassible
				//System.out.println("Blocked!");
				return true;
			} else {
				return false;
			}
		} else if (((Orc)mover).getType() == 'n') {
			String impassableObjects = "WobshBm"; //wary orc obstacles
			if (impassableObjects.indexOf(viewMat[x][y]) != -1) { //if object is impassible
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Get the cost of moving through the given tile. This can be used to 
	 * make certain areas more desirable. A simple and valid implementation
	 * of this method would be to return 1 in all cases.
	 * 
	 * @param mover The mover that is trying to move across the tile
	 * @param sx The x coordinate of the tile we're moving from
	 * @param sy The y coordinate of the tile we're moving from
	 * @param tx The x coordinate of the tile we're moving to
	 * @param ty The y coordinate of the tile we're moving to
	 * @return The relative cost of moving across the given tile
	 */
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}

}