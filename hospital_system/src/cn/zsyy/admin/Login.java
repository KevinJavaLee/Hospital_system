package cn.zsyy.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/admin/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String sqlStr = "select * from user where isDel='false' and  username=? and password=?";
		String[] params = {username,password};
		ArrayList<HashMap<String, Object>> result = Dao.query(sqlStr, params);
		
		System.out.println(result);
		if(result.size()>0) {
			System.out.println("正确");
			HashMap<String, Object> user = result.get(0);
			String userType = (String) user.get("userType");
			System.out.println("userType:"+userType);
			//���õ�¼״̬
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("username", username);
			session.setAttribute("userType", userType);
			//��ʾ��¼�ɹ���Ϣ
			request.setAttribute("httpUrl", "/admin/Index");
			request.setAttribute("info", "登录成功，正在跳转页面");
			request.setAttribute("title", "登录成功");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			
		}else {
			System.out.println("登录失败");
			
			request.setAttribute("httpUrl", "/admin/login");
			request.setAttribute("info", "登录失败，密码错误或者用户名不存在");
			request.setAttribute("title", "登录成功！");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
	}

}
