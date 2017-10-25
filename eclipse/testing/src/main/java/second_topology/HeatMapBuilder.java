package second_topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.code.geocoder.model.LatLng;

import backtype.storm.Config;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class HeatMapBuilder extends BaseBasicBolt {
	private Map<Long, List<LatLng>> heatmaps;
	
	@Override
	public void prepare(Map config, TopologyContext context) {
		heatmaps = new HashMap<Long, List<LatLng>>();
	}
	
	@Override
	public Map<String, Object> getComponentConfiguration() {
		Config conf = new Config();
		conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 60);
		return conf;
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		Long time = input.getLongByField("time");
		LatLng geocode = (LatLng) input.getValueByField("geocode");
		
		Long timeInterval = selectTimeInterval(time);
		List<LatLng> checkins = getCheckinsForInterval(timeInterval);
		checkins.add(geocode);
	}
	
	private Long selectTimeInterval(Long time) {
		return time / (15 * 1000);
	}
	
	private List<LatLng> getCheckinsForInterval(Long timeInterval) {
		List<LatLng> hotzones = heatmaps.get(timeInterval);
		if (hotzones == null) {
			hotzones = new ArrayList<LatLng>();
			heatmaps.put(timeInterval, hotzones);
		}
		return hotzones;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

}
