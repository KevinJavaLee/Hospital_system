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
 * Servlet implementation class ReserveList
 */
@WebServlet("/admin/doctor/reservelist")
public class MyReserveList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReserveList() {
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
		String page = request.getParameter("page");
		String username = request.getParameter("username");
		System.out.println("username:"+username);
		if (page == null) {
			page = "1";
		}
		

		String sqlString = "";
		String sqlSize = "";
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		if(starttime==null) {
			starttime = "";
		}
		if(endtime ==null)
		{
			endtime = "";
		}
		
		System.out.println("starttime:"+starttime);
		System.out.println("endtime:"+endtime);
		int num = (Integer.parseInt(page) - 1) * 5;
		if (!starttime.equals("")&&!endtime.equals("")&&username!=null) {
			
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' and r_time BETWEEN '"+starttime+"' and '"+endtime+"' order by r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' and r_time BETWEEN '"+starttime+"' and '"+endtime+"'";
		} else if(!starttime.equals("")&&endtime.equals("")&&username!=null){
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' and r_time > '"+starttime+"' order by r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' and r_time >'"+starttime+"'";
		} else if (starttime.equals("")&&!endtime.equals("")&&username!=null) {
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.uesrname='"+username+"' and r_time  < '"+endtime+"' order by r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' and r_time  < '"+endtime+"'";
		} else {
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"' order by r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and res.r_status='待诊'  and u.username='"+username+"'";
			
		}
		

		ArrayList<HashMap<String, Object>> reservelist = new ArrayList<HashMap<String,Object>>();
		reservelist = Dao.query(sqlString);

		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();

		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);

		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
		request.setAttribute("username", username);
		System.out.println("name:"+username);
		
		request.setAttribute("reservelist", reservelist);
		request.setAttribute("page", page);

		request.getRequestDispatcher("/admin/doctor/reservelist.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		doGet(request, response);
	}

}
