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
@WebServlet("/admin/section/addsection")
public class AddSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSection() {
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
		request.getRequestDispatcher("/admin/section/addsection.jsp").forward(request, response);
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
		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = fm.format(date);
		String name = request.getParameter("sectionname");
		String specialty = request.getParameter("specialty");
		String sectionType = request.getParameter("sectionType");
		String content = request.getParameter("content");
		HashMap<String, Object> section = new HashMap<String, Object>();
		section.put("s_time", dateString);
		section.put("s_name", name);
		section.put("specialty", specialty);
		section.put("s_type", sectionType);
		section.put("s_brief", content);
		section.put("s_isDel", "false");
		int result = Dao.insertObj("section", section);
		
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/section/sectionlist");
			request.setAttribute("info", "添加科室信息成功，正在跳转页面！");
			request.setAttribute("title", "添加科室信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/section/sectionlist");
			request.setAttribute("info", "添加科室信息失败，正在跳转页面！");
			request.setAttribute("title", "添加科室信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
	}

}
