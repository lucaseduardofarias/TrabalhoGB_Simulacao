package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class FinishCooking extends Event {

	private Order order;
	private Resource resource;

	public FinishCooking(int id, Scheduler scheduler, Order order, Resource resource) {
		super(id, scheduler);
		this.order = order;
		this.resource = resource;
	}

	public void execute() {
		Scheduler scheduler = this.scheduler;
		/* 
		  Agenda início da refeição do cliente caso ele já esteja sentado 
		*/
		if (this.order.getClients().getIsSeated()) {
			scheduler.scheduleNow(new StartEating(scheduler.getAndIncrementCurrentEventId(), scheduler, this.order.getClients()));
			/* Ou adiciona na fila de pedidos prontos */
		} else {
			EntitySet finishedOrders = scheduler.getEntitySet(6);
			finishedOrders.insert(order);
			System.out.printf("\u001B[35m%.2f - " + "Evento " + this.eventId + ": Cliente " + order.getId() + " Pedido esperando que o cliente sente\n\u001B[0m", scheduler.time);
		}

		resource.release(1);

		EntitySet orderQueue = scheduler.getEntitySet(resource.getId());
		if (!(orderQueue.isEmpty())) {
			Order order = (Order) orderQueue.getFirst();
			orderQueue.remove();
			scheduler.scheduleNow(new StartCooking(scheduler.getAndIncrementCurrentEventId(), scheduler, order, this.resource));
		}
	}

}