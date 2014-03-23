package datatypes;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Coordinate {

	public double	x;
	public double	y;
	public double	z;

	public Coordinate() {
		this(0, 0, 0);
	}

	public Coordinate(double x, double y) {
		this(x, y, 0);
	}

	public Coordinate(double x, double y, double z) {
		setXYZ(x, y, z);
	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setXYZ(double x, double y, double z) {
		setXY(x, y);
		this.z = z;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(x).append(y).append(z).toHashCode();
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
		Coordinate that = (Coordinate) obj;
		return new EqualsBuilder().append(this.x, that.x).append(this.y, that.y).append(this.z, that.z).isEquals();

	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
