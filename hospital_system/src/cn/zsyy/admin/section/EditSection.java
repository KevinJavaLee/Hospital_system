package cn.zsyy.admin.section;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class AddSection
 */
@WebServlet("/admin/section/editsection")
public class EditSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSection() {
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
//		2.��ȡ����
		String id = request.getParameter("id");
		String sqlString = " select * from section where s_id="+id;
		
		
		
		ArrayList<HashMap<String, Object>> sectionlist = Dao.query(sqlString);
		HashMap<String, Object> section = sectionlist.get(0);
		System.out.println(section);
		request.setAttribute("section", section);
		
		request.getRequestDispatcher("/admin/section/editsection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().println("�޸ĳɹ�");
//		2.��ȡ����
		String id  = request.getParameter("id");
		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = fm.format(date);
		String name = request.getParameter("sectionname");
		String specialty = request.getParameter("specialty");
		String sectionType = request.getParameter("sectionType");
		String content = request.getParameter("content");
//		3.���в���
		String sqlString = "update section set s_name=?,s_brief=?,s_time=?,specialty=?,s_isDel=?,s_type=? where s_id="+id;
		Object[] objects = {name,content,dateString,specialty,"false",sectionType};
		
		int result = Dao.execute(sqlString,objects);
		
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/section/sectionlist");
			request.setAttribute("info", "修改科室信息成功，正在跳转页面！");
			request.setAttribute("title", "修改科室");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/section/sectionlist");
			request.setAttribute("info", "修改科室信息失败，正在跳转页面！");
			request.setAttribute("title", "修改科室");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
	}

}
