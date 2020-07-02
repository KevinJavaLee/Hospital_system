package cn.zsyy.spider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zsyy.db.Dao;


public class CollectDataOfWorld {
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
	public static String HOST = "i.snssdk.com";
	public static String REFERER = "https://i.snssdk.com/feoffline/hot_list/template/hot_list/forum_tab.html?activeWidget=1";

	public static void main(String[] args) throws IOException, ParseException {
		
		CollectData();
	}
	public static void  CollectData() throws IOException {
		

		String url = "https://i.snssdk.com/forum/home/v1/info/?activeWidget=1&forum_id=1656784762444839";
		String doc = Jsoup.connect(url).userAgent(USER_AGENT).header("Host", HOST).header("Referer", REFERER).execute().body();
//		1.转换为对象
		JSONObject jsonObject = JSON.parseObject(doc);
//		String ncovStringList = jsonObject.getJSONObject("forum").getJSONObject("extra").getString("ncov_string_list");
//		JSONObject ncovListObj = JSON.parseObject(ncovStringList);
//		JSONArray nationwides = ncovListObj.getJSONArray("nationwide");
		String ncovStringList = jsonObject.getJSONObject("forum").getJSONObject("extra").getString("ncov_string_list");
		JSONObject ncovListObj = JSON.parseObject(ncovStringList);
		
		
		//取出每日的相关汇总数据,可以把这个数据存储到数据库中
		//[{confirmedNum: 9809, curesNum: 183, date: "2020-01-31", deathsNum: 213, suspectedNum: 15238}]
		JSONArray world = ncovListObj.getJSONArray("world");
		System.out.println(world);
		for (int i = 0; i <world.size(); i++) {
			JSONObject countryList = (JSONObject) world.get(i);
			System.out.println(countryList);
			int suspectedNum = countryList.getInteger("suspectedNum");
			int curesNum = countryList.getInteger("curesNum");
			int code = countryList.getInteger("code");
			int confirmedNum = countryList.getInteger("confirmedNum");
			int treatingNum = countryList.getInteger("treatingNum");
			int deathsNum = countryList.getInteger("deathsNum");
			String id = countryList.getString("id");
			String name = countryList.getString("name");
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String dateString = formatter.format(date);
			System.out.println(dateString);
			
			
			String sqString = "update world set suspectedNum=?,curesNum=?,code=?,confirmedNum=?,treatingNum=?,deathsNum=?,name=?,date=? where id=?  ";
			Object[] objects = {suspectedNum,curesNum,code,confirmedNum,treatingNum,deathsNum,name,dateString,id};
			int result = Dao.execute(sqString, objects);
//			HashMap<String, Object> hashMap = new HashMap<String, Object>();
//			hashMap.put("id",id );
//			hashMap.put("suspectedNum",suspectedNum );
//			hashMap.put("curesNum", curesNum);
//			hashMap.put("code", code);
//			hashMap.put("confirmedNum", confirmedNum);
//			hashMap.put("treatingNum",treatingNum );
//			hashMap.put("deathsNum",deathsNum );
//			hashMap.put("name", name);
//			hashMap.put("date", dateString);
//			int result =Dao.insertObj("world", hashMap);
			System.out.println(result);
					
		}
		


	}
	
	}
	
