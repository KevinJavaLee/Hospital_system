package cn.zsyy.admin.scheduling;

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
@WebServlet("/admin/scheduling/addscheduling")
public class AddScheduling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddScheduling() {
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
		String sqlString = "select * from user where userType = 'doctor'";
		ArrayList<HashMap<String, Object>> doctorlist = new ArrayList<HashMap<String,Object>>();
		doctorlist = Dao.query(sqlString);
		request.setAttribute("doctorlist", doctorlist);
		request.getRequestDispatcher("/admin/scheduling/addscheduling.jsp").forward(request, response);
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
//		System.out.println("");
		
//		request.getRequestDispatcher("/admin/scheduling/schedulinglist.jsp").forward(request, response);
////		2.��ȡ����
//		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
//		Date date = new Date();
//		String dateString = fm.format(date);
		String id = request.getParameter("doctorid");
		String num = request.getParameter("num");
		String time = request.getParameter("time");
		System.out.println("id:"+id);
//		String content = request.getParameter("content");
		HashMap<String, Object> scheduling = new HashMap<String, Object>();
		scheduling.put("time", time);
		scheduling.put("num", num);
		scheduling.put("userid", id);
		scheduling.put("isDel", "false");
		
//		section.put("s_type", sectionType);
//		section.put("s_brief", content);
//		section.put("s_isDel", "false");
		int result = Dao.insertObj("scheduling", scheduling);
//		
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
			request.setAttribute("info", "添加排班信息成功，正在跳转页面!");
			request.setAttribute("title", "添加排班");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
			request.setAttribute("info", "添加排班信息失败，正在跳转页面!");
			request.setAttribute("title", "添加排班");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
		
	}

}
