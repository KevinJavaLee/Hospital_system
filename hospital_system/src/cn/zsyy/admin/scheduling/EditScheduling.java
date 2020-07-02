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
@WebServlet("/admin/scheduling/editscheduling")
public class EditScheduling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditScheduling() {
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
		
		String id = request.getParameter("id");
//		
		String sql = "select * from scheduling sche,user u  where sche.userid = u.id and sche.sid="+id;
		ArrayList<HashMap<String, Object>> sche = new ArrayList<HashMap<String,Object>>();
		sche = Dao.query(sql);
		HashMap<String, Object> result = sche.get(0);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/admin/scheduling/editscheduling.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String userid = request.getParameter("userid");
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		String time = request.getParameter("time");
		String currentDate = request.getParameter("currentdate");
		System.out.println("id:"+id);
		System.out.println("currentDate:"+currentDate);
		System.out.println("time:"+time);
		
		String sqlEditString ="SELECT count(*) as num  FROM reservation where uid="+userid+" and r_isDel='false' and r_time='"+currentDate+"'";
		ArrayList<HashMap<String, Object>> editList = new ArrayList<HashMap<String,Object>>();
		editList = Dao.query(sqlEditString);
		long exitNum =  (long) editList.get(0).get("num");
		System.out.println(exitNum);
		if (currentDate.equals(time))
		{
			if (exitNum < Integer.parseInt(num)) {
				String sqlString = "update scheduling set time=?,num=?,userid=?,isDel=? where sid="+id;
				Object[] objects = {time,num,userid,"false"};
				
				int result = Dao.execute(sqlString,objects);
				if (result > 0) {
					request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
					request.setAttribute("info", "修改排班信息成功，正在跳转页面！");
					request.setAttribute("title", "修改排班信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}else {
					request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
					request.setAttribute("info", "修改排班信息失败，正在跳转页面！");
					request.setAttribute("title", "修改排班信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}
			}
			else {
				request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
				request.setAttribute("info", "修改排班信息失败，修改排班人数不能少于已经预约的人数正在跳转页面！");
				request.setAttribute("title", "修改排班信息");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
		}else {
			if (exitNum==0) {
				String sqlString = "update scheduling set time=?,num=?,userid=?,isDel=? where sid="+id;
				Object[] objects = {time,num,userid,"false"};
				
				int result = Dao.execute(sqlString,objects);
				if (result > 0) {
					request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
					request.setAttribute("info", "修改排班信息成功，正在跳转页面！");
					request.setAttribute("title", "修改排班信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}else {
					request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
					request.setAttribute("info", "修改排班信息失败，正在跳转页面！");
					request.setAttribute("title", "修改排班信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}
			}else {
					request.setAttribute("httpUrl", "/admin/scheduling/schedulinglist");
					request.setAttribute("info", "修改排班信息失败，已经有患者预约该医生，正在跳转页面！");
					request.setAttribute("title", "修改排班信息");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
		}
		
//		判断修改排班的人数不应该少于
//		String content = request.getParameter("content");
		
		
		
		
		
		
		
	}

}
