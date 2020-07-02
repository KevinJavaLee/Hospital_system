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
 * Servlet implementation class ArticleList
 */
@WebServlet("/admin/article/articlelist")
public class ArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleList() {
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
//		2.从数据据库中查询数据
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
			sqlString = "SELECT c1.a_id,c1.a_title,c1.a_author,c1.a_readnum,c1.a_pubtime,c2.c_category categoryname from article  c1  left JOIN category  c2 on c1.a_cid = c2.c_id  where c1.a_title like '%"+likeuser+"%' order by c1.a_id DESC limit "+num+",5";
			sqlSize = "select * from article where  a_title like '%"+likeuser+"%'";
		} 
		else {
			 sqlString = "SELECT c1.a_id,c1.a_title,c1.a_author,c1.a_readnum,c1.a_pubtime,c2.c_category categoryname from article  c1  left JOIN category  c2 on c1.a_cid = c2.c_id order by a_id desc LIMIT "+num+" ,5;";
			 sqlSize = "select * from article ";
			 System.out.println("执行else");
		}
	   
		
		ArrayList<HashMap<String, Object>> articlelist = new ArrayList<HashMap<String,Object>>();
		articlelist = Dao.query(sqlString);
//		
		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
//		查询的数据总数 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
		System.out.println(allpage);
		System.out.println("page："+page);
//		System.out.println(listNum.size());
//		System.out.println(allpage);
		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
		
		
		request.setAttribute("articlelist", articlelist);
		System.out.println(articlelist);
		request.setAttribute("page", page);
//		2.请求转发
		request.getRequestDispatcher("/admin/article/articlelist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
