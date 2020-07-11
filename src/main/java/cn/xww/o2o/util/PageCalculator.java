package cn.xww.o2o.util;
//dao只认函数，浏览器认页数，将pageindex转成rowindex

public class PageCalculator {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		//数据库中分页语句 LIMIT #{rowIndex},#{pageSize};从rowIndex，查pageSize条数据
		//如果pageindex=1，则从第0条开始选取pagesize条，pageindex=2,表示要查第二页，2-1=1，从第pagesize开始选取了，。。。=3，从第2*pagesize选
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
