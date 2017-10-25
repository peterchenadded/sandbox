package first_topology;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class CommitFeedListener extends BaseRichSpout {
	private SpoutOutputCollector outputCollector;
	private List<String> commits;

	@Override
	public void nextTuple() {
		for(String commit: commits) {
			outputCollector.emit(new Values(commit));
		}

	}

	@Override
	public void open(Map configMap, TopologyContext context, SpoutOutputCollector outputCollector) {
		this.outputCollector = outputCollector;
		
		try {
			commits = IOUtils.readLines(ClassLoader.getSystemResourceAsStream("changelog.txt"), Charset.defaultCharset().name());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("commit"));

	}

}
