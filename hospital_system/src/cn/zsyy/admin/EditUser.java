package cn.zsyy.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/admin/edituser")
@MultipartConfig
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
//		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println(username);
//		
		String sqlString = "select * from section where s_isDel='false' ";
		ArrayList<HashMap<String, Object>> sectionList = new ArrayList<HashMap<String,Object>>();
		sectionList = Dao.query(sqlString);
		request.setAttribute("sectionList", sectionList);
//		
		String strSql = "select * from user where username = ?";
		Object[] objects = {username};
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		result = Dao.query(strSql, objects);
		HashMap<String, Object> user = result.get(0);
//		3.sesion����
		session.setAttribute("user", user);
//		3.sesion����
		request.getRequestDispatcher("./userinfo.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
