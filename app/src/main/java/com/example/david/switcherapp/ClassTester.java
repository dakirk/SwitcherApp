//This was the program I (David Kirk) used to test the backend before the frontend was ready for
//integration. It was at one point capable of running independently of Android, though Model has
//since been modified to work with Android Studio, eliminating independent functionality.

/*package com.example.david.switcherapp;

import java.io.IOException;

public class ClassTester {

	public static void main(String[] args) {
		
		CartPoint point1 = new CartPoint(0, 0);
		CartPoint point2 = new CartPoint();
		CartPoint point3 = new CartPoint(0, 8);
		CartPoint point4 = new CartPoint(8, 0);
		CartPoint point5 = new CartPoint(8, 8);


		Model testModel = new Model("Level1.txt", );
		// Orc orc1 = new Orc(point1, testModel);
		// Orc orc2 = new Orc(point4, testModel);
		// Wizard wizard = new Wizard(point5, testModel);

		// //add a wall of walls
		// for (int i = 0; i < 9; i++) {
		// 	testModel.addObject(new Wall(new CartPoint(i, 5)));
		// }


		// Orc orc1 = (Orc)testModel.getGameObject(point4);
		// Orc orc2 = (Orc)testModel.getGameObject(point1);
		// Wizard wizard = (Wizard)testModel.getGameObject(new CartPoint(4, 8));

		testModel.printAll();
		System.out.println("\n" + testModel.getHint());
		testModel.printBoard();
		testModel.clear();


		//testing movement
		
		boolean isMoving;

		do {
			try {
				System.in.read(); //waits for user to press enter
			} catch (IOException e) {}
			testModel.clear();
			isMoving = !testModel.update();
			testModel.printBoard();
		} while (testModel.getGameState() == 0);

		System.out.println();



		//testModel.printAll();


		//testModel.update();


		//how to make the orc get stuck:
		//have a container class with all objects, containing a matrix with objects in it
		//every orc has a copy of a reference to the container and can check for nearby objects
		//get grid positions, see if they match any other objects
		//if matches a trap, orc dies
		//if matches an obstacle (orc, wall, or level boundary), sets state to "blocked" until next tick
		//if all orcs dead or blocked at the end of a tick, game won
		//if any orc position matches wizard position, game lost
	}
}*/