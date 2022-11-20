package engine.restaurant.entities;

import engine.Entity;

public class Clients extends Entity {

	private int groupSize;
	private boolean isSeated = false;

	public Clients(int id, double creationTime) {
		super(id, creationTime);
		setRandGroupSize();
	}

	public boolean getIsSeated() {
		return this.isSeated;
	}

	public void setIsSeated(boolean isSeated) {
		this.isSeated = isSeated;
	}

	public int getGroupSize() {
		return this.groupSize;
	}

	private void setRandGroupSize() {
		groupSize = (int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
	}

}
