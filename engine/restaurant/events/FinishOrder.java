package engine.restaurant.events;

import engine.EntitySet;
import engine.Event;
import engine.Resource;
import engine.Scheduler;
import engine.restaurant.entities.Clients;
import engine.restaurant.entities.Order;

public class FinishOrder extends Event {

	private Clients clients;
	private Resource resource;

	public FinishOrder(int id, Scheduler scheduler, Clients clients, Resource resource) {
		super(id, scheduler);
		this.clients = clients;
		this.resource = resource;
	}

	public void execute() {

		sendClientsToTable();

		sendOrderToKitchen();

		pickNextAttende();

		resource.release(1); // Libera o caixa
	}

	/**
	 * Agenda evento de envio de clientes à mesa de acordo com o tamanho do grupo de
	 * clientes.
	 * 
	 * @return void
	 */
	private void sendClientsToTable() {
		Resource table = null;
		if (clients.getGroupSize() == 1)
			table = this.scheduler.getResource(2);
		else if (clients.getGroupSize() == 2)
			table = this.scheduler.getResource(3);
		else
			table = this.scheduler.getResource(4);

		if (table.isAvailable()) {
			Seat ss = new Seat(this.scheduler.getAndIncrementCurrentEventId(), this.scheduler, clients, table);
			scheduler.scheduleNow(ss);
		} else {
			EntitySet seatQueue = this.scheduler.getEntitySet(table.getId());
			System.out.printf("\u001B[31m%.2f - " + "Evento " + this.eventId + ": Cliente " + clients.getId()
					+ " esperando na fila da mesa - Recurso: " + table.getId() + "\n\u001B[0m", this.scheduler.time);
			seatQueue.insert(this.clients);
		}
	}

	/**
	 * Agenda envio do pedido à cozinha
	 * 
	 * @return void
	 */
	private void sendOrderToKitchen() {
		Resource cooks = this.scheduler.getResource(5);
		Order order = new Order(clients.getId(), this.scheduler.getTime(), clients);
		if (cooks.isAvailable()) {
			StartCooking sc = new StartCooking(this.scheduler.getAndIncrementCurrentEventId(), this.scheduler, order,
					cooks);
			this.scheduler.scheduleNow(sc);
		} else {
			EntitySet kitchenQueue = this.scheduler.getEntitySet(cooks.getId());

			kitchenQueue.insert(order);
		}
	}

	/**
	 * Agenda próximo atendimento caso possua fila no caixa
	 * 
	 * @return void
	 */
	private void pickNextAttende() {
		EntitySet cashierQueue = this.scheduler.getEntitySet(resource.getId());

		if (!(cashierQueue.isEmpty())) {

			Clients clients = (Clients) cashierQueue.getFirst();
			cashierQueue.remove();

			this.scheduler.scheduleNow(new StartOrder(this.scheduler.getAndIncrementCurrentEventId(), this.scheduler,
					clients, this.resource));
		}
	}

}