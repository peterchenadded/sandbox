# Pull latests
docker pull splunk/splunk:7.1.0
docker run -d -e "SPLUNK_START_ARGS=--accept-license" -e "SPLUNK_USER=root" -p "8000:8000" splunk/splunk:7.1.0

