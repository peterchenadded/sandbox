package second_topology;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;


public class GeocodeLookup extends BaseBasicBolt {
	private Geocoder geocoder;
	final static Logger logger = LoggerFactory.getLogger(GeocodeLookup.class);

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String address = input.getStringByField("address");
		Long time = input.getLongByField("time");
		
		GeocoderRequest request = new GeocoderRequestBuilder()
				.setAddress(address)
				.setLanguage("en")
				.getGeocoderRequest();
	
		try {
			GeocodeResponse response = geocoder.geocode(request);
			GeocoderStatus status = response.getStatus();
			
			if (GeocoderStatus.OK.equals(status)) {
				GeocoderResult firstResult = response.getResults().get(0);
				LatLng latLng = firstResult.getGeometry().getLocation();
				collector.emit(new Values(time, latLng));
			}
		} catch (Exception ex) {
			logger.error("Response failure", ex);
		}
	}


	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("time", "geocode"));
	}
	
	@Override
	public void prepare(Map config, TopologyContext context) {
		geocoder = new Geocoder();
	}

}
