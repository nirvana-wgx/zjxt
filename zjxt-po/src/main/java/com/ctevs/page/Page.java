package com.ctevs.page;

public class Page<T> {

	private T t;
	
	/**
	 * 页数，从1开始
	 */
	private int pageNum = 1;

	/**
	 * 数量，每页显示数量，数据库使用
	 */
	private int count =10 ;
	
	public Page() {}

	public Page(int pageNum, int count) {
		super();
		this.pageNum = pageNum;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}

	/**
	 * 结束下标值
	 * @return
	 */
	public int getStop() {
		return getStart()+count-1;
	}

	/**
	 *  数量，每页显示数量，数据库使用
	 * @return
	 */
	public int getStart(){
		return pageNum*count;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 获取总页数
	 */
	public static int getTotalPage(Integer totalRow,Integer count){
		int totalPage=0;
		if(totalRow%count==0){
			totalPage=totalRow/count;
		}else{
			totalPage=totalRow/count+1;
		}
		return totalPage;
	}
	/**
	 * 判断是否为第一页
	 */
	public static boolean isFirst(Integer pageNum){
		if(pageNum==0){
			return true;
		}
		return false;
	} 
	/**
	 * 判断是否为最后一页
	 */
	public static boolean isLast(Integer pageNum,Integer totalPage){
		if(pageNum==totalPage-1){
			return true;
		}
		return false;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}