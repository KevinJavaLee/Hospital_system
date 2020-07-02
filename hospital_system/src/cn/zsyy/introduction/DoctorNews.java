package cn.zsyy.introduction;

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
 * Servlet implementation class Article
 */
@WebServlet("/introduction/doctornews")
public class DoctorNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
//		String pathInfo = request.getPathInfo();
//		String id = pathInfo.substring(3,pathInfo.length()-5);
//		System.out.println("id:"+id);
//		2.�����ݿ��в�ѯ����
		String sqlString = "select * from user where userType='doctor' ";
//		Object[] objects = {id};
		ArrayList<HashMap<String, Object>> doctornews = new ArrayList<HashMap<String,Object>>();
		 doctornews = Dao.query(sqlString);
		 String jsonString = JSON.toJSONString(doctornews);
		 response.getWriter().println(jsonString);
//		3.��������
//		request.setAttribute("doctornews", doctornews);
//		request.getRequestDispatcher("/articlelist/introduction.jsp").forward(request, response);
//		response.getWriter().println(pathInfo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
