package com.example.david.switcherapp;

import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Model {

	private int size;
	private int gameState = 0; //0 for in progress, 1 for win, -1 for loss
	private char[][] viewArray;
	private ArrayList<GameObject> objList = new ArrayList<GameObject>();
	private ArrayList<Orc> orcList = new ArrayList<Orc>(); //list of orcs, for sweeping orc operations
	private Wizard protagonist; //only one wizard per level, to avoid confusion
	private String hint;

//Setup

	/**
	 * Constructor
	 * @param filename name of the level file to be loaded
	 */
	public Model(String filename) {
		//eventually, will have it read a file to get object locations, but for now I'll hardcode them
		size = 9;
		viewArray = new char[size][size];
		clear();

		boolean validLevel;

		//load the level from the text document and initialize all objects
		//makes orcs hunt wizard
		try {
			loadLevel(filename);
			update();
			validLevel = initGame();
		} catch (IOException e) {
			e.printStackTrace();
			validLevel = false;
		}


		if (validLevel) {
			System.out.println("Model constructed");
		} else {
			System.out.println("Model improperly constructed. Make sure the level includes a wizard.");
		}
	}

	/**
	 * Creates GameObjects for level. Modified from https://www.mkyong.com/java/java-read-a-text-file-line-by-line/
	 * @param filename name of level file to be loaded
	 * @exception IOException if file read fails
	 */
	private void loadLevel(String filename) throws IOException {

		try {

            File f = new File(filename);

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            System.out.println("Reading file using Buffered Reader");

            int lineCount = 0;

            while ((readLine = b.readLine()) != null) {

            	if (lineCount == 0) { //first line is always the hint
            		hint = readLine;
            	} else { //all other lines are object data

	                String[] words = readLine.split("\\s");
	                if (words[0].length() == 1) {

	                	//get coords for object (no error checking)
	                	double xCoord = Double.parseDouble(words[1]);
	                	double yCoord = Double.parseDouble(words[2]);

	                	switch (words[0]) { //check if any objects available
	                		case "o":
	                			objList.add(new Orc(new CartPoint(xCoord, yCoord), this));
	                			break;
	                		case "W":
	                			objList.add(new Wall(new CartPoint(xCoord, yCoord)));
	                			break;
	                		case "P":
	                			objList.add(new Wizard(new CartPoint(xCoord, yCoord), this));
	                			break;
	                		default:
	                			System.out.println("Invalid object type");
	                			break;
	                	}
                	}
                }
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Initializes all objects in game (mainly, fills orcList and sets them to attack)
	 * @return true if one wizard, false otherwise
	 */
	private boolean initGame() { //returns true if initialized successfully (wizard present)
		boolean isViable = false;
		for (GameObject obj : objList) {

			switch (obj.getType()) { //set relevant variables
				case 'P':
					isViable = true;
					protagonist = (Wizard)obj; //sets protagonist
					break;
				case 'o':
				case 'O':
				case 's':
				case 'S': {
					orcList.add((Orc)obj);
				}
			}
		}

		if (isViable) { //if level is viable
			for (Orc enemy : orcList) {
				enemy.startMoving(protagonist.getLocation());
			}
		}

		return isViable;
	}

//Updating

	/**
	 * Updates all GameObjects in the Model
	 * @return true if something updated, false otherwise
	 */
	public boolean update() {

		if (gameState == 0) {
			int deadOrcCounter = 0;
			boolean returnVal = false;

			for (GameObject obj : objList) { //update every object in list
				boolean eventHappened = obj.update();
				if (eventHappened) {
					returnVal = true;
				}
			}

			for (GameObject obj : objList) { //tally dead and blocked orcs, see if game over
				

				if (obj.getType() == 'o') { //if this object is an orc (will expand in future to include more types of orcs)
					if (obj.getState() == 'b' || obj.getState() == 'd') { //if orc is dead or blocked
						deadOrcCounter++; //increase incapacitated orc counter
					}
				}

				if (obj.getType() == 'X') { //if wizard died, player loses
					gameState = -1;
					returnVal = true;
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

	/**
	 * Wipes the viewArray clean
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				viewArray[i][j] = '.';
			}
		}
	}

//Getters

	/**
	 * Getter for game state. Will be used to determine whether level is over.
	 * @return 0 if game still running, 1 if player won, -1 if player lost
	 */
	public int getGameState() {
		return gameState;
	}

	/**
	 * Searches for a GameObject by location
	 * @param objLocation the location of the object to be searched for
	 * @return the GameObject at that location if there is one, or null otherwise
	 */
	public GameObject getGameObject(CartPoint objLocation) {
		for (GameObject obj : objList) {
			if (obj.getLocation().equals(objLocation)) {
				return obj;
			}
		}
		return null;
	}

	//searches for an Orc by location
	public Orc getOrc(CartPoint orcLocation) {
		for (Orc orc : orcList) {
			if (orc.getLocation().equals(orcLocation)) {
				return orc;
			}
		}
		return null;
	}

	public String getHint() {
		return hint;
	}

//Setters

	//adds a new GameObject to the list
	//for the sake of simplicity, the wizard must always be at the end of the list
	public void addObject(GameObject object) {
		objList.add(object);
	}

//Printing

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