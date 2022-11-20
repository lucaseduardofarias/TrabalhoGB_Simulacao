package engine.restaurant.events;

import engine.Event;
import engine.Scheduler;
import engine.ConsoleColors;
import engine.EntitySet;
import engine.restaurant.entities.Clients;

public class ClientsArrival extends Event {

	public static double TIME_EVENT_SCHEDULEN = 0.326605;

	public ClientsArrival(int id, Scheduler scheduler) {
		super(id, scheduler);
	}

	@Override
	public void execute() {
		Scheduler schedule = this.scheduler;
		if (schedule.getTime() < 180) {
			/* Agenda próxima chegada */
			schedule.scheduleIn(schedule.createEvent("ClientsArrival"), TIME_EVENT_SCHEDULEN);

			Clients clients = new Clients(schedule.getAndIncrementCurrentClientId(), schedule.getTime());

			System.out.printf(ConsoleColors.BLACK_BOLD + "%.2f - " + "Evento " + this.eventId + ": Chegada do cliente: "
					+ clients.getId() + ConsoleColors.RESET, schedule.time);
			/* Agenda início do atendimento */
			if (schedule.getResource(0).isAvailable()) {
				StartOrder ss = new StartOrder(schedule.getAndIncrementCurrentEventId(), schedule, clients,
						schedule.getResource(0));
				schedule.scheduleNow(ss);
			} else if (schedule.getResource(1).isAvailable()) {
				StartOrder ss = new StartOrder(schedule.getAndIncrementCurrentEventId(), schedule, clients,
						schedule.getResource(1));
				schedule.scheduleNow(ss);
			}
			/* Ou insere na menor fila do Caixa */
			else {
				EntitySet cashierQueue = schedule.getEntitySet(0);
				EntitySet cashierQueue2 = schedule.getEntitySet(1);
				if (cashierQueue.getSize() < cashierQueue2.getSize()) {
					cashierQueue.insert(clients);
					System.out.printf(
							ConsoleColors.GREEN_BACKGROUND + "%.2f - " + "Evento " + this.eventId + ": Cliente "
									+ clients.getId() + " esperando na fila do Caixa 1\n" + ConsoleColors.RESET,
							schedule.time);
				} else {
					System.out.printf("\u001B[34m%.2f - " + "Evento " + this.eventId + ": Cliente " + clients.getId()
							+ " esperando na fila do Caixa 2\n\u001B[0m", schedule.time);
					cashierQueue2.insert(clients);

				}
			}
		}
	}
}