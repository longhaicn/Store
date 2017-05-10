package com.jky.dao;

import java.util.List;
import java.util.Vector;

import com.jky.entity.WorkRecord;

public interface IWorkRecord {
	/**
	 * 这个方法描述的是：查询所有的工作记录
	 * @author Dijkstra 2016年1月7日 上午10:08:58
	 * @param condition
	 * @return List<WorkRecord>
	 */
	public List<WorkRecord> queryAllWork(String condition);
	/**
	 * 这个方法描述的是：将List<WorkRecord>转为Vector<Vector<Object>>
	 * @author Dijkstra 2016年1月7日 上午10:09:19
	 * @param lists
	 * @return Vector<Vector<Object>>
	 */
	public Vector<Vector<Object>> queryAllWorkToVector(List<WorkRecord> lists);
	/**
	 * 这个方法描述的是：添加工作记录
	 * @author Dijkstra 2016年1月7日 上午10:09:51
	 * @param workRecord
	 * @return boolean
	 */
	public boolean addWorkRecord(WorkRecord workRecord);
	/**
	 * 这个方法描述的是：更新工作记录
	 * @author Dijkstra 2016年1月7日 上午10:10:07
	 * @param workRecord
	 * @return boolean
	 */
	public boolean updWorkRecord(WorkRecord workRecord);
}
