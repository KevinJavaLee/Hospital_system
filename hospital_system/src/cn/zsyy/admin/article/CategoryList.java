package cn.zsyy.admin.article;

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
 * Servlet implementation class CategoryList
 */
@WebServlet("/admin/article/categorylist")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryList() {
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
//		
		String likeuser = request.getParameter("likeuser");
		System.out.println(likeuser);
		
		int num = (Integer.parseInt(page) - 1) * 5;
		if (likeuser != null) {
			sqlString = "select * from category  where  c_category like'%"+likeuser+"%' order by c_id desc limit "+num+",5";
			sqlSize = "select * from category where  c_category like '%"+likeuser+"%'";
		} 
		else {
			 sqlString = "SELECT c1.c_id,c1.c_category,c1.c_brief,c1.c_parentid,c2.c_category  parentname  from category  c1  left JOIN category  c2 on c1.c_parentid = c2.c_id order by c1.c_id desc LIMIT "+num+" ,5;";
			 sqlSize = "select * from category ";
			 System.out.println("ִelse");
		}
	   
		
		ArrayList<HashMap<String, Object>> categorylist = new ArrayList<HashMap<String,Object>>();
		categorylist = Dao.query(sqlString);
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
		
		
		request.setAttribute("categorylist", categorylist);
		System.out.println(categorylist);
		request.setAttribute("page", page);
//		2.����ת��
		request.getRequestDispatcher("/admin/article/categorylist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
