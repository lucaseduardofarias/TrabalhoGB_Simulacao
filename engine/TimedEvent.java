package engine;

public class TimedEvent {

	private Double executionTime;
	private Event event;

	public TimedEvent(Event event, Double executionTime) {
		this.event = event;
		this.executionTime = executionTime;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Double getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(Double executionTime) {
		this.executionTime = executionTime;
	}
}
