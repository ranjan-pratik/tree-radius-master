package com.holidu.interview.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.holidu.interview.assignment.model.CartesianCircle;
import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.service.TreeDataService;
import com.holidu.interview.assignment.utils.AppUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TreeDataIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	@Qualifier("treeDataService")
	TreeDataService<TreeData> treeDataService;

	@Test
	public void testFilterDataAndAggregateResults()
			throws JsonProcessingException {

		CartesianCircle circle = new CartesianCircle(1027431.148, 202756.7687,
				1000);

		Map<String, Integer> aggregatedResults = treeDataService
				.filterAndAggregate(circle);

		assertThat(aggregatedResults).isNotNull();
		assertThat(aggregatedResults).isNotEmpty();
		assertThat(aggregatedResults.size()).isLessThan(1000);

		AppUtils.printAggregatedMap(aggregatedResults);
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		String mockReturnJson = "\"red maple\" : 1";

		this.mockMvc
				.perform(get("/aggregateTreesInCircle")
						.param("circleXcoord", "1027431.148")
						.param("circleYcoord", "202756.7687")
						.param("circleRad", "1"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(mockReturnJson)));
	}

}
