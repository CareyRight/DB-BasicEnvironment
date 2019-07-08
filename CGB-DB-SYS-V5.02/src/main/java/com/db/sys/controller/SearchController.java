package com.db.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")  //根路径就是把url的共性提出来,方便书写
public class SearchController {
	
	/*
	 * 这个方法用来测试注解方式搭建的springmvc的运行环境
	 * http://localhost:8080/CGB-DB-SYS-V5.02/doIndexUI0.do
	 */
	@RequestMapping("doIndexUI0")
	@ResponseBody
	public String doIndexUI0() {
		return "starteraaaaaaaaaaaaaa";
	}
	
	/*
	 * 这个方法返回一个字符串,而不是返回一个页面
	 * http://localhost:8080/CGB-DB-SYS-V5.02/doSearch.do
	 */
	@RequestMapping("doSearch")
	@ResponseBody
	public String doSearch() {
		System.out.println("search ...");
		return "search by pageCurrent";
	}
	
	/*
	 * 这个方法将一个对象转化成一个json串返回
	 * http://localhost:8080/CGB-DB-SYS-V5.02/doSearchPageCurrent.do
	 */
	
	@RequestMapping("doSearchPageCurrent")
	@ResponseBody
	public List<Map<String, Object>> doSearchPageCurrent(){
		ArrayList<Map<String , Object>> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String ,Object>();
		map.put("A", 111);
		map.put("B", 222);
		list.add(map);
		map=new HashMap<String, Object>();
		map.put("C", 333);
		map.put("D", 444);
		list.add(map);
		return list;
	}
}
