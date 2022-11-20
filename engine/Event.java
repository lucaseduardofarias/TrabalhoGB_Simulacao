package engine;

public class Event {

	protected int eventId;
	protected Scheduler scheduler;

	public Event(int id, Scheduler scheduler) {
		this.eventId = id;
		this.scheduler = scheduler;
	}

	public Event() {
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Scheduler getScheduler() {
		return this.scheduler;
	}

	public void execute() {
	}

}
