package cn.zsyy.admin.article;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cn.zsyy.db.Dao;
import cn.zsyy.json.articleList;

/**
 * Servlet implementation class EditArticle
 */
@WebServlet("/admin/article/editarticle")
@MultipartConfig
public class EditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticle() {
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
		String sqlString = " select * from article where a_id="+id;
		String sqlStr = "select * from category ";
		ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String,Object>>();
		clist = Dao.query(sqlStr);
		
		ArrayList<HashMap<String, Object>> articlelist = Dao.query(sqlString);
		HashMap<String, Object> article = articlelist.get(0);
		System.out.println(article);
		request.setAttribute("article", article);
		request.setAttribute("clist", clist);
//		response.getWriter().println("�༭�ɹ���");
//		response.getWriter().println("id:"+id);
//		response.getWriter().println("�޸ĳɹ���");
		request.getRequestDispatcher("/admin/article/editarticle.jsp").forward(request, response);
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
		String id = request.getParameter("id");
		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = fm.format(date);
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
//		String parentid = request.getParameter("parentid");
		String parentid = request.getParameter("parentid").equals("")?"0":request.getParameter("parentid");
		int  random =  (int)(Math.random()*100);
		String content = request.getParameter("content");
//		System.out.println(parentid);
		String titleimgUrl = uploadimg(request, response);
		System.out.println("parentid:"+parentid);
		
		
		String sqlString = "update article set a_title=?,a_pubtime=?,a_readnum=?,a_content=?,a_author=?,a_cid=?,a_titleimg=?where a_id="+id;
		Object[] objects = {title,dateString,random,content,author,parentid,titleimgUrl};
		
		int result = Dao.execute(sqlString,objects);
		System.out.println("result:"+result);
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/article/articlelist");
			request.setAttribute("info", "修改文章信息成功，正在跳转页面");
			request.setAttribute("title", "修改文章");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/article/articlelist");
			request.setAttribute("info", "修改文章信息失败，正在跳转页面");
			request.setAttribute("title", "修改文章");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
	}
	String uploadimg(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		
		
		Part part = request.getPart("titleimg");
		
		
		String header = part.getHeader("content-disposition");
		
		String filename = getfilename(header);
		
		
		String realPath = request.getServletContext().getRealPath("upload");
		
		long time = new Date().getTime();
		
		
		String path = realPath+"/"+time+filename;
		
		part.write(path);
		
		
		String  httpUrl = request.getContextPath()+"/upload/"+time+filename;
		return httpUrl;
	}
	
	//��ͷ���ַ�������ȡ�ļ���
	String getfilename(String header){
		String filename = null;
		String[] splitArr = header.split(";");
		String[] split = splitArr[2].split("=");
		//System.out.println(splitArr[2]);
		filename = split[1].substring(1, split[1].length()-1);
		return filename;
	}

	

}
