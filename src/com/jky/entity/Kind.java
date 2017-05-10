package com.jky.entity;

public class Kind {

	/**商品种类ID*/
	private int kindId;
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**商品种类名称*/
	private String name;
	@Override
	public String toString() {
		return "Kind [kindId=" + kindId + ", name=" + name + "]";
	}

}
