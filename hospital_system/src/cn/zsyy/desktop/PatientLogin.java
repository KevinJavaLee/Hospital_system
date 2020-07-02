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

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/desktop/login")
public class PatientLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientLogin() {
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
//		response.getWriter().println("����ţ�ƣ�");
		request.getRequestDispatcher("/admin/desktop/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		String sqlStr = "select * from patient where p_isDel='false' and  p_username=? and p_password=?";
		String[] params = {username,password};
		ArrayList<HashMap<String, Object>> result = Dao.query(sqlStr, params);
		
		System.out.println(result);
		if(result.size()>0) {
			System.out.println("��¼�ɹ�");
			HashMap<String, Object> patient = result.get(0);
			String userType = (String) patient.get("userType");
			System.out.println("userType:"+userType);
			//���õ�¼״̬
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("username", username);
			session.setAttribute("userType", userType);
			response.getWriter().println("��½�ɹ���");
			//��ʾ��¼�ɹ���Ϣ
			request.setAttribute("httpUrl", "/desktop/Index");
			request.setAttribute("info", "用户登录成功，正在跳转主页！");
			request.setAttribute("title", "用户登录");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			
		}else {
			System.out.println("�û��������벻�ԣ�");
			//��ʾ��¼ʧ��ҳ��
			request.setAttribute("httpUrl", "/desktop/login");
			request.setAttribute("info", "用户登录失败，请重新登录！");
			request.setAttribute("title", "用户登录");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
//		
		
	}

}
