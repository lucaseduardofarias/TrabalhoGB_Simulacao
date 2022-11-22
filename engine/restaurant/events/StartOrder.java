package engine.restaurant.events;

import engine.Event;
import engine.Scheduler;
import engine.Resource;
import engine.restaurant.entities.Clients;

public class StartOrder extends Event {

	private Clients clients;
	private Resource resource;

	public StartOrder(int id, Scheduler scheduler, Clients clients, Resource resource) {
		super(id, scheduler);
		this.clients = clients;
		this.resource = resource;
	}

	public StartOrder(int id, Scheduler scheduler) {
		super(id, scheduler);
	}

	public void execute() {
		/* Agenda a finalização do atendimento */
		Scheduler scheduler = this.scheduler;
		System.out.printf("%.2f - " + "Evento " + this.eventId + ": Cliente " + clients.getId()+ " sendo atendido pelo caixa " + (resource.getId() + 1) + "\n", scheduler.time);
		resource.allocate(1);
		FinishOrder ss = new FinishOrder(scheduler.getAndIncrementCurrentEventId(), scheduler, this.clients, this.resource);
		scheduler.scheduleIn(ss, scheduler.getNumberGenerators().normalDist(8, 2));
	}

}