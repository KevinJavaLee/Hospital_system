package cn.zsyy.admin.reservation;

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
@WebServlet("/admin/reservation/reservelist")
public class ReserveList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String sqlAutoExcuteString ="update reservation set r_status='未就诊'   where r_isDel='false' and r_status='待诊'  and r_time<(select CURRENT_DATE)";
		int resultAuto=Dao.execute(sqlAutoExcuteString);
		String page = request.getParameter("page");
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
		if (!starttime.equals("")&&!endtime.equals("")) {
			
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time BETWEEN '"+starttime+"' and '"+endtime+"' order by res.r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time BETWEEN '"+starttime+"' and '"+endtime+"'";
		} else if(!starttime.equals("")&&endtime.equals("")){
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time > '"+starttime+"' order by res.r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time >'"+starttime+"'";
		} else if (starttime.equals("")&&!endtime.equals("")) {
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time  < '"+endtime+"' order by res.r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' and r_time  < '"+endtime+"'";
		} else {
			sqlString = "SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false' order by res.r_time desc limit "+num+",5";
			sqlSize ="SELECT res.r_id,p.p_name,p.p_sex,p.p_age,p.p_tel,res.r_time,sec.s_name,u.`name`,res.r_status,res.registernum FROM `reservation` res,patient p,user u,section sec where res.pid = p.p_id and res.uid = u.id and u.sectionid = sec.s_id and res.r_isDel='false'";
			
		}
		
//		3.���ò���
		ArrayList<HashMap<String, Object>> reservelist = new ArrayList<HashMap<String,Object>>();
		reservelist = Dao.query(sqlString);
//		
		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
//		��ѯ���������� 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
//		System.out.println(listNum.size());
//		System.out.println(allpage);
		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
		
		
		request.setAttribute("reservelist", reservelist);
		request.setAttribute("page", page);
		//		����ת��
		request.getRequestDispatcher("/admin/reservation/reservelist.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
