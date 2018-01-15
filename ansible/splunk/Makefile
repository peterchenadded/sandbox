run:
	ansible-playbook splunk_playbook.yml -K

run_hf:
	ansible-playbook splunk_hf_playbook.yml -K

run_uf:
	ansible-playbook splunk_uf_playbook.yml -K

download:
	#wget -O splunk-6.6.2-4b804538c686-Linux-x86_64.tgz 'https://www.splunk.com/bin/splunk/DownloadActivityServlet?architecture=x86_64&platform=linux&version=6.6.2&product=splunk&filename=splunk-6.6.2-4b804538c686-Linux-x86_64.tgz&wget=true'
	wget -O splunk-6.5.3-36937ad027d4-Linux-x86_64.tgz 'https://www.splunk.com/page/download_track?file=6.5.3/linux/splunk-6.5.3-36937ad027d4-Linux-x86_64.tgz&ac=&wget=true&name=wget&platform=Linux&architecture=x86_64&version=6.5.3&product=splunk&typed=release'
	#wget -O splunk-7.0.1-2b5b15c4ee89-Linux-x86_64.tgz 'https://www.splunk.com/bin/splunk/DownloadActivityServlet?architecture=x86_64&platform=linux&version=7.0.1&product=splunk&filename=splunk-7.0.1-2b5b15c4ee89-Linux-x86_64.tgz&wget=true'

auditd:
	ansible-playbook auditd_playbook.yml -K
