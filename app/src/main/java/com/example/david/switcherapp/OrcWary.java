package com.example.david.switcherapp;

/**
 * This Orc inherits its pathfinding from OrcSmart, and therefore has the same ability to avoid
 * obstacles. However, this orc has the ability to avoid conspicuous traps (uncovered holes and
 * bombs). Note that this orc implements the Mover template in order to use the A* code.
 *
 * @see GameWorld#blocked(Mover, int, int)
 * @author David Kirk
 */
public class OrcWary extends OrcSmart implements Mover {

	/**
	 * Simple constructor, generates a default CartPoint as its location
	 * @param inModel Model that the OrcSmart will be placed into
	 */
	public OrcWary(Model inModel) {
		super(inModel);
		displayCode = 'n'; //n for nervous
	}

	/**
	 * Constructor
	 * @param inLoc	Location of OrcBrute at creation
	 * @param inModel Model that the OrcBrute will be placed into
	 */
	public OrcWary(CartPoint inLoc, Model inModel) {
		super(inLoc, inModel);
		displayCode = 'n';
	}
}