package cn.zsyy.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class articleList
 */
@WebServlet("/json/doctorList")
public class doctorList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctorList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.设置字符编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		//允许请求所有的类型
		response.addHeader("Access-Control-Allow-Content-Type", "*");
		String sqlString ="select u.username,u.`name`,u.sex,u.age,u.brief,u.imgurl,sec.s_type,sec.s_name,sec.specialty from user u,section sec where isDel='false' and  u.sectionid = sec.s_id and sec.s_isDel='false'";
		ArrayList<HashMap<String, Object>> articleList = new ArrayList<HashMap<String,Object>>();
		articleList = Dao.query(sqlString);
		String jsonString = JSON.toJSONString(articleList);
		response.getWriter().println(jsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
