package com.holidu.interview.assignment.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {

	public static boolean checkPointInsideACircle(double point_X,
			double point_Y, double circle_X, double circle_Y,
			double circle_Radius) {
		if ((point_X - circle_X) * (point_X - circle_X)
				+ (point_Y - circle_Y) * (point_Y - circle_Y) <= circle_Radius
						* circle_Radius)
			return true;
		else
			return false;
	}

	public static String printAggregatedMap(Map<String, Integer> result)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(result);

		return json;
	}

}
