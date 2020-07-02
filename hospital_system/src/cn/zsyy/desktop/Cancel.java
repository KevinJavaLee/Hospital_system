package cn.zsyy.desktop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class Cancel
 */
@WebServlet("/desktop/cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String id = request.getParameter("id");
		String userid = request.getParameter("userid");
		String time = request.getParameter("time");
		String datetime="";
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
			datetime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("id:"+id);
		System.out.println("userid:"+userid);
		System.out.println("time:"+datetime);
//		1.ȡ��Ԥ��
		String cancelSql = "update reservation set r_isDel='true ' where r_id="+id+" and r_time='"+datetime+"'";
		int cancelResult = Dao.execute(cancelSql);
		
//		2.�޸��Ű�ƻ��е���ԤԼ��
		String sqlString ="select * from scheduling where time=? and userid=? and isDel='false' ";
		Object[] objects= {datetime,userid};
		ArrayList<HashMap<String, Object>> result = Dao.query(sqlString,objects);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			hashMap = result.get(0);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		int confirmedNum = (int) hashMap.get("confirmnum");
		if (confirmedNum > 0) {
			confirmedNum -= 1;
		}
		String updateSql = "update scheduling set confirmnum="+confirmedNum+" where userid="+userid+" and isDel='false' and time='"+datetime+"'";
		int updateResult = Dao.execute(updateSql);
		
		if (cancelResult > 0 && updateResult > 0) {
			request.setAttribute("httpUrl", "/desktop/myreservation");
			request.setAttribute("info", "取消预约成功，正在跳转页面！");
			request.setAttribute("title", "取消预约");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/userlist");
			request.setAttribute("info", "取消预约信息失败，正在跳转页面");
			request.setAttribute("title", "取消预约");
			request.getRequestDispatcher("/desktop/myreservation").forward(request, response);
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
