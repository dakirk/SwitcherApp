package com.example.david.switcherapp;

import java.util.*;

public class Model {

	private int size;
	private char[][] viewArray;
	private ArrayList<GameObject> objList = new ArrayList<GameObject>(); 
//
	public Model() {
		//eventually, will have it read a file to get object locations, but for now I'll hardcode them
		size = 9;
		viewArray = new char[size][size];
		clear();

		Orc orc1 = new Orc(new CartPoint(1, 1), this);

		objList.add(orc1);
		//objList.put(new CartPoint(1, 1), new Orc(new CartPoint(1, 1), this));

		System.out.println("Model constructed");
	}

	//updates all GameObjects
	public boolean update() {
		boolean returnVal = false;
		for (GameObject obj : objList) {
			//obj.update();
		}
		return returnVal;
	}

	//fills objArray with blank spaces
	public void clear() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				viewArray[i][j] = '.';
			}
		}
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
}