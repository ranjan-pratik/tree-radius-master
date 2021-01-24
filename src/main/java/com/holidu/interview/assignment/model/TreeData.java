package com.holidu.interview.assignment.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("tree_id")
	private long treeId;
	
	@JsonProperty("spc_common")
	private String speciesCommonName;
	
	@JsonProperty("spc_latin")
	private String speciesLatinName;
	
	@JsonProperty("x_sp")
	private double Xsp;
	
	@JsonProperty("y_sp")
	private double Ysp;
	
	public TreeData() {}
	
	public TreeData(long treeId, String speciesCommonName) {
		super();
		this.treeId = treeId;
		this.speciesCommonName = speciesCommonName;
	}
	
	public long getTreeId() {
		return treeId;
	}
	public void setTreeId(long treeId) {
		this.treeId = treeId;
	}
	public String getSpeciesCommonName() {
		return speciesCommonName;
	}
	public void setSpeciesCommonName(String speciesCommonName) {
		this.speciesCommonName = speciesCommonName;
	}
	public double getXsp() {
		return Xsp;
	}
	public void setXsp(double xsp) {
		Xsp = xsp;
	}
	public double getYsp() {
		return Ysp;
	}
	public void setYsp(double ysp) {
		Ysp = ysp;
	}

}
