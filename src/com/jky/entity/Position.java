package com.jky.entity;

public class Position {

	/**货架ID*/
	private int positionId ;
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**货架名称*/
	private String name;
	@Override
	public String toString() {
		return "Position [positionId=" + positionId + ", name=" + name + "]";
	}

}
