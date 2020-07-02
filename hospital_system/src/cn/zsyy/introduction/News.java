package cn.zsyy.introduction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zsyy.db.Dao;
import sun.awt.RepaintArea;

/**
 * Servlet implementation class News
 */
@WebServlet("/introduction/news")
public class News extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public News() {
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
		String sqString = "select * from introduction";
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		result = Dao.query(sqString);
		HashMap<String, Object> news = result.get(0);
		request.setAttribute("news", news);
		response.getWriter().println("ת���ɹ���");
		request.getRequestDispatcher("/admin/introduction/news.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().println("�޸ĳɹ���");
		String id  = request.getParameter("id");
		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = fm.format(date);
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
//		3.���в���
		String sqlString = "update introduction set title=?,content=?,time=?,author=? where id="+id;
		Object[] objects = {title,content,dateString,author};
		
		int result = Dao.execute(sqlString,objects);
		
		if (result > 0) {
			request.setAttribute("httpUrl", "/introduction/news");
			request.setAttribute("info", "修改医院简介新闻成功！");
			request.setAttribute("title", "修改简介");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/introduction/news");
			request.setAttribute("info", "修改医院简介新闻失败！");
			request.setAttribute("title", "修改简介");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
	}

}
