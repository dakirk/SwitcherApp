package com.example.david.switcherapp;

import java.util.*;

public class Model {

	private int size;
	private int gameState = 0; //0 for in progress, 1 for win, -1 for loss
	private char[][] viewArray;
	private ArrayList<GameObject> objList = new ArrayList<GameObject>();

	public Model() {
		//eventually, will have it read a file to get object locations, but for now I'll hardcode them
		size = 9;
		viewArray = new char[size][size];
		clear();

		//objList.put(new CartPoint(1, 1), new Orc(new CartPoint(1, 1), this));

		System.out.println("Model constructed");
	}

	//updates all GameObjects
	public boolean update() {

		if (gameState == 0) {
			int deadOrcCounter = 0;
			boolean returnVal = false;

			for (GameObject obj : objList) {
				boolean eventHappened = obj.update();
				if (eventHappened) {
					returnVal = true;
				}

				if (obj.getType() == 'o') { //if this object is an orc (will expand in future to include more types of orcs)
					if (obj.getState() == 'b' || obj.getState() == 'd') { //if orc is dead or blocked
						deadOrcCounter++; //increase incapacitated orc counter
					}
				}

				if (obj.getType() == 'P') { //if wizard died, player loses
					if (obj.getState() == 'd') {
						gameState = -1;
						returnVal = true;
					}
				}

				if (deadOrcCounter == Orc.getNumOrcs()) { //if all orcs dead or incapacitated, player wins
					gameState = 1;
					returnVal = true;
				}

				viewArray[(int)obj.getLocation().x][(int)obj.getLocation().y] = obj.getType();

			}

			//determine which message to print if game is over
			if (gameState == 1) {
				System.out.println("All orcs are killed or blocked. You win!");
			} else if (gameState == -1) {
				System.out.println("An orc killed the wizard. You lose!");
			}

			return returnVal;
		} else {
			return true; //prevents infinite loop if updates are looped after the game ends
		}

	}

	//fills objArray with blank spaces
	public void clear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				viewArray[i][j] = '.';
			}
		}
	}

	public int getGameState() {
		return gameState;
	}

	//searches for a GameObject by location
	public GameObject getGameObject(CartPoint objLocation) {
		for (GameObject obj : objList) {
			if (obj.getLocation().equals(objLocation)) {
				return obj;
			}
		}
		return null;
	}

	//adds a new GameObject to the list
	//for the sake of simplicity, the wizard must always be at the end of the list
	public void addObject(GameObject object) {
		objList.add(object);
	}

	public void printAll() {
		System.out.println("...");
		for (GameObject obj : objList) {
			System.out.println(obj);
		}
		System.out.println("...");
	}

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + viewArray[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}