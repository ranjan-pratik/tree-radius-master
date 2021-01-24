package com.holidu.interview.assignment.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CachedTreeData {

	@Autowired
	private RestTemplate restTemplate;

	final static Logger logger = Logger.getLogger("CachedTreeData");
	final static String URL = "https://data.cityofnewyork.us/resource/nwxe-4ae8.json";

	private List<TreeData> rawTreeDataList;

	public void init() {
		logger.info("Please wait. Loading Tree data from "+URL + " ...");
		loadTreeData();
	}

	public List<TreeData> getRawTreeDataList() {
		return rawTreeDataList;
	}

	private void loadTreeData() {
		if (rawTreeDataList == null) {
			rawTreeDataList = Collections.unmodifiableList(fetchRawTreeData());
		}
	}

	private List<TreeData> fetchRawTreeData() {

		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(URL,
				Object[].class);
		Object[] objects = responseEntity.getBody();
		logger.info("Loaded "+objects.length+" Tree data from "+URL);
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.stream(objects)
				.map(object -> mapper.convertValue(object, TreeData.class))
				.collect(Collectors.toList());
	}
}
