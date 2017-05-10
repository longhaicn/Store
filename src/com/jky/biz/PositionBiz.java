package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Position;
import com.jky.factory.Factory;

public class PositionBiz {

	public PositionBiz() {
		// TODO Auto-generated constructor stub
	}

	public List<Position> queryAllPosition(String position) {
		// TODO Auto-generated method stub
		return Factory.getIPositionInstance().queryAllPosition(position);
	}

	public Vector<Vector<Object>> queryAllPositionToVector(List<Position> listsPosition) {
		// TODO Auto-generated method stub
		return Factory.getIPositionInstance().queryAllGoodsToVector(listsPosition);
	}

	public boolean udrPosition(String[] condition) {
		// TODO Auto-generated method stub
		return Factory.getIPositionInstance().udrPosition(condition);
	}

}
