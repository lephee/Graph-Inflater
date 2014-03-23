package datatypes;

public class VertexValues {

	private Coordinate	coordinate;
	private double		weight;

	public VertexValues() {
		this(new Coordinate(), 0);
	}

	public VertexValues(Coordinate c) {
		this(c, 0);
	}

	public VertexValues(Coordinate c, double weight) {
		coordinate = c;
		this.weight = weight;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
