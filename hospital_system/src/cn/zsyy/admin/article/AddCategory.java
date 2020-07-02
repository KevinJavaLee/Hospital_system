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
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/article/addcategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
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
		String sqlString = " select * from category";
		ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String,Object>>();
		clist = Dao.query(sqlString);
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("/admin/article/addcategory.jsp").forward(request, response);
		response.getWriter().println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().println("�����Ϣ�ɹ���");

			
			String category = request.getParameter("category");
			String brief = request.getParameter("brief");
			String parentid = request.getParameter("parentid").equals("")?"0":request.getParameter("parentid");
			System.out.println("parentid:"+parentid);

			String sqlUsername ="select * from user where c_category = ?";
			Object[] objects= {category};
			ArrayList<HashMap<String, Object>> result= new ArrayList<HashMap<String,Object>>();
			result = Dao.query(sqlUsername, objects);
			int size = result.size();
			if (size == 0) {

				HashMap<String, Object> categoryMap = new HashMap<String, Object>();
				categoryMap.put("c_category", category);
				categoryMap.put("c_brief", brief);
				categoryMap.put("c_parentid", parentid);
				
				
				Dao.insertObj("category", categoryMap);
				request.setAttribute("httpUrl", "/admin/article/categorylist");
				request.setAttribute("info", "添加文章类别信息成功，正在跳转。");
				request.setAttribute("title", "添加文章类别");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			} 	
			 else {
				request.setAttribute("httpUrl", "/admin/article/categorylist");
				request.setAttribute("info", "添加文章类别信息失败");
				request.setAttribute("title", "添加文章类别");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
	}
	
	
	
}
