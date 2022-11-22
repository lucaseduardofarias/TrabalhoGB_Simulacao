package engine;

public class Entity {

	private String name;
	private int id;
	private double creationTime;

	public Entity(int id, double creationTime) {
		this.id = id;
		this.creationTime = creationTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setCreationTime(double creationTime) {
		this.creationTime = creationTime;
	}

	public double getCreationTime() {
		return this.creationTime;
	}
}