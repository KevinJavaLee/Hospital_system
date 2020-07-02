package cn.zsyy.spider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.org.apache.xml.internal.security.Init;

import cn.zsyy.db.Dao;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class Article {
	static String listArticleUrl = "http://www.nfyy.com/xwzx/yyxw";
	static String host = "http://www.nfyy.com";
 	
	
	static HashMap<String, Object> getArticle() throws IOException{
		HashMap<String, Object> article = new HashMap<>();
		//1ģ�����������http������֤�룬�ƴ���ƽ̨
		String url = "http://www.nfyy.com/xwzx/yyxw";
		Document document = Jsoup.connect(url).get();
		//System.out.println(document);
		
		//ͨ���ĵ���dom������ȡԪ��
		/*Element ele = document.getElementById("nav_yyjs");
		System.out.println(ele);*/
		//ͨ��Ԫ�ض���text()/attr()/html()
		/*System.out.println(ele.text());
		System.out.println(ele.attr("class"));
		System.out.println(ele.html());*/
		
		
		
		//2������������Ӧ������
		
		//3��ȡ���ݵ����ݿ�
		
		
		
		
		
		return article;
	}
	static void getListUrl(String nextUrl) throws IOException {
		//1ģ�����������http������֤�룬�ƴ���ƽ̨
		
		Document document = Jsoup.connect(nextUrl).get();
		Elements select = document.select(".next");
		//��ȡ��һ��ѡ��class
		Element first = select.first();
		
		//��ȡ��һҳ��ַ
		String  nextPageUrl= first.attr("href");
		
		if(!nextPageUrl.equals("javascript:void(0);")) {
//			System.out.println(nextPageUrl);
			getListUrl(nextPageUrl);
			//ͨ���б�ҳ��ַȥ��ȡ����ҳ��ַ
			getArticleUrl(nextPageUrl);
		}
		
		
	}
	static void getArticleUrl(String url) throws IOException{
		
		Document document = Jsoup.connect(url).get();
		
		Elements lilist = document.select(".list_box ul li");
		for(Element item: lilist) {
			
			Elements aEle = item.select("a");
			String articleUrl = aEle.attr("href");
//			System.out.println(articleUrl);
			//ͨ������ҳȥ��ȡ����
			getContent(articleUrl);
			
		}
	}
//	String articleUrl
 	static void  getContent(String articleUrl) throws IOException {
 		String url = host + articleUrl;
// 		String url = "http://www.nfyy.com/xwzx/yyxw/a_106394.html";
 		Document article = Jsoup.connect(url).get();
 		//��ȡ����
 		String title = article.select(".art_box_c h1").first().text();
 		//��ȡ����
 		String author = article.select(".remark").first().text();
 		Pattern compile = Pattern.compile(".*?��(.*?)����");
 		Matcher matcher = compile.matcher(author);
 		if(matcher.find()) {
 			author = matcher.group(1);
 		}
 		String text = article.select(".art_con").first().html();
// 		System.out.println(title);
// 		System.out.println(author);
// 		System.out.println(text);
 		int  random =  (int)(Math.random()*100);
 		SimpleDateFormat fm=new SimpleDateFormat("yy/MM/dd HH:mm");
		Date date = new Date();
		String dateString = fm.format(date);
 		HashMap<String, Object> hashMap = new HashMap<>();
 		hashMap.put("a_title", title);
 		hashMap.put("a_author",author);
 		hashMap.put("a_content", text);
 		hashMap.put("a_pubtime", dateString);
 		hashMap.put("a_readnum", random);
 		hashMap.put("a_cid", 1);
 		int insertObj = Dao.insertObj("article", hashMap);
 	}
	
	public static void main(String[] args) throws IOException {
		
		getArticleUrl(listArticleUrl);
		getListUrl(listArticleUrl);
//		getContent();
	}
}
