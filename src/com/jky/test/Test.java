package com.jky.test;

import java.util.List;

import com.jky.biz.KindBiz;
import com.jky.biz.MemberBiz;
import com.jky.biz.SaleRecordBiz;
import com.jky.entity.Kind;
import com.jky.entity.Member;
import com.jky.ui.CasherFrame;

public class Test {

	public static void main(String[] args) {
		//问业务
//		MemberBiz biz = new MemberBiz();
//		Member member = biz.isCheckLogin("user1", "111111");
//		System.out.println(member);
		
//		CasherFrame casherFrame = new CasherFrame();
		
		SaleRecordBiz sbiz = new SaleRecordBiz();
		KindBiz kbiz = new KindBiz();
		List<Kind> allKind = kbiz.queryAllKind(null);
		int size = allKind.size();
		int kindId[]=new int[size];
		String kindName[]=new String[size];
		for (Kind kind : allKind) {
			--size;
			kindId[size] = kind.getKindId();
			kindName[size] = kind.getName();
		}
		
		int value[]=sbiz.countKindSale(kindId);
	}

}



