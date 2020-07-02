package cn.zsyy.admin.article;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class DelArticle
 */
@WebServlet("/admin/article/delarticle")
public class DelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelArticle() {
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
		String id = request.getParameter("id");
		String sqString ="delete from article where a_id="+id;
		int result = Dao.execute(sqString);
		if (result > 0 ) {
			request.setAttribute("httpUrl", "/admin/article/articlelist");
			request.setAttribute("info", "删除文章信息成功，正在跳转页面");
			request.setAttribute("title", "删除文章");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/article/categorylist");
			request.setAttribute("info", "删除信息失败，正在跳转页面");
			request.setAttribute("title", "删除文章信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			
		}
//		response.getWriter().println("id:"+id+"ɾ���ɹ���");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
//			2.��ȡ����
			String[] idList = request.getParameterValues("ids[]");
			System.out.println(idList);
			
			String sqlString = "delete from article where a_id in(";
			
			try {
				for (int i = 0; i < idList.length; i++) {
					if (i < idList.length - 1) {
						sqlString = sqlString + idList[i]+",";
					}
					else {
						sqlString = sqlString + idList[i] + ")";
						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("����Ϊ��");
			}
			
//			3.ִ��sql ���
			int execute = Dao.execute(sqlString);
			if (execute == 0) {
				System.out.println("ɾ��ʧ��");
			}
			System.out.println(sqlString);
		
	}

}
