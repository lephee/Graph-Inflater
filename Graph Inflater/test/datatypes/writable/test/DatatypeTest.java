package datatypes.writable.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.junit.Test;

import datatypes.Coordinate;
import datatypes.writable.CoordinateWritable;
import datatypes.writable.EdgeDataWritable;
import datatypes.writable.VertexDataWritable;

public class DatatypeTest {

	@Test
	public void testSaveCoordinate() {
		Random random = new Random();
		CoordinateWritable coordinate1 = new CoordinateWritable(new Coordinate(random.nextDouble(),
				random.nextDouble(), random.nextDouble()));
		DataInput buf = save(coordinate1);
		try {
			CoordinateWritable coordinate2 = CoordinateWritable.read(buf);
			assertEquals(coordinate1, coordinate2);
		} catch (IOException e) {
			e.printStackTrace();
			fail("unable to read back");
		}
	}

	@Test
	public void testSaveVertexData() {
		Random random = new Random();
		CoordinateWritable coordinate = new CoordinateWritable(new Coordinate(random.nextDouble(), random.nextDouble(),
				random.nextDouble()));
		VertexDataWritable vertexData1 = new VertexDataWritable(coordinate, new DoubleWritable(random.nextDouble()));
		DataInput buf = save(vertexData1);
		try {
			VertexDataWritable vertexData2 = VertexDataWritable.read(buf);
			assertEquals(vertexData1, vertexData2);
		} catch (IOException e) {
			e.printStackTrace();
			fail("unable to read back");
		}
	}

	@Test
	public void testSaveEdge() {
		Random random = new Random();
		EdgeDataWritable edge1 = new EdgeDataWritable(random.nextDouble());
		DataInput buf = save(edge1);
		try {
			EdgeDataWritable edge2 = EdgeDataWritable.read(buf);
			assertEquals(edge1, edge2);
		} catch (IOException e) {
			e.printStackTrace();
			fail("undable to read back");
		}
	}

	// helper
	public DataInput save(Writable original) {
		byte[] data = WritableUtils.toByteArray(original);
		DataInput buf = new DataInputStream(new ByteArrayInputStream(data));
		return buf;
	}

}
