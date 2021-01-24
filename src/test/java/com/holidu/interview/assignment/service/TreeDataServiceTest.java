package com.holidu.interview.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.holidu.interview.assignment.model.CartesianCircle;
import com.holidu.interview.assignment.model.TreeData;
import com.holidu.interview.assignment.service.TreeDataService;
import com.holidu.interview.assignment.utils.AppUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TreeDataServiceTest {

	@Autowired
	@Qualifier("treeDataService")
	TreeDataService<TreeData> treeDataService;

	@Test
	public void testAggregatedResults_noData_null() {

		Map<String, Integer> aggregated = treeDataService
				.getAggregatedResults(null);

		assertThat(aggregated).isNotNull();
		assertThat(aggregated).isEmpty();

	}

	@Test
	public void testAggregatedResults_noData_emptyList() {

		List<TreeData> input = new ArrayList<TreeData>();
		Map<String, Integer> aggregated = treeDataService
				.getAggregatedResults(input);

		assertThat(aggregated).isNotNull();
		assertThat(aggregated).isEmpty();
	}

	@Test
	public void testAggregatedResults_withData_single() {

		List<TreeData> input = new ArrayList<TreeData>();
		TreeData treeData1 = new TreeData(1L, "red mapple");
		input.add(treeData1);

		Map<String, Integer> aggregated = treeDataService
				.getAggregatedResults(input);

		assertThat(aggregated).isNotNull();
		assertThat(aggregated).isNotEmpty();
		assertThat(aggregated.size()).isEqualTo(1);
	}

	@Test
	public void testAggregatedResults_withData_multiple() {

		List<TreeData> input = new ArrayList<TreeData>();
		TreeData treeData1 = new TreeData(1L, "red mapple");
		input.add(treeData1);
		TreeData treeData2 = new TreeData(2L, "red mapple");
		input.add(treeData2);
		TreeData treeData3 = new TreeData(3L, "honeylocust");
		input.add(treeData3);
		TreeData treeData4 = new TreeData(4L, "red mapple");
		input.add(treeData4);

		Map<String, Integer> aggregated = treeDataService
				.getAggregatedResults(input);

		assertThat(aggregated).isNotNull();
		assertThat(aggregated).isNotEmpty();
		assertThat(aggregated.size()).isEqualTo(2);
		assertThat(aggregated.get("red mapple")).isEqualTo(3);
		assertThat(aggregated.get("honeylocust")).isEqualTo(1);
	}

	@Test
	public void filterTrees_noData_null() {

		List<TreeData> output = treeDataService.filterGivenDataInsideACircle(null, null);

		assertThat(output).isNull();
	}

	@Test
	public void filterTrees_noData_empty() {

		List<TreeData> input = new ArrayList<TreeData>();
		List<TreeData> output = treeDataService.filterGivenDataInsideACircle(input,
				new CartesianCircle());

		assertThat(output).isNotNull();
		assertThat(output).isEmpty();
	}

	@Test
	public void filterTrees_withData() {

		List<TreeData> input = new ArrayList<TreeData>();

		TreeData treeData1 = new TreeData(1L, "red mapple");
		treeData1.setXsp(4d);
		treeData1.setYsp(4d);
		input.add(treeData1);

		TreeData treeData2 = new TreeData(2L, "red mapple");
		treeData2.setXsp(4d);
		treeData2.setYsp(2d);
		input.add(treeData2);

		TreeData treeData3 = new TreeData(3L, "red mapple");
		treeData3.setXsp(15d);
		treeData3.setYsp(71d);
		input.add(treeData3);

		CartesianCircle circle = new CartesianCircle(1, 1, 6);

		List<TreeData> output = treeDataService.filterGivenDataInsideACircle(input,
				circle);

		assertThat(output).isNotNull();
		assertThat(output).isNotEmpty();
		assertThat(output.size()).isEqualTo(2);
	}

}
