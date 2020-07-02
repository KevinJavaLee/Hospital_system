package cn.zsyy.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/desktop/register")
public class PatientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientRegister() {
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
		//request.getContextPath()
		request.getRequestDispatcher("/admin/desktop/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String brief = request.getParameter("brief");
		String phone = request.getParameter("phone");
		String repass = request.getParameter("repassword");
//		System.out.println("name:"+name);
//		System.out.println("sex:"+sex);
//		System.out.println("age:"+age);
//		System.out.println("brief:"+brief);
//		System.out.println("phone:"+phone);
//		System.out.println("repassword:"+repass);
//		System.out.println(username);
//		System.out.println(password);
		//��ѯ�����Ƿ��Ѵ����û���
		String strSQL= "select p_username from patient where p_username= ?";
		String[] params = {username};
		ArrayList<HashMap<String, Object>> result = Dao.query(strSQL,params);
		if(result.size()>0) {
			System.out.println("���û����Ѿ�ע���");
			request.setAttribute("title", "用户注册");
			request.setAttribute("info", "用户注册失败，该用户已经存在！");
			request.setAttribute("httpUrl","/desktop/register");
		}else {
			System.out.println("ע��ɹ�");
			//�����û���Ϣ�����
			HashMap<String, Object> patient = new HashMap<>();
			patient.put("p_username", username);
			patient.put("p_password", password);
			patient.put("p_name", name);
			patient.put("p_sex", sex);
			patient.put("p_age", age);
			patient.put("p_brief", brief);
			patient.put("p_tel", phone);
			patient.put("userType", "patient");
			patient.put("p_isDel", "false");
			
			Dao.insertObj("patient", patient);
			request.setAttribute("title", "用户注册");
			request.setAttribute("info", "用户注册成功，请先登录！");
			request.setAttribute("httpUrl","/desktop/login");
		}
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		
	}

}
