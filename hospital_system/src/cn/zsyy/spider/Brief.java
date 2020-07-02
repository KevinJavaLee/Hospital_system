package cn.zsyy.spider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.zsyy.db.Dao;
import sun.util.resources.cldr.bo.CalendarData_bo_CN;

public class Brief {
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
	public static void main(String[] args) throws IOException {
//		1.url的地址
		String url = "http://www.nfyy.com/aboutus/index.html";
		Document doc = Jsoup.connect(url).get();
		String  content= doc.getElementsByClass("content-yyjs").first().html();
		System.out.println(content);
		String title = "公寓村医院简介";
		SimpleDateFormat fm=new SimpleDateFormat("yy/MM/dd HH:mm");
		Date date = new Date();
		String dateString = fm.format(date);
 		HashMap<String, Object> hashMap = new HashMap<>();
 		hashMap.put("content", content);
 		hashMap.put("time",dateString);
 		hashMap.put("title", title);
 		
 		
 		int insertObj = Dao.insertObj("introduction", hashMap);
 		
//		System.out.println(doc);
		

	}

}
