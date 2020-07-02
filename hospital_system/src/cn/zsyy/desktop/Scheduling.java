package cn.zsyy.desktop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class SchedulingList
 */
@WebServlet("/desktop/schedulinglist")
public class Scheduling extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scheduling() {
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
//		response.getWriter().println("�Ű��");
//		2.�õ�ҳ����
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String sqlString = "";
		String sqlSize = "";
		String likeuser="";
		String type="";
//		3.��õ�½���û���
//		HttpSession session = request.getSession();
//		String username = (String) session.getAttribute("username");
//		System.out.println("username:"+username);
		System.out.println(likeuser);
		System.out.println(type);
		likeuser = request.getParameter("likeuser");
		type = request.getParameter("type");
		System.out.println("likeuser:"+likeuser);
		System.out.println("type:"+type);
//		if (likeuser.equals("")) {
//			likeuser = null;
//		}
//		if (type.equals("")) {
//			type = null;
//		}
//		System.out.println("likeuser:"+likeuser);
//		System.out.println("sectionType:"+sectionType);
//		�������ʱ��
		Date date=new Date();//ȡʱ��
		Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    calendar.add(calendar.DATE,5);
	    date=calendar.getTime(); 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String futureTime = formatter.format(date);
	    String currentTime = formatter.format(new Date());
		System.out.println();
		int num = (Integer.parseInt(page) - 1) * 5;
		
		
		if (type!=null&&likeuser!=null) {
			
			if (type.equals("doctor"))
			{
				sqlString = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and u.name like '%"+likeuser+"%' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"' order by s.time desc LIMIT "+num+" ,5";
				sqlSize = "select   s.sid,s.time,s.num,s.confirmnum,`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and u.name like '%"+likeuser+"%' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"'";
			} else if (type.equals("section")) {
				sqlString = "select   s.sid,s.time,s.confirmnum,s.num,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and sec.s_name like '%"+likeuser+"%' and s.isDel ='false'  and s.time >='"+currentTime+"' and s.time<='"+futureTime+"' order by s.time desc LIMIT "+num+" ,5";
				sqlSize = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor'and sec.s_name like '%"+likeuser+"%' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"'";
				
			}else {
				sqlString = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"' order by s.time desc LIMIT "+num+" ,5;";
				 sqlSize = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"'";
			}
			
		}else {
			 sqlString = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"' order by s.time desc LIMIT "+num+" ,5;";
			 sqlSize = "select   s.sid,s.time,s.num,s.confirmnum,u.`name`,sec.s_name  from scheduling s, user u,section sec where s.userid = u.id and u.sectionid =sec.s_id and u.userType ='doctor' and s.isDel ='false' and s.time >='"+currentTime+"' and s.time<='"+futureTime+"'";
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
//			System.out.println(listNum.size());
//			System.out.println(allpage);
//			1.����������
			request.setAttribute("total", totalSize);
//			2.������ҳ��
			request.setAttribute("allpage", allpage);
				
				
			request.setAttribute("schedulinglist", schedulinglist);
			System.out.println(schedulinglist);
			request.setAttribute("page", page);
			
			request.getRequestDispatcher("/admin/desktop/schedulinglist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
