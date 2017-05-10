package com.jky.dao;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Goods;
import com.jky.entity.GoodsPlus;

public interface IGoods {
	/**
	 * 这个方法描述的是：根据二维码找到该商品的详细信息
	 * @author Dijkstra 2015年12月19日 上午10:43:52
	 * @param barCode
	 * @return Goods
	 */
	public Goods getTheGood(String barCode);
	/**
	 * 这个方法描述的是：更新商品库存
	 * @author Dijkstra 2016年1月7日 上午9:51:33
	 * @param goodsId
	 * @param number
	 * @return boolean
	 */
	public boolean subGoodsNumber(int goodsId,int number);
	/**
	 * 这个方法描述的是：查询全部商品
	 * @author Dijkstra 2016年1月7日 上午9:52:22
	 * @param condition
	 * @return List<GoodsPlus>
	 */
	public List<GoodsPlus> queryAllGoods(String condition);
	/**
	 * 这个方法描述的是：将List<GoodsPlus>转化为Vector<Vector<Object>>
	 * @author Dijkstra 2016年1月7日 上午9:53:33
	 * @param listsGoods
	 * @return Vector<Vector<Object>>
	 */
	public Vector<Vector<Object>> queryAllGoodsToVector(List<GoodsPlus> listsGoods);
	/**
	 * 这个方法描述的是：添加商品
	 * @author Dijkstra 2016年1月7日 上午9:54:39
	 * @param goods
	 * @return boolean
	 */
	public  boolean addGoods(Goods goods);
	/**
	 * 这个方法描述的是：删除商品
	 * @author Dijkstra 2016年1月7日 上午9:55:00
	 * @param barCode
	 * @return boolean
	 */
	public boolean delGoods(String barCode);
	/**
	 * 这个方法描述的是：更新商品
	 * @author Dijkstra 2016年1月7日 上午9:56:12
	 * @param goods
	 * @return boolean
	 */
	public boolean updGoods(Goods goods);
	/**
	 * 这个方法描述的是：查询一条商品信息
	 * @author Dijkstra 2016年1月7日 上午9:56:41
	 * @param barcode
	 * @return Goods
	 */
	public Goods selGoods(String barcode);
	/**
	 * 这个方法描述的是：按商品种类查询所有商品
	 * @author Dijkstra 2016年1月7日 上午9:57:02
	 * @param kindId
	 * @return List<Goods>
	 */
	public List<Goods> queryAllGoods(int kindId);


}
