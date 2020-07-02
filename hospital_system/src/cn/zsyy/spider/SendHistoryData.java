package cn.zsyy.spider;

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
 * Servlet implementation class SendData
 */
@WebServlet("/sendhistorydata")
public class SendHistoryData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendHistoryData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			1.设置字符编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		//允许所有的域访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		//允许请求所有的类型
		response.addHeader("Access-Control-Allow-Content-Type", "*");
		String sqlString ="SELECT distinct date,curesNum,confirmedNum,deathsNum,suspectedNum from history order by date desc limit 20";
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
