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


public class CollectHistoryData {
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
	public static String HOST = "i.snssdk.com";
	public static String REFERER = "https://i.snssdk.com/feoffline/hot_list/template/hot_list/forum_tab.html?activeWidget=1";
	
	public void HistoryData() throws IOException {
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
		JSONArray nationwide = ncovListObj.getJSONArray("nationwide");
		System.out.println(nationwide);
		
		for (int i = 0; i < nationwide.size(); i++) {
			
			JSONObject historyList = (JSONObject) nationwide.get(i);
			System.out.println(historyList);
			Date date = historyList.getDate("date");
			int suspectedNum = historyList.getInteger("suspectedNum");
			int curesNum = historyList.getInteger("curesNum");
			int suspectedIncr = historyList.getInteger("suspectedIncr");
			int confirmedNum = historyList.getInteger("confirmedNum");
			int deathsNum = historyList.getInteger("deathsNum");
			int suspectedNumStr = historyList.getInteger("suspectedNumStr");
			Double curesRatio = historyList.getDouble("curesRatio");
			int suspectedIncrStr = historyList.getInteger("suspectedIncrStr");
			int asymptomaticNum = historyList.getInteger("asymptomaticNum");
			double deathsRatio = historyList.getDoubleValue("deathsRatio");
			int asymptomaticIncr = historyList.getInteger("asymptomaticIncr");
			int treatingNum = historyList.getInteger("treatingNum");
			int inboundNum = historyList.getInteger("inboundNum");
			System.out.println(date);
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("date", date);
			hashMap.put("suspectedNum", suspectedNum);
			hashMap.put("curesNum", curesNum);
			hashMap.put("suspectedIncr", suspectedIncr);
			hashMap.put("confirmedNum", confirmedNum);
			hashMap.put("deathsNum", deathsNum);
			hashMap.put("suspectedNumStr", suspectedNumStr);
			hashMap.put("curesRatio",curesRatio);
			hashMap.put("suspectedIncrStr",suspectedIncrStr);
			hashMap.put("asymptomaticNum",asymptomaticNum);
			hashMap.put("deathsRatio",deathsRatio);
			hashMap.put("asymptomaticIncr",asymptomaticIncr);
			hashMap.put("treatingNum",treatingNum);
			hashMap.put("inboundNum",inboundNum);
			int result =Dao.insertObj("history", hashMap);
			System.out.println(result);
			
		

	}
	}
	}
	
