package formats.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.giraph.conf.GiraphConfiguration;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.junit.Before;
import org.junit.Test;

import utils.NoOpComputation;

import com.google.gson.Gson;

import datatypes.writable.EdgeDataWritable;
import datatypes.writable.VertexDataWritable;
import formats.JsonCustomVertexInputFormat;

public class TestJsonCustomVertexInputFormat extends JsonCustomVertexInputFormat {

	RecordReader<LongWritable, Text>												rr;
	ImmutableClassesGiraphConfiguration<Text, VertexDataWritable, EdgeDataWritable>	conf;
	TaskAttemptContext																tac;
	String																			input	= "{'id':'15','values':{'coordinate':{'x':-527.610,'y':-880.87,'z':0.0},'weight':0.0},'edges':[{'targetId':'5','weight':0.0},{'targetId':'6','weight':0.0}]}";
	Gson																			gson	= new Gson();
	Vertex<Text, VertexDataWritable, EdgeDataWritable>								vertexRead;

	@Before
	public void setUp() throws IOException, InterruptedException {
		rr = mock(RecordReader.class);
		when(rr.nextKeyValue()).thenReturn(true).thenReturn(false);
		GiraphConfiguration giraphConf = new GiraphConfiguration();
		giraphConf.setComputationClass(DummyComputation.class);
		conf = new ImmutableClassesGiraphConfiguration<Text, VertexDataWritable, EdgeDataWritable>(giraphConf);
		tac = mock(TaskAttemptContext.class);
		when(tac.getConfiguration()).thenReturn(conf);
	}

	protected TextVertexReader createVertexReader(final RecordReader<LongWritable, Text> rr) {
		return new JsonCustomVertexReader() {
			@Override
			protected RecordReader<LongWritable, Text> createLineRecordReader(InputSplit inputSplit,
					TaskAttemptContext context) throws IOException, InterruptedException {
				return rr;
			}
		};
	}

	@Test
	public void testReadVertex() {
		try {
			readVertex();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			fail("unable to read");
		}
	}

	@Test
	public void testHappyRead() throws IOException, InterruptedException {
		vertexRead = readVertex();
		assertEquals("15", vertexRead.getId().toString());
	}

	public Vertex<Text, VertexDataWritable, EdgeDataWritable> readVertex() throws IOException, InterruptedException {
		when(rr.getCurrentValue()).thenReturn(new Text(input));
		TextVertexReader vr = createVertexReader(rr);
		vr.setConf(conf);
		vr.initialize(null, tac);
		return vr.getCurrentVertex();
	}

	public static class DummyComputation extends
			NoOpComputation<Text, VertexDataWritable, EdgeDataWritable, NullWritable> {
	}
}
