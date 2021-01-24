package com.holidu.interview.assignment.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.service.TreeDataService;

@RunWith(SpringRunner.class)
@WebMvcTest(TreeDataController.class)
public class TreeDataControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TreeDataService<TreeData> service;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		String mockReturnJson = "{\"Chinese fringetree\":1,\"Norway maple\":5,\"pin oak\":1}";
		Mockito.when(service.filterTreeDataAndAggregateToJsonResults(13d, 15d, 100d))
				.thenReturn(mockReturnJson);

		this.mockMvc
				.perform(get("/aggregateTreesInCircle")
						.param("circleXcoord", "13").param("circleYcoord", "15")
						.param("circleRad", "100"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(mockReturnJson)));
	}
}
