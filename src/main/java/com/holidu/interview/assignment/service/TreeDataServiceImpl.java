package com.holidu.interview.assignment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.holidu.interview.assignment.model.CachedTreeData;
import com.holidu.interview.assignment.model.CartesianCircle;
import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.utils.AppUtils;

@Service("treeDataService")
public class TreeDataServiceImpl implements TreeDataService<TreeData> {

	final static Logger logger = Logger.getLogger("TreeDataServiceImpl");

	@Autowired
	CachedTreeData treeData;

	@Override
	public Map<String, Integer> filterAndAggregate(
			CartesianCircle inputCircle) {

		return getAggregatedResults(filterGivenDataInsideACircle(
				treeData.getRawTreeDataList(), inputCircle));
	}

	public Map<String, Integer> getAggregatedResults(
			final List<TreeData> input) {

		Map<String, Integer> result = new HashMap<String, Integer>();

		if (input != null && input.size() > 0) {
			for (TreeData thisTreeData : input) {
				String thisTreeCnName = thisTreeData.getSpeciesCommonName();
				if (StringUtils.isEmpty(thisTreeCnName)) {
					logger.warning("No Common Name found for tree id -"
							+ thisTreeData.getTreeId()+". Assigning a default name.");
					thisTreeCnName = "<no-name>";
				}

				int count = result.getOrDefault(thisTreeCnName, 0);
				result.put(thisTreeCnName, ++count);
			}
		}

		return result;
	}

	public List<TreeData> filterGivenDataInsideACircle(List<TreeData> input,
			CartesianCircle circle) {
		if (input != null && input.size() > 0) {

			return input.stream()
					.filter(data -> AppUtils.checkPointInsideACircle(
							data.getXsp(), data.getYsp(), circle.getCoordX(),
							circle.getCoordY(), circle.getRadius()))
					.collect(Collectors.toList());
		}

		return input;
	}

	@Override
	public String filterTreeDataAndAggregateToJsonResults(double circleXcoord,
			double circleYCoord, double circleRad) {

		CartesianCircle circle = new CartesianCircle(circleXcoord, circleYCoord,
				circleRad);

		Map<String, Integer> result = getAggregatedResults(
				filterGivenDataInsideACircle(treeData.getRawTreeDataList(),
						circle));

		String jsonResponse = "";
		try {
			jsonResponse = AppUtils.printAggregatedMap(result);
		} catch (JsonProcessingException e) {
			logger.severe("Error occured while json mapping results. # "
					+ e.getMessage());
		}

		return jsonResponse;

	}

}
