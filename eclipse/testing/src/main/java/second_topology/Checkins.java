package second_topology;

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

public class Checkins extends BaseRichSpout {
	private List<String> checkins;
	private int nextEmitIndex;
	private SpoutOutputCollector outputCollector;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.outputCollector = collector;
		this.nextEmitIndex = 0;
		
		try {
			checkins = IOUtils.readLines(ClassLoader.getSystemResourceAsStream("checkins.txt"),
					Charset.defaultCharset().name());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void nextTuple() {
		String checkin = checkins.get(nextEmitIndex);
		String[] parts = checkin.split(",");
		Long time = Long.valueOf(parts[0]);
		String address = parts[1];
		outputCollector.emit(new Values(time, address));
		
		nextEmitIndex = (nextEmitIndex + 1) % checkins.size();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("time", "address"));

	}

}
