package com.holidu.interview.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.service.TreeDataService;

@RestController
public class TreeDataController {

	@Autowired
	TreeDataService<TreeData> treeDataService;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public @ResponseBody String info() {
		return "Tree Data Application is live";
	}

	@RequestMapping(value = "/aggregateTreesInCircle", method = RequestMethod.GET)
	public @ResponseBody String aggregateTreesInCircle(
			@RequestParam("circleXcoord") double circleXcoord,
			@RequestParam("circleYcoord") double circleYcoord,
			@RequestParam("circleRad") double circleRad) {
		return treeDataService.filterTreeDataAndAggregateToJsonResults(circleXcoord,
				circleYcoord, circleRad);
	}
}
