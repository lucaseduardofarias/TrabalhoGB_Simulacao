package engine.restaurant.entities;

import engine.Entity;

public class Order extends Entity {

	private Clients clients;
	private boolean isReady = false;

	public Order(int id, double creationTime, Clients clients) {
		super(id, creationTime);
		this.clients = clients;
	}

	public Clients getClients() {
		return this.clients;
	}

	public boolean getIsReady() {
		return this.isReady;
	}

	public void setIsReady(boolean isReady) {
		this.isReady = isReady;
	}

}
