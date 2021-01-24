package com.holidu.interview.assignment.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AppUtilsTest {

	@Test
	public void testIfpointIsInsideTheCircle() {
		double p1X = 4d;
		double p1Y = 4d;
		double cX = 1d;
		double cY = 1d;
		double cRad = 6d;

		boolean result = AppUtils.checkPointInsideACircle(p1X, p1Y, cX,
				cY, cRad);
		
		assertThat(result).isTrue();

	}
	
	@Test
	public void testIfpointIsOnTheCircle() {
		double p1X = 4;
		double p1Y = 4;
		double cX = 0;
		double cY = 1;
		double cRad = 5.0d;

		boolean result = AppUtils.checkPointInsideACircle(p1X, p1Y, cX,
				cY, cRad);
		
		assertThat(result).isTrue();

	}
	
	@Test
	public void testIfpointIsOutsideTheCircle() {
		double p1X = 3;
		double p1Y = 3;
		double cX = 0;
		double cY = 1;
		double cRad = 2;

		boolean result = AppUtils.checkPointInsideACircle(p1X, p1Y, cX,
				cY, cRad);
		
		assertThat(result).isFalse();

	}
}
