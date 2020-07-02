package cn.zsyy.article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.RequestGroupInfo;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class Article
 */
@WebServlet("/article/*")
public class Article extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Article() {
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
		String pathInfo = request.getPathInfo();
		String id = pathInfo.substring(3,pathInfo.length()-5);
		if (id.matches("\\d+")) {
			System.out.println("id:"+id);
//			2.�����ݿ��в�ѯ����
			String sqlString = "select * from article where a_id=?";
			Object[] objects = {id};
			ArrayList<HashMap<String, Object>> result = Dao.query(sqlString, objects);
			HashMap<String, Object> article = result.get(0);
//			3.��������
			request.setAttribute("article", article);
			request.getRequestDispatcher("/articlelist/article.jsp").forward(request, response);
			response.getWriter().println(pathInfo);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
