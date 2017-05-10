package com.jky.dao;

public interface ICheck {
	/**
	 * 这个方法描述的是：新建一张订单
	 * @author Dijkstra 2015年12月19日 下午7:15:56
	 * @param memberId
	 * @param date
	 * @param sum
	 * @return
	 */
	public boolean addCheck(int memberId,String date,float sum) ;
	/**
	 * 这个方法描述的是：根据时间拿到订单号
	 * @author Dijkstra 2015年12月19日 下午7:16:14
	 * @param date
	 * @return
	 */
	public int getCheckId(String date) ;
	
}
