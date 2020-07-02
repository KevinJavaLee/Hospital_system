package cn.zsyy.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.PUBLIC_MEMBER;

import cn.zsyy.db.Dao;






public class CollectScore {
	
	public static int cnt = 0;
	static String urlString ="https://yz.chsi.com.cn/zxdy/forum--method-listDefault,year-2014,forumid-";
	public static void main(String[] args) throws IOException {
		
		
		String collegename = null;
		int collegenum = 0;
		ArrayList<HashMap<String, Object>> collegeList = new ArrayList<HashMap<String,Object>>();
		String sqlString = "select collegename,collegenum from collegenum";
		collegeList = Dao.query(sqlString);
//		HashMap<String, Object> hashMap =collegeList.get(0);
		for (HashMap<String, Object> hashMap : collegeList) {
//			System.out.println(hashMap);
			
			System.out.println(hashMap.get("collegename"));
			collegename = (String) hashMap.get("collegename");
			collegenum =  (int) hashMap.get("collegenum");
//			System.out.println(urlString+collegenum);
			getNextPageLink(urlString+collegenum,collegename);
		}

//		
			
//		}
		
//		getNextPageLink();
		System.out.println("结束");
		
		
		

	}
	
	public static void getNextPageLink(String post,String name) throws IOException {
		
		String poString = post+",start-";
		int num = 0;
		String nextUrl = "";
		nextUrl = poString +num+".dhtml";
//		System.out.println(nextUrl);
		Document document = Jsoup.connect(nextUrl).get();
//		Elements tags = document.getElementsByIndexEquals(index);
//		Elements pages = document.getElementsByClass("ch-page");
//		for (Element element : pages) {
//			
//			Elements lips = element.getElementsByClass("lip");
//		}
		String page = null;
		Elements lips = document.getElementsByClass("lip");
//		System.out.println(lips);
		if (lips.size() > 3) {
			 page = lips.eq(lips.size()-3).text();
		} else {
			page = "0";
		}
//		String page = lips.eq(lips.size()-3).text();
//		System.out.println(page);
		int cnt = Integer.parseInt(page);
//		System.out.println(cnt);
		
//		System.out.println(lips.eq(lips.size()-3).text());
//		for (Element element : lips) {
//			System.out.println(element);
//		}
//		int cnt = 0;
//		for (int i = 0; i < lips.size(); i++) {
//			cnt++;
//			System.out.println(cnt);
//			System.out.println(lips.eq(1));
//		}
//		for (Element element : lips) {
//			System.out.println(element);
//		}
		getInfo(nextUrl,name);
		for (int i = 0; i < cnt; i++) {
			nextUrl = poString +num+".dhtml";
			num = num + 15;
			getInfo(nextUrl,name);
		}

		
	}
	
	
	public static void  getInfo(String url,String name) throws IOException {
		
		
        
        

        
            
		
		Document document = Jsoup.connect(url).get();
		org.jsoup.select.Elements question = document.getElementsByClass("question_cnt_tr");
		
		
		for (org.jsoup.nodes.Element element : question) {
			cnt++;
			Elements quest = element.getElementsByClass("question");
			String quest_textString = quest.text();
			Elements quest_a = element.getElementsByClass("question_a");
			String quest_atextString = quest_a.text();
			
//			Elements time = element.getElementsByClass("question_t ch-table-center");
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("question", quest_textString);
			hashMap.put("answer", quest_atextString);
			hashMap.put("collegename", name);
			
			int result = Dao.insertObj("score", hashMap);
			

		}
	}

}
