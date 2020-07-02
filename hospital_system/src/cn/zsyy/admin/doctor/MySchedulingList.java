package cn.zsyy.admin.doctor;

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
 * Servlet implementation class SchedulingList
 */
@WebServlet("/admin/doctor/schedulinglist")
public class MySchedulingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySchedulingList() {
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
		response.getWriter().println("");
//		2.�õ�ҳ����
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String sqlString = "";
		String sqlSize = "";
		String username = request.getParameter("username");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		System.out.println("username:"+username);
		if(starttime==null) {
			starttime = "";
		}
		if(endtime ==null)
		{
			endtime = "";
		}
		
		int num = (Integer.parseInt(page) - 1) * 5;
		if (!starttime.equals("")&&!endtime.equals("")&&username!=null) {
					
				sqlString = "select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false' and sch.time BETWEEN '"+starttime+"' and '"+endtime+"' order by sch.time desc limit "+num+",5";
				sqlSize ="select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false' and sch.time BETWEEN '"+starttime+"' and '"+endtime+"'";
			} else if(!starttime.equals("")&&endtime.equals("")&&username!=null){
				sqlString = "select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false'  and sch.time >'"+starttime+"' order by sch.time desc limit "+num+",5";
				sqlSize ="select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false'  and sch.time >'"+starttime+"'";
			} else if (starttime.equals("")&&!endtime.equals("")&&username!=null) {
				sqlString = "select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false' and sch.time<'"+endtime+"' order by sch.time desc limit "+num+",5";
				sqlSize ="select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false' and sch.time<'"+endtime+"'";
			} else {
				sqlString = "select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false'  order by sch.time desc limit "+num+",5";
				sqlSize ="select sch.sid,sch.time,sch.num,sec.s_name from scheduling sch,user u ,section sec where sch.userid = u.id and u.sectionid = sec.s_id and u.username='"+username+"' and u.isDel='false' and sch.isDel='false' ";
				
			}
		

		ArrayList<HashMap<String, Object>> schedulinglist = new ArrayList<HashMap<String,Object>>();
		schedulinglist = Dao.query(sqlString);
//				
				
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
//			��ѯ���������� 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
			
		System.out.println(allpage);
		System.out.println("page?"+page);
//		System.out.println(listNum.size());
//		ystem.out.println(allpage);
//		1.����������
		request.setAttribute("total", totalSize);
//		2.������ҳ��
		request.setAttribute("allpage", allpage);
		request.setAttribute("schedulinglist", schedulinglist);
		System.out.println(schedulinglist);
		request.setAttribute("page", page);
		request.setAttribute("username", username);	
		
		request.getRequestDispatcher("/admin/doctor/schedulinglist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		doGet(request, response);
	}

}
