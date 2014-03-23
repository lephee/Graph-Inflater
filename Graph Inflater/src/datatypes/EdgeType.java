package datatypes;

public class EdgeType {

	private String	targetId;
	private double	weight;

	public EdgeType(String targetId) {
		this(targetId, 0);
	}

	public EdgeType(String targetId, double weight) {
		this.targetId = targetId;
		this.weight = weight;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
