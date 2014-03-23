package datatypes.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Writable;

public class EdgeDataWritable implements Writable {

	private DoubleWritable	weight;

	public static EdgeDataWritable read(DataInput in) throws IOException {
		EdgeDataWritable instance = new EdgeDataWritable();
		instance.readFields(in);
		return instance;
	}

	public EdgeDataWritable() {
		this(0);
	}

	public EdgeDataWritable(double weight) {
		this(new DoubleWritable(weight));
	}

	public EdgeDataWritable(DoubleWritable weight) {
		this.weight = weight;
	}

	public DoubleWritable getWeight() {
		return weight;
	}

	public void setWeight(DoubleWritable weight) {
		this.weight = weight;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		weight.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		weight.write(out);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(weight).toHashCode();
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
		EdgeDataWritable that = (EdgeDataWritable) obj;
		return new EqualsBuilder().append(this.weight, that.weight).isEquals();
	}

	@Override
	public String toString() {
		return "[EdgeDataWritable weight: " + weight + "]";
	}
}
