package com.jky.factory;

import com.jky.dao.ICheck;
import com.jky.dao.IGoods;
import com.jky.dao.IKind;
import com.jky.dao.IMember;
import com.jky.dao.IPosition;
import com.jky.dao.ISaleRecord;
import com.jky.dao.IWorkRecord;
import com.jky.impl.CheckImpl;
import com.jky.impl.GoodsImpl;
import com.jky.impl.KindImpl;
import com.jky.impl.MemberImpl;
import com.jky.impl.PositionImpl;
import com.jky.impl.SaleRecordImpl;
import com.jky.impl.WorkRecordImpl;
//静态工厂
public class Factory {
	//提供接口的实现类 返回类型为接口，new 的都是实现类
	public static IMember getIMeberInstance(){
		return new MemberImpl();
	}
	public static IGoods getIGoodsInstance() {
		return new GoodsImpl();
		
	}
	public static ICheck getICheckInstance() {
		return new CheckImpl();
		
	}
	public static ISaleRecord getISaleRecordInstance() {
		// TODO Auto-generated method stub
		return new SaleRecordImpl();
	}
	public static IWorkRecord getIWorkRecordInstance() {
		return new WorkRecordImpl();
	}
	public static IKind getIKindInstance() {
		return new KindImpl();
	}
	public static PositionImpl getIPositionInstance() {
		return new PositionImpl();
	}

	
	
	
}
