package cn.zsyy.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class MyReservation
 */
@WebServlet("/desktop/myreservation")
public class MyReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReservation() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String sqlAutoExcuteString ="update reservation set r_status='未就诊' where r_isDel='false' and r_status='待诊' and r_time<(select CURRENT_DATE)";
		int resultAuto=Dao.execute(sqlAutoExcuteString);
//		System.out.println(resultAuto);
//		2.��ѯ����
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
//		2.��ѯ����
		String sqlString = "";
		String sqlSize = "";
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("username"+username);
		
		int num = (Integer.parseInt(page) - 1) * 5;
//		2.sql���
		sqlString ="SELECT res.r_id ,res.r_time,res.r_status,res.uid,u.`name`,u.sex,u.age,sec.s_name,res.registernum FROM `reservation` res,user u,section sec,patient p where res.uid = u.id and u.sectionid = sec.s_id and res.pid= p.p_id and  res.r_isDel='false' and u.isDel='false' and p.p_username='"+username+"' order by res.r_time desc limit "+num+",5";
		sqlSize = "SELECT res.r_id ,res.r_time,res.r_status,res.uid,u.`name`,u.sex,u.age,sec.s_name,res.registernum FROM `reservation` res,user u,section sec,patient p where res.uid = u.id and u.sectionid = sec.s_id and res.pid= p.p_id and  res.r_isDel='false' and u.isDel='false' and p.p_username='"+username+"' order by res.r_time desc";
		
////	3.���ò���
		ArrayList<HashMap<String, Object>> reservelist = new ArrayList<HashMap<String,Object>>();
		reservelist = Dao.query(sqlString);
	//	
		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
	////	��ѯ���������� 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
	//	System.out.println(listNum.size());
	//	System.out.println(allpage);
		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
//		request.setAttribute("username", username);
//		System.out.println("name:"+username);
		
		request.setAttribute("reservelist", reservelist);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/desktop/myreservation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
