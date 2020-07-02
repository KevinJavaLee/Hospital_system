package cn.zsyy.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.collections.MappingChange.Map;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/desktop/userinfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
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
		//		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("username:"+username);
		response.getWriter().println("������Ϣ");
//		2.sql ���
		
		  String strSql = "select * from patient where p_username= ?"; 
		  Object[] objects = {username}; 
		  ArrayList<HashMap<String, Object>> result = new
		  ArrayList<HashMap<String,Object>>(); result = Dao.query(strSql, objects);
		  HashMap<String, Object> patient = result.get(0); // 3.sesion����
		  session.setAttribute("patient", patient); // 3.sesion����
		  request.getRequestDispatcher("/admin/desktop/userinformation.jsp").forward(request,
		  response);
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//2.��ȡ��Ϣ
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String userType= request.getParameter("userType");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String brief = request.getParameter("brief");
		String tel = request.getParameter("mail");
		
		System.out.println(username);
		System.out.println(name);
		System.out.println(sex);
		System.out.println(age);
		System.out.println(tel);
		System.out.println(brief);
		System.out.println(password);
		System.out.println("");
////		3.����sql �������޸�
		String sqlString = "update patient set p_name=?,p_password=?,userType=?,p_sex=?,p_age=?,p_brief=?,p_tel=? where p_username=?";
		Object[] objects = {name,password,"patient",sex,age,brief,tel,username};
		int excute = Dao.execute(sqlString,objects);
		System.out.println("excute:"+excute);
//		2.������Ϣ
		if (excute > 0) {
//			response.getWriter().println("��Ϣ�޸ĳɹ���");
			request.setAttribute("httpUrl", "/desktop/Index");
			request.setAttribute("info", "修改我的信息成功，正在跳转");
			request.setAttribute("title", "修改信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
//			response.getWriter().println("��Ϣ�޸�ʧ�ܣ�");
			request.setAttribute("httpUrl", "/desktop/Index");
			request.setAttribute("info", "修改我的信息失败，正在跳转页面");
			request.setAttribute("title", "修改信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
	}

}
