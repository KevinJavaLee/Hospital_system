package cn.zsyy.spider;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.*;

import com.alibaba.fastjson.JSONObject;



public class CollectHot {
//	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
//	public static String HOST = "voice.baidu.com";
////	public static String REFERER = "https://i.snssdk.com/feoffline/hot_list/template/hot_list/forum_tab.html?activeWidget=1";
	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_1#tab2");  //°Ù¶È·çÔÆ°ñÍøÖ·
		connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");  //Ä£Äâ»ðºüä¯ÀÀÆ÷·ÃÎÊÍøÒ³
		
		Document document  = connect.get();
		System.out.println(document);
		
	}

}
