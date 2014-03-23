package formats;

import java.io.IOException;
import java.util.List;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.io.formats.TextVertexInputFormat;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import datatypes.EdgeType;
import datatypes.VertexType;
import datatypes.VertexValues;
import datatypes.writable.EdgeDataWritable;
import datatypes.writable.VertexDataWritable;

public class JsonCustomVertexInputFormat extends
		TextVertexInputFormat<Text, VertexDataWritable, EdgeDataWritable> {

	@Override
	public TextVertexReader createVertexReader(InputSplit split, TaskAttemptContext context) throws IOException {
		return new JsonCustomVertexReader();
	}

	public class JsonCustomVertexReader extends
			TextVertexReaderFromEachLineProcessedHandlingExceptions<VertexType, JsonSyntaxException> {
		private Gson	gson	= new Gson();

		@Override
		protected Iterable<Edge<Text, EdgeDataWritable>> getEdges(VertexType vertexType) throws JsonSyntaxException,
				IOException {
			EdgeType[] edgeTypeArray = vertexType.getEdges();
			List<Edge<Text, EdgeDataWritable>> edges = Lists.newArrayListWithCapacity(edgeTypeArray.length);
			for (int i = 0; i < edgeTypeArray.length; i++) {
				EdgeType edgeType = edgeTypeArray[i];
				edges.add(EdgeFactory.create(new Text(edgeType.getTargetId()),
						new EdgeDataWritable(edgeType.getWeight())));
			}
			return edges;
		}

		@Override
		protected Text getId(VertexType vertexType) throws JsonSyntaxException, IOException {
			return new Text(vertexType.getId());
		}

		@Override
		protected VertexDataWritable getValue(VertexType vertexType) throws JsonSyntaxException, IOException {
			VertexValues values = vertexType.getValues();
			return new VertexDataWritable(values.getCoordinate(), values.getWeight());
		}

		@Override
		protected VertexType preprocessLine(Text line) throws JsonSyntaxException, IOException {
			return gson.fromJson(line.toString(), VertexType.class);
		}
	}
}