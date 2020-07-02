package cn.zsyy.admin;

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
@WebServlet("/admin/userlist")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
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
		if (userType==null) {
			userType = "all";
		}
		int num = (Integer.parseInt(page) - 1) * 5;
		if (likeuser == null&&!userType.equals("all")) {
			sqlString = "select * from user  where isDel='false' and userType='"+userType+"' order by id desc limit "+num+",5";
			sqlSize = "select * from user where isDel ='false' and userType='"+userType+"'";
		} else if(likeuser != null&&!userType.equals("all")) {
			sqlString = "select * from user  where isDel='false' and userType='"+userType+"' and name like '%"+likeuser+"%' order by id desc limit "+num+",5";
			sqlSize = "select * from user where isDel ='false' and userType="+userType+" and name like '%"+likeuser+"%'";
		}else if(likeuser != null&&userType.equals("all")) {
			sqlString = "select * from user  where isDel='false'and name like '%"+likeuser+"%' order by id desc limit "+num+",5";
			sqlSize = "select * from user where isDel ='false' and name like '%"+likeuser+"%'";
		}
		else {
			 sqlString = "select * from user  where isDel='false' order by id desc limit "+num+",5";
			 sqlSize = "select * from user where isDel ='false'";
		}
	   
		
		ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String,Object>>();
		userList = Dao.query(sqlString);
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
		
		
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/userlist.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
