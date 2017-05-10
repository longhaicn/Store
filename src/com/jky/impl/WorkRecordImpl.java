package com.jky.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.IWorkRecord;
import com.jky.db.DBConnection;
import com.jky.entity.WorkRecord;

public class WorkRecordImpl extends DBConnection implements IWorkRecord {

	@Override
	public List<WorkRecord> queryAllWork(String condition) {
		List<WorkRecord> lists = null;
		WorkRecord workRecord = null;
		conn = super.getConnection();
		String sql = "SELECT * FROM store.workrecord;";
		if (condition.length() == 8) {
			sql = "SELECT * FROM store.workrecord where memberId = '" + condition + "' order by workrecordId desc";
		}

		System.out.println(sql);

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<WorkRecord>();
				while (rs.next()) {
					workRecord = new WorkRecord();
					workRecord.setWorkRecordId(rs.getInt("workrecordId"));
					workRecord.setMemeberId(rs.getInt("memberId"));
					workRecord.setStartTime(rs.getString("starttime"));
					workRecord.setLastTime(rs.getString("lasttime"));
					lists.add(workRecord);
				}
			}
		} catch (Exception e) {

		}
		return lists;
	}

	@Override
	public Vector<Vector<Object>> queryAllWorkToVector(List<WorkRecord> lists) {
		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (WorkRecord workRecord : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(workRecord.getWorkRecordId());
				v.add(workRecord.getMemeberId());
				v.add(workRecord.getStartTime());
				v.add(workRecord.getLastTime());
				temp.add(v);
			}
		}
		return temp;
	}

	@Override
	public boolean addWorkRecord(WorkRecord workRecord) {

		conn = super.getConnection();
		try {
			String sql = "INSERT INTO `store`.`workrecord` (  `memberId`, `starttime`,`lasttime`) VALUES ( '"
					+ workRecord.getMemeberId() + "', '" + workRecord.getStartTime() + "', '" + workRecord.getLastTime()
					+ "');";

			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			super.closeAll();
		}
		return true;

	}

	@Override
	public boolean updWorkRecord(WorkRecord workRecord) {

		conn = super.getConnection();
		try {
			String sql = "UPDATE `store`.`workrecord` SET `lasttime`='" + workRecord.getLastTime()
					+ "' WHERE `workrecordId`='" + workRecord.getWorkRecordId() + "';";

			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			super.closeAll();
		}
		return true;

	}

}
