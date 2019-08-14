package com.ctevs.common;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtils {

	private static final Pattern json_pattern_1 = Pattern.compile("^\\{(.*?)\\}$");
	private static final Pattern jaon_pattern_2 = Pattern.compile("^\\[(.*?)\\]$");

	/**
	 * 判断list是否为空
	 */
	public static boolean isEmpty(List<?> list){
		return list == null || list.isEmpty();
	}

    /**
     * 将集合拆分为500个每个并返回
     * @param resList
     * @param <T>
     * @return
     */
	public static   <T> List<List<T>> splitByFive(List<T> resList){
       return CommonUtils.split(resList,500);
    }
    /**
     *
     */
	/**
	 * 拆分集合
	 * @param <T>
	 * @param resList  要拆分的集合
	 * @param count    每个集合的元素个数
	 * @return  返回拆分后的各个集合
	 */
	public static  <T> List<List<T>> split(List<T> resList,int count){

		if(resList==null ||count<1)
			return  null ;
		List<List<T>> ret=new ArrayList<List<T>>();
		int size=resList.size();
		if(size<=count){ //数据量不足count指定的大小
			ret.add(resList);
		}else{
			int pre=size/count;
			int last=size%count;
			//前面pre个集合，每个大小都是count个元素
			for(int i=0;i<pre;i++){
				List<T> itemList=new ArrayList<T>();
				for(int j=0;j<count;j++){
					itemList.add(resList.get(i*count+j));
				}
				ret.add(itemList);
			}
			//last的进行处理
			if(last>0){
				List<T> itemList=new ArrayList<T>();
				for(int i=0;i<last;i++){
					itemList.add(resList.get(pre*count+i));
				}
				ret.add(itemList);
			}
		}
		return ret;

	}
	/*public static void main(String[] args){
		List<Integer> listDemo = new ArrayList<>();
		for (int i = 0; i < 600; i++) {
			listDemo.add(i);
		}
		List<List<Integer>> demos = CommonUtils.split(listDemo,700);
		System.out.println("");
	}*/


	/**
	 * 检查字符串是否是json格式的，是返回true，否返回false
	 *
	 * @param content
	 * @return
	 */
	public static boolean checkIfJSON(String content) {
		if (StringUtils.isBlank(content)) {
			return false;
		}

		boolean b1 = json_pattern_1.matcher(content).find();
		boolean b2 = jaon_pattern_2.matcher(content).find();

		return (b1 || b2);
	}

}
