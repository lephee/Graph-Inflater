package datatypes.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Writable;

import datatypes.Coordinate;

public class VertexDataWritable implements Writable {

	private CoordinateWritable	coordinate;
	private DoubleWritable		weight;

	public static VertexDataWritable read(DataInput in) throws IOException {
		VertexDataWritable instance = new VertexDataWritable();
		instance.readFields(in);
		return instance;
	}

	public VertexDataWritable() {
		this(new CoordinateWritable(), new DoubleWritable());
	}

	public VertexDataWritable(Coordinate coordinate, double weight) {
		this(new CoordinateWritable(coordinate), new DoubleWritable(weight));
	}

	public VertexDataWritable(CoordinateWritable coordinate, DoubleWritable weight) {
		setCoordinate(coordinate);
		setWeight(weight);
	}

	public CoordinateWritable getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(CoordinateWritable coordinate) {
		this.coordinate = coordinate;
	}

	public DoubleWritable getWeight() {
		return weight;
	}

	public void setWeight(DoubleWritable weight) {
		this.weight = weight;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		coordinate.readFields(in);
		weight.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		coordinate.write(out);
		weight.write(out);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(coordinate).append(weight).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		VertexDataWritable that = (VertexDataWritable) obj;
		return new EqualsBuilder().append(this.coordinate, that.coordinate)
								  .append(this.weight, that.weight).isEquals();
	}
	
	@Override
	public String toString() {
		return "[VertexDataWritable coordinate: " + coordinate + " weight: " + weight + "]";
	}
}