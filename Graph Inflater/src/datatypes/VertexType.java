package datatypes;

public class VertexType {

	private String			id;
	private VertexValues	values;
	private EdgeType[]		edges;

	public VertexType(String id, VertexValues values) {
		this(id, values, new EdgeType[0]);
	}

	public VertexType(String id, VertexValues values, EdgeType[] edges) {
		this.id = id;
		this.values = values;
		this.edges = edges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VertexValues getValues() {
		return values;
	}

	public void setValues(VertexValues values) {
		this.values = values;
	}

	public EdgeType[] getEdges() {
		return edges;
	}

	public void setEdges(EdgeType[] edges) {
		this.edges = edges;
	}
}
