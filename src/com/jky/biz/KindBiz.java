package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Kind;
import com.jky.factory.Factory;

public class KindBiz {

	public List<Kind> queryAllKind(String condition) {
		// TODO Auto-generated method stub
		return Factory.getIKindInstance().queryAllKind(condition);
	}

	public Vector queryAllKindToVector(List<Kind> listsKind) {
		// TODO Auto-generated method stub
		return Factory.getIKindInstance().queryAllKindToVector(listsKind);
	}

	public boolean udrKind(String[] condition) {
		// TODO Auto-generated method stub
		return Factory.getIKindInstance().udrKind(condition);
	}

}
