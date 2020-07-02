package cn.zsyy.admin.patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/admin/patient/patientlist")
public class PatientList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.设置编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		
		String sqlString = "";
		String sqlSize = "";
		String userType = request.getParameter("userType");
		String likeuser = request.getParameter("likeuser");
		System.out.println(likeuser);
//		if (userType==null) {
//			userType = "all";
//		}
		int num = (Integer.parseInt(page) - 1) * 5;
		if (likeuser !=null&&userType!=null) {
			if (userType.equals("name")) {
				sqlString = "select * from patient  where p_isDel='false' and p_name like'%"+likeuser+"%' order by p_id desc limit "+num+",5";
				sqlSize = "select * from patient where p_isDel ='false' and p_name like'%"+likeuser+"%'";
			}else if(userType.equals("username")) {
				sqlString = "select * from patient  where p_isDel='false' and p_username like'%"+likeuser+"%' order by p_id desc limit "+num+",5";
				sqlSize = "select * from patient where p_isDel ='false' and p_username like'%"+likeuser+"%'";
			}
			
			
		} else {
			
			 sqlString = "select * from patient  where p_isDel='false' order by p_id desc limit "+num+",5";
			 sqlSize = "select * from patient where p_isDel ='false'";
		}
	   
		
		ArrayList<HashMap<String, Object>> patientlist = new ArrayList<HashMap<String,Object>>();
		patientlist = Dao.query(sqlString);
//		
		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
//		查询的数据总数 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
//		System.out.println(listNum.size());
//		System.out.println(allpage);
		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
		
		
		request.setAttribute("patientlist", patientlist);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/patient/patientlist.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
