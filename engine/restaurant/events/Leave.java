package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class Leave extends Event {

	private Resource resource;
	private int idClient;

	public Leave(int id, Scheduler scheduler, Resource resource, int idClient) {
		super(id, scheduler);
		this.resource = resource;
		this.idClient = idClient;
	}

	public Leave(int id, Scheduler scheduler) {
		super(id, scheduler);
	}

	public void execute() {
		Scheduler s = this.scheduler;
		// Cliente senta
		System.out.printf("\u001B[31m%.2f - Evento " + this.eventId + ": Cliente " + this.getIdClient()
				+ " deixou o restaurante\n\u001B[0m", s.time);
		resource.release(1);
		EntitySet tableQueue = s.getEntitySet(resource.getId());
		if (!(tableQueue.isEmpty())) {
			Clients clients = (Clients) tableQueue.getFirst();
			tableQueue.remove();
			s.scheduleNow(new Seat(s.getAndIncrementCurrentEventId(), s, clients, this.resource));
		}

	}

	public int getIdClient() {
		return idClient;
	}

}
