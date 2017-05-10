package com.jky.entity;

public class GoodsPlus {
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getNameGoods() {
		return nameGoods;
	}

	public void setNameGoods(String nameGoods) {
		this.nameGoods = nameGoods;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getKindId() {
		return kindId;
	}

	public void setKindId(int kindId) {
		this.kindId = kindId;
	}

	public String getNameKind() {
		return nameKind;
	}

	public void setNameKind(String nameKind) {
		this.nameKind = nameKind;
	}

	public String getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(String namePosition) {
		this.namePosition = namePosition;
	}

	/**商品ID*/
	private int goodsId;
	/**商品名称*/
	private String nameGoods;
	/**商品二维码*/
	private String barCode;
	/**商品价格*/
	private float price;
	/**商品数量*/
	private int number;
	/**商品所在货架ID*/
	private int positionId;
	/**商品种类ID*/
	private int kindId;
	
	/**商品种类名称*/
	private String nameKind;
	
	/**货架名称*/
	private String namePosition;

	@Override
	public String toString() {
		return "GoodsPlus [goodsId=" + goodsId + ", nameGoods=" + nameGoods + ", barCode=" + barCode + ", price="
				+ price + ", number=" + number + ", positionId=" + positionId + ", kindId=" + kindId + ", nameKind="
				+ nameKind + ", namePosition=" + namePosition + "]";
	}

}
