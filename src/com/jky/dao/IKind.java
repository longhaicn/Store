package com.jky.dao;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Kind;

public interface IKind {
	/**
	 * 这个方法描述的是：查询所有种类类型
	 * @author Dijkstra 2016年1月7日 上午9:59:39
	 * @param condition
	 * @return List<Kind>
	 */
	public List<Kind> queryAllKind(String condition);
	/**
	 * 这个方法描述的是：将List<Kind>转为Vector<Vector<Object>>
	 * @author Dijkstra 2016年1月7日 上午10:00:10
	 * @param listsKind
	 * @return Vector<Vector<Object>>
	 */
	public Vector<Vector<Object>> queryAllKindToVector(List<Kind> listsKind);
	/**
	 * 这个方法描述的是：对商品种类信息的增、删、改
	 * @author Dijkstra 2016年1月7日 上午10:01:45
	 * @param condition
	 * @return boolean
	 */
	public boolean udrKind(String[] condition);

}
