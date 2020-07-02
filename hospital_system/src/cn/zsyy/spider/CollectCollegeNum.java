package cn.zsyy.spider;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.PUBLIC_MEMBER;

import cn.zsyy.db.Dao;
import jdk.internal.dynalink.beans.StaticClass;

public class CollectCollegeNum {
	static int cntnum =0;
	public static void main(String[] args) throws IOException {
			
			String post = "https://yz.chsi.com.cn/zxdy/opentime.do?start=";
			int num = 0;
			for (int i = 0; i < 18; i++) {
				getCollegeLink(post+num);
				num = num + 50;
			}
			

	}
	
	public static void getCollegeLink(String url) throws IOException {
		int cnt = 0;
		int endIndex = 0;
		String collegeUrl = null;
		String collegename = null;
		String num = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int result = 0;
		Document document = Jsoup.connect(url).get();
//		System.out.println(document);
		Elements trElements = document.getElementsByTag("tr");
		for (Element element : trElements) {
			Elements tds=element.getElementsByTag("td");
			
			for (Element element2 : tds) {
				cnt++;
				if(cnt % 2 !=0) {
					collegeUrl = element2.getElementsByTag("a").attr("href");
//					
					collegename = element2.text();
					endIndex =collegeUrl.indexOf("start");
					num = collegeUrl.substring(41, endIndex-1);
					System.out.println(num);
					hashMap.put("collegename", collegename);
					hashMap.put("collegenum", num);
					result=Dao.insertObj("collegenum", hashMap);
					cntnum++;
					System.out.println(cntnum);
//					System.out.println(cnt);
//					System.out.println(collegename);
//					System.out.println(collegeUrl);
//					System.out.println(num);
//					int startIndex = collegeUrl.indexOf("forumid-");40
//					int endIndex =collegeUrl.indexOf("start");
//					System.out.println(startIndex);
//					System.out.println(endIndex);
				}
				
			}
			
		}
	}

}
