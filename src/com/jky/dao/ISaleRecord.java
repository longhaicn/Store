package com.jky.dao;

import java.util.List;
import java.util.Vector;

import com.jky.entity.SaleRecord;

public interface ISaleRecord {
	/**
	 * 这个方法描述的是：添加销售记录
	 * @author Dijkstra 2016年1月7日 上午10:05:45
	 * @param checkId
	 * @param goodsId
	 * @param number
	 * @param sumUp
	 * @return boolean
	 */
	public boolean addSaleRecord(int checkId, int goodsId, int number, float sumUp);
	/**
	 * 这个方法描述的是：查询所有销售记录
	 * @author Dijkstra 2016年1月7日 上午10:06:16
	 * @param condition
	 * @return List<SaleRecord>
	 */
	public List<SaleRecord> queryAllSale(String condition);
	/** 
	 * 这个方法描述的是：将List<SaleRecord>转化为 Vector<Vector<Object>>
	 * @author Dijkstra 2016年1月7日 上午10:06:41
	 * @param listsSale
	 * @return Vector<Vector<Object>>
	 */
	public Vector<Vector<Object>> queryAllSaleToVector(List<SaleRecord> listsSale);
	/**
	 * 这个方法描述的是：计算各类商品的销售总额
	 * @author Dijkstra 2016年1月7日 上午10:07:28
	 * @param kindId
	 * @return int[]
	 */
	public int[] countKindSale(int[] kindId);

}
