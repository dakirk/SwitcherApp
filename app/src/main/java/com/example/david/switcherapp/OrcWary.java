package com.example.david.switcherapp;

public class OrcWary extends OrcSmart implements Mover {
	
	public OrcWary(Model inModel) {
		super(inModel);
		displayCode = 'n'; //n for nervous
	}

	public OrcWary(CartPoint inLoc, Model inModel) {
		super(inLoc, inModel);
		displayCode = 'n';
	}
}