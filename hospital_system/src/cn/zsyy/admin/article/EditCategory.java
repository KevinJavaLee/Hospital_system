package cn.zsyy.admin.article;

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
 * Servlet implementation class EditArticle
 */
@WebServlet("/admin/article/editcategory")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
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

		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		System.out.println("id:"+id);
//		2.��ѯ����
		String sqlString = " select * from category where c_id="+id;
		String sqlStr = "select * from category where c_id!="+id;
		ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String,Object>>();
		clist = Dao.query(sqlStr);
		
		ArrayList<HashMap<String, Object>> cate = Dao.query(sqlString);
		HashMap<String, Object> category = cate.get(0);
		System.out.println(category);
		request.setAttribute("category", category);
		request.setAttribute("clist", clist);
//		response.getWriter().println("�༭�ɹ���");
		request.getRequestDispatcher("/admin/article/editcategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
//		2.��ȡ����
		String category = request.getParameter("category");
		String brief = request.getParameter("brief");
		String parentid = request.getParameter("parentid");
		String id = request.getParameter("cid");
		System.out.println("id:"+id);
		System.out.println("parentid:"+parentid);
		String sqlString = "update category set c_category=?,c_brief=?,c_parentid=? where c_id="+id;
		Object[] objects = {category,brief,parentid};
		int result = Dao.execute(sqlString, objects);
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/article/categorylist");
			request.setAttribute("info", "修改文章类别信息成功");
			request.setAttribute("title", "修改文章类别");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		else {
			request.setAttribute("httpUrl", "/admin/article/categorylist");
			request.setAttribute("info", "修改文章类别信息失败");
			request.setAttribute("title", "修改文章类别");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		
//		response.getWriter().println("�޸ĳɹ�");
		
//		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}

}
