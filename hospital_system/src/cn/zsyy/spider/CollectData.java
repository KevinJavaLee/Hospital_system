package cn.zsyy.spider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zsyy.db.Dao;


public class CollectData {
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36";
//	public static String HOST = "i.snssdk.com";
	public static String REFERER = "https://i.snssdk.com/feoffline/hot_list/template/hot_list/forum_tab.html?activeWidget=1";

	public static void main(String[] args) throws IOException, ParseException {

		collectData();
		
}
	public static void  collectData() throws IOException {
		String url = "https://i.snssdk.com/forum/home/v1/info/?activeWidget=1&forum_id=1656784762444839";
		String doc = Jsoup.connect(url).userAgent(USER_AGENT).header("Referer", REFERER).execute().body();
//		1.ת��Ϊ����
		JSONObject jsonObject = JSON.parseObject(doc);
//		String ncovStringList = jsonObject.getJSONObject("forum").getJSONObject("extra").getString("ncov_string_list");
//		JSONObject ncovListObj = JSON.parseObject(ncovStringList);
//		JSONArray nationwides = ncovListObj.getJSONArray("nationwide");
		String ncovStringList = jsonObject.getJSONObject("forum").getJSONObject("extra").getString("ncov_string_list");
		JSONObject ncovListObj = JSON.parseObject(ncovStringList);
		//ȡ��ÿ�յ���ػ�������,���԰�������ݴ洢�����ݿ���
		//[{confirmedNum: 9809, curesNum: 183, date: "2020-01-31", deathsNum: 213, suspectedNum: 15238}]
		JSONArray provinces = ncovListObj.getJSONArray("provinces");
//		System.out.println(provinces);
//		System.out.println(provinces);
//		2.����json����
		System.out.println(provinces.size());
		for (int i = 0; i < provinces.size(); i++) {
			
			JSONObject cursesNumList = (JSONObject) provinces.get(i);
//			System.out.println(cursesNumList);
			int curesNum = (int)cursesNumList.getInteger("curesNum");
//			System.out.println("curesNum:"+curesNum);
			String provinceName = cursesNumList.getString("name");
//			Date date = new Date(cursesNumList.getInteger("updateTime"));
			String update = cursesNumList.getString("updateDate");
//			Date dateString = new SimpleDateFormat("yyyy-MM-dd").parse(update);
			int confirmedNum = cursesNumList.getInteger("confirmedNum");
			int confirmedIncr = cursesNumList.getInteger("confirmedIncr");
			int id = cursesNumList.getInteger("id");
			JSONArray cities = cursesNumList.getJSONArray("cities");
			
			for (int j = 0; j < cities.size(); j++) {
//				1.��������
				JSONObject urban = (JSONObject) cities.get(j);
				
				int curesNumcity = (int)urban.getInteger("curesNum");
				String namecity = urban.getString("name");
				int confirmedNumcity = urban.getInteger("confirmedNum");
				int confirmedIncrcity = urban.getInteger("confirmedIncr");
				int jumpLocal = urban.getInteger("jumpLocal");
				int deathsNum = urban.getInteger("deathsNum");
				int treatingNum = urban.getInteger("treatingNum");
				String cid =  urban.getString("id");
				
				String sqlCityString = "update urban set curesNum=?,name=?,confirmedNum=?,confirmedIncr=?,jumpLocal=?,"
						+ "deathsNum=?,treatingNum=? where id=? and cid=?";
				Object[] parmsObjects = {curesNumcity,namecity,confirmedNumcity,confirmedIncrcity,jumpLocal,deathsNum,treatingNum,cid,id};
				
				
				int excute = Dao.execute(sqlCityString, parmsObjects);

			}
//			System.out.println("id:"+id);
//			System.out.println("���ڣ�"+update);
//			System.out.println("����������"+curesNum);
//			System.out.println("ʡ�ݣ�"+provinceName);
//			System.out.println("ȷ��������"+confirmedNum);
//			System.out.println("����������"+confirmedIncr);
////			�޸�����
			String sqlString = "update city set dateString=?,curesNum=?,provinceName=?,confirmedNum=?,confirmedIncr=?"
					+ " where id=?";
			Object[] objects= {update,curesNum,provinceName,confirmedNum,confirmedIncr,id};
			int result = Dao.execute(sqlString, objects);
			System.out.println(result);
////			HashMap<String, Object> hashMap = new HashMap<String, Object>();
////			hashMap.put("dateString", dateString);
////			hashMap.put("provinceName", provinceName);
////			hashMap.put("confirmedNum", confirmedNum);
////			hashMap.put("curesNum", curesNum);
////			hashMap.put("confirmedIncr", confirmedIncr);
////			hashMap.put("id",id);
////			Dao.insertObj("city", hashMap);
			System.out.println("***********************************************");
//		}
//		System.out.println(provinces.get(0));
		
//		System.out.println("curseNum:"+cursesNumList.getInteger("curesNum"));
//		for (Object object : provinces) {
//			System.out.println(object);
//		}
		
//		System.out.println(doc);
		
//		System.out.println(jsonObject);
//		System.out.println(provinces);
//		System.out.println(nationwideIncr);
		
	}
	}
	}
	
