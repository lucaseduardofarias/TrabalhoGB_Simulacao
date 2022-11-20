package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class Seat extends Event {

	private Clients clients;
	private Resource resource;

	public Seat(int id, Scheduler scheduler, Clients clients, Resource resource) {
		super(id, scheduler);
		this.clients = clients;
		this.resource = resource;
	}

	public void execute() {
		Scheduler s = this.scheduler;
		// Cliente senta
		resource.allocate(1);
		clients.setIsSeated(true);
		System.out.printf("\u001B[37m%.2f - " + "Evento " + this.eventId + ": Cliente " + clients.getId()
				+ " Esperando na mesa - Recurso: " + resource.getId() + "\n\u001B[0m", s.time);

		EntitySet finishedOrders = s.getEntitySet(6);
		for (Entity order : finishedOrders.getEntityList()) {
			Order aux = (Order) order;
			if (clients.getId() == aux.getId()) {
				StartEating se = new StartEating(s.getAndIncrementCurrentEventId(), s, this.clients);
				s.scheduleNow(se);
				finishedOrders.removeById(clients.getId());
				return;
			}
		}

	}

}