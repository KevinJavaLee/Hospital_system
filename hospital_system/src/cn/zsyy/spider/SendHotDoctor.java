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
 * Servlet implementation class SendTable
 */
@WebServlet("/hotdoctors")
public class SendHotDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendHotDoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		//锟斤拷锟斤拷锟斤拷锟叫碉拷锟斤拷锟斤拷锟�
		response.addHeader("Access-Control-Allow-Origin", "*");
		//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫碉拷锟斤拷锟斤拷
		response.addHeader("Access-Control-Allow-Content-Type", "*");
		
		String sql = "select name from user where userType='doctor' and isDel='false' ";
		ArrayList<HashMap<String, Object>> info  = new ArrayList<HashMap<String,Object>>();
		info = Dao.query(sql);
		String data = JSON.toJSONString(info);
		response.getWriter().println(data);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
