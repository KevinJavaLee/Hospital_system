package cn.zsyy.Thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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






public class CollectScore1 {
	
	public static int cnt = 0;
	static String urlString ="https://yz.chsi.com.cn/zxdy/forum--method-listDefault,year-2014,forumid-";
	public static void main(String[] args) throws IOException {
		
		
		String collegename = null;
		int collegenum = 0;
		ArrayList<HashMap<String, Object>> collegeList = new ArrayList<HashMap<String,Object>>();
		String sqlString = "select collegename,collegenum from collegenum where id < 5382 and id >=5366";
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
		
		String mysqlurl = "jdbc:mysql://localhost:3306/collegeinfo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true"; //数据库连接地址
        String mysqlname = "com.mysql.cj.jdbc.Driver";
        String user = "root"; 
        String password = "a18713837118";//密码
        java.sql.Connection conn = null;
			try {	
			    Class.forName(mysqlname);
				conn=DriverManager.getConnection(mysqlurl, user, password);
				conn.setAutoCommit(false);//关闭自动提交
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Document document = Jsoup.connect(url).timeout(60000).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36").get();
		org.jsoup.select.Elements question = document.getElementsByClass("question_cnt_tr");
		
		String sql= "INSERT INTO score (question,answer,collegename) VALUES(?,?,?)";
		
		StringBuilder sqls= new StringBuilder(); 
		try {
			conn.setAutoCommit(false);
			PreparedStatement pst=conn.prepareStatement(sql);
			for (org.jsoup.nodes.Element element : question) {
				cnt++;
				Elements quest = element.getElementsByClass("question");
				String quest_textString = quest.text();
				Elements quest_a = element.getElementsByClass("question_a");
				String quest_atextString = quest_a.text();
				
//				Elements time = element.getElementsByClass("question_t ch-table-center");
				pst.setString(1,quest_textString);
				pst.setString(2, quest_atextString);
				pst.setString(3, name);
				pst.addBatch();
				
				

			}
			
			pst.executeBatch();
	        // 提交事务
	        conn.commit();
	        pst.close();
            conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
