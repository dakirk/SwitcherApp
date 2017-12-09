package com.example.david.switcherapp;

import com.example.david.switcherapp.AStarPathFinding.Mover;
import com.example.david.switcherapp.AStarPathFinding.TileBasedMap;

/**
 * This class holds a minimal representation of the world as an array matrix, with each
 * GameObject represented as a single character. It also implements the TileBasedMap template
 * in order to maintain compatibility with the A* pathfinding code.
 *
 * @author David Kirk
 */
public class GameWorld implements TileBasedMap {

	private char[][] viewMat;
	private boolean[][] visitedMat;
	private int size;

	/**
	 * Default constructor for GameWorld. It initializes all matrices to 9x9, since that is the size
	 * of the button grid.
	 */
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

	/**
	 * Getter for the view matrix
	 * @param x x-coordinate of the target
	 * @param y y-coordinate of the target
	 * @return character at the given x and y coordinates
	 */
	public char getView(int x, int y) {
		return viewMat[x][y];
	}

	/**
	 * Getter for the visited matrix
	 * @param x x-coordinate of the target
	 * @param y y-coordinate of the target
	 * @return boolean at the given x and y coordinates
	 */
	public boolean getVisited(int x, int y) {
		return visitedMat[x][y];
	}

	/**
	 * Sets the character in the view matrix at the targeted position
	 * @param x x-coordinate of the target
	 * @param y y-coordinate of the target
	 * @param objType character representing the type of object at the targeted position
	 */
	public void setView(int x, int y, char objType) {
		viewMat[x][y] = objType;
	}

	/**
	 * Clears the visited matrix to false
	 */
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
	 * the supplied mover. Behaves differently depending on the type of mover that uses it
	 * 
	 * @param mover The mover that is potentially moving through the specified
	 * tile.
	 * @param x The x coordinate of the tile to check
	 * @param y The y coordinate of the tile to check
	 * @return True if the location is blocked
	 */
	public boolean blocked(Mover mover, int x, int y) {
		//System.out.println("Moving object: " + mover);
		if (((Orc)mover).getType() == 's') {
			String impassableObjects = "Wobsn"; //smart orc obstacles
			if (impassableObjects.indexOf(viewMat[x][y]) != -1) { //if object is impassible
				//System.out.println("Blocked!");
				return true;
			} else {
				//System.out.println("Not blocked!");
				return false;
			}
		} else if (((Orc)mover).getType() == 'n') {
			String impassableObjects = "WobsnhBm"; //wary orc obstacles
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

	public void printWorld() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + viewMat[i][j]);
			}
			System.out.println();
		}
	}
}