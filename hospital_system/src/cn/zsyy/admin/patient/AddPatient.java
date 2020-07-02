package cn.zsyy.admin.patient;

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
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/patient/addpatient")
public class AddPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPatient() {
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
		request.getRequestDispatcher("/admin/patient/addpatient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
			
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String brief = request.getParameter("brief");
			String tel = request.getParameter("tel");
			
			System.out.println(username);
			response.getWriter().println("");
//			1.�ж����ݿ����Ƿ������ͬ���û���
			String sqlUsername ="select * from patient where p_username = ?";
			Object[] objects= {username};
			ArrayList<HashMap<String, Object>> result= new ArrayList<HashMap<String,Object>>();
			result = Dao.query(sqlUsername, objects);
			int size = result.size();
			if (size == 0) {
//				1.�����Ϣ
				HashMap<String, Object> patient = new HashMap<String, Object>();
				patient.put("p_username", username);
				patient.put("p_name", name);
				patient.put("p_password", password);
				patient.put("p_sex", sex);
				patient.put("p_age", age);
				patient.put("p_brief", brief);
				patient.put("p_tel", tel);
				patient.put("p_isDel", "false");
				Dao.insertObj("patient", patient);
				request.setAttribute("httpUrl", "/admin/patient/patientlist");
				request.setAttribute("info", "添加病患信息成功，正在跳转页面");
				request.setAttribute("title", "添加患者信息");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			} else if (result.get(0).get("p_isDel").equals("true") ) {
				String sqlString = "update patient set p_name=?,p_password=?,p_sex=?,p_age=?,p_brief=?,p_tel=?,p_isDel='false' where p_username=?";
				Object[] paraObjects= {name,password,sex,age,brief,tel,username};
				int excute = Dao.execute(sqlString,paraObjects);
				if (excute > 0) {
					request.setAttribute("httpUrl", "/admin/patient/patientlist");
					request.setAttribute("info", "添加患者信息成功，正在跳转页面");
					request.setAttribute("title", "添加患者信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				} else {
					request.setAttribute("httpUrl", "/admin/patient/patientlist");
					request.setAttribute("info", "添加患者信息失败，正在跳转页面");
					request.setAttribute("title", "添加患者信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}
				
				
			} else {
				request.setAttribute("httpUrl", "/admin/patient/patientlist");
				request.setAttribute("info", "添加患者信息失败，正在跳转页面");
				request.setAttribute("title", "添加用户信息");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
	}

}
