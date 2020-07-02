package cn.zsyy.admin.scheduling;

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
@WebServlet("/admin/scheduling/schedulinglist")
public class SchedulingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchedulingList() {
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
		response.getWriter().println("�Ű��");
//		2.�õ�ҳ����
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String sqlString = "";
		String sqlSize = "";
		String likeuser = request.getParameter("likeuser");
		String type = request.getParameter("type");
		System.out.println("likeuser:"+likeuser);
		System.out.println("type:"+type);
//		if (sectionType==n) {
//			sectionType = "all";
//		}
//		System.out.println("likeuser:"+likeuser);
//		System.out.println("sectionType:"+sectionType);
		
		int num = (Integer.parseInt(page) - 1) * 5;
		
		
		if (type!=null&&likeuser!=null) {
			
			if (type.equals("doctor"))
			{
				sqlString = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and u.name like '%"+likeuser+"%' and s.isDel ='false' order by time desc LIMIT "+num+" ,5";
				sqlSize = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and u.name like '%"+likeuser+"%' and s.isDel ='false'";
			} else if (type.equals("section")) {
				sqlString = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and sec.s_name like '%"+likeuser+"%' and s.isDel ='false' order by time desc LIMIT "+num+" ,5";
				sqlSize = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and sec.s_name like '%"+likeuser+"%' and s.isDel ='false' ";
				
			}
			
		}   else {
			 sqlString = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false' order by time desc LIMIT "+num+" ,5;";
			 sqlSize = "select   s.sid,s.time,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false'";
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
			System.out.println("page"+page);
//			System.out.println(listNum.size());
//			System.out.println(allpage);
//			1.����������
			request.setAttribute("total", totalSize);
//			2.������ҳ��
			request.setAttribute("allpage", allpage);
				
				
			request.setAttribute("schedulinglist", schedulinglist);
			System.out.println(schedulinglist);
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("/admin/scheduling/schedulinglist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
