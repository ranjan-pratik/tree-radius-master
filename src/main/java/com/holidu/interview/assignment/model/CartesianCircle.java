package com.holidu.interview.assignment.model;

import org.springframework.stereotype.Component;

@Component
public class CartesianCircle {

	double coordX;
	double coordY;
	double radius;
	
	public CartesianCircle() {}
	
	public CartesianCircle(double coordX, double coordY, double radius) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.radius = (radius*3.28);
	}
	
	public double getCoordX() {
		return coordX;
	}
	public void setCoordX(double coordX) {
		this.coordX = coordX;
	}
	public double getCoordY() {
		return coordY;
	}
	public void setCoordY(double coordY) {
		this.coordY = coordY;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}

}
