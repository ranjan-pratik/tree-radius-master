package com.holidu.interview.assignment.service;

import java.util.List;
import java.util.Map;

import com.holidu.interview.assignment.model.CartesianCircle;
import com.holidu.interview.assignment.model.TreeData;

public interface TreeDataService<T extends TreeData> {

	Map<String, Integer> getAggregatedResults(List<TreeData> input);

	List<TreeData> filterGivenDataInsideACircle(List<TreeData> input,
			CartesianCircle circle);

	Map<String, Integer> filterAndAggregate(CartesianCircle circle);

	String filterTreeDataAndAggregateToJsonResults(double circleXcoord,
			double circleYCoord, double circleRad);

}
