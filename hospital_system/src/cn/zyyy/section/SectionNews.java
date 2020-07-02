package cn.zyyy.section;

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
@WebServlet("/section/*")
public class SectionNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.设置字符编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String pathInfo = request.getPathInfo();
		String id = pathInfo.substring(3,pathInfo.length()-5);
		if (id.matches("\\d+")) {
			System.out.println("id:"+id);
//			2.从数据库中查询数据
			String sqlString = "select * from section where s_id=?";
			Object[] objects = {id};
			ArrayList<HashMap<String, Object>> result = Dao.query(sqlString, objects);
			HashMap<String, Object> section = result.get(0);
//			3.设置属性
			request.setAttribute("section", section);
			request.getRequestDispatcher("/articlelist/section.jsp").forward(request, response);
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
