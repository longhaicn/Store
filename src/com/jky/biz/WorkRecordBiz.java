package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.SaleRecord;
import com.jky.entity.WorkRecord;
import com.jky.factory.Factory;

public class WorkRecordBiz {

	public List<WorkRecord> queryAllWork(String condition) {
		// TODO Auto-generated method stub
		return Factory.getIWorkRecordInstance().queryAllWork(condition);
	}

	public Vector<Vector<Object>> queryAllWorkToVector(List<WorkRecord> listsWork) {
		// TODO Auto-generated method stub
		return Factory.getIWorkRecordInstance().queryAllWorkToVector(listsWork);
	}

	public boolean addWorkRecord(WorkRecord workRecord) {
		// TODO Auto-generated method stub
		return Factory.getIWorkRecordInstance().addWorkRecord(workRecord);
	}

	public boolean updWorkRecord(WorkRecord workRecord) {
		// TODO Auto-generated method stub
		return Factory.getIWorkRecordInstance().updWorkRecord(workRecord);
	}





}
