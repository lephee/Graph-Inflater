package datatypes.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.hadoop.io.Writable;

import datatypes.Coordinate;

public class CoordinateWritable implements Writable {

	private Coordinate	coordinate;

	public static CoordinateWritable read(DataInput in) throws IOException {
		CoordinateWritable cw = new CoordinateWritable();
		cw.readFields(in);
		return cw;
	}

	public CoordinateWritable() {
		set(new Coordinate());
	}

	public CoordinateWritable(Coordinate c) {
		set(c);
	}

	public Coordinate get() {
		return coordinate;
	}

	public void set(Coordinate c) {
		coordinate = c;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		coordinate.x = in.readDouble();
		coordinate.y = in.readDouble();
		coordinate.z = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeDouble(coordinate.x);
		out.writeDouble(coordinate.y);
		out.writeDouble(coordinate.z);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(coordinate).toHashCode();
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
		CoordinateWritable that = (CoordinateWritable) obj;
		return new EqualsBuilder().append(this.coordinate, that.coordinate).isEquals();
	}

	@Override
	public String toString() {
		return "[CoordinateWritable " + coordinate + "]";
	}
}
