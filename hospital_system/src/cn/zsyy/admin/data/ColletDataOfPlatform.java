package cn.zsyy.admin.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class ColletDataOfPlatform
 */
@WebServlet("/data/colletDataOfPlatform")
public class ColletDataOfPlatform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColletDataOfPlatform() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
//		统计平台的医生数量
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userNumSqlString="select count(*) as num from user where userType='doctor' and isDel='false'";
		ArrayList<HashMap<String, Object>> user = new ArrayList<HashMap<String,Object>>();
		user = Dao.query(userNumSqlString);
		hashMap.put("name", "医生数量");
		hashMap.put("value", user.get(0).get("num"));
		list.add(hashMap);
		
		Map<String, Object> hashMap1 = new HashMap<String, Object>();
//		统计平台的患者数量
		String patientSqlString = "select count(*) as num from patient where p_isDel='false'";
		user = Dao.query(patientSqlString);
		hashMap1.put("name", "患者数量");
		hashMap1.put("value", user.get(0).get("num"));
		list.add(hashMap1);
		
		Map<String, Object> hashMap2 = new HashMap<String, Object>();
//		今日排班的医生数量
		String doctorNumSqlString = "SELECT count(*) as num  FROM `scheduling` where isDel='false' and time in(select  CURRENT_DATE)";
		user = Dao.query(doctorNumSqlString);
		hashMap2.put("name", "当日医生排班数");
		hashMap2.put("value", user.get(0).get("num"));
		list.add(hashMap2);
		
		Map<String, Object> hashMap3 = new HashMap<String, Object>();
//		今日预约患者数
		String reservationSqlString = "select count(*) as num  from reservation where r_isDel='false' and r_time in(select CURRENT_DATE)";
		user = Dao.query(reservationSqlString);
		hashMap3.put("name", "当日预约患者数");
		hashMap3.put("value", user.get(0).get("num"));
		list.add(hashMap3);
		
		Map<String, Object> hashMap4 = new HashMap<String, Object>();
//		今日取消预约数
		String cacelSqlString = "select count(*) as num  from reservation where r_isDel='true' and r_time in(select CURRENT_DATE)";
		user = Dao.query(cacelSqlString);
		hashMap4.put("name", "当日取消预约患者数");
		hashMap4.put("value", user.get(0).get("num"));
		list.add(hashMap4);
		
		Map<String, Object> hashMap5 = new HashMap<String, Object>();
//		前日未及时就诊人数
		String unCompleteSqlString = "select count(*) as num  from reservation where r_isDel='false' and r_time in(select CURRENT_DATE) and r_status='未就诊'";
		user = Dao.query(unCompleteSqlString);
		hashMap5.put("name", "前日未就诊患者数");
		hashMap5.put("value", user.get(0).get("num"));
		list.add(hashMap5);
		
		
		
		String jsonString = JSON.toJSONString(list);
////		System.out.println(JSON.toJSONString(hashMap));
		response.getWriter().println(jsonString);
//		response.getWriter().println(userNum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
