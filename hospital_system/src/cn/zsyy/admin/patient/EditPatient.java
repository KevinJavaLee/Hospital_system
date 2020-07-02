package cn.zsyy.admin.patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.I2F;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/patient/editpatient")
public class EditPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPatient() {
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
		String id = request.getParameter("id");
		String sqlString = "select * from patient where p_id="+id;
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		result = Dao.query(sqlString);
		 HashMap<String, Object> patientlist  = new HashMap<String, Object>();
		  patientlist = result.get(0);
		request.setAttribute("patientlist", patientlist);
		request.getRequestDispatcher("/admin/patient/editpatient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//2.��ȡ��Ϣ
			
			String username = request.getParameter("username");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String brief = request.getParameter("brief");
			String tel = request.getParameter("tel");
			
			System.out.println(username);
			response.getWriter().println("�޸ĳɹ�");
//			1.�ж����ݿ����Ƿ������ͬ���û���
			String sqlString ="update patient set p_name=?,p_password=?,p_sex=?,p_age=?,p_brief=?,p_tel=? where p_username = ?";
			Object[] objects= {name,password,sex,age,brief,tel,username};
//			ArrayList<HashMap<String, Object>> result= new ArrayList<HashMap<String,Object>>();
//			result = Dao.query(sqlUsername, objects);
//			int size = result.size();
			int result = Dao.execute(sqlString,objects);
			
			if (result > 0) {
				request.setAttribute("httpUrl", "/admin/patient/patientlist");
				request.setAttribute("info", "修改患者信息成功，正在跳转页面！");
				request.setAttribute("title", "修改患者信息");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}else {
				request.setAttribute("httpUrl", "/admin/patient/patientlist");
				request.setAttribute("info", "修改患者信息失败，正在跳转页面！");
				request.setAttribute("title", "修改患者信息");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
	}

}
