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

/**
 * Servlet implementation class AddArticle
 */
@WebServlet("/admin/article/addarticle")
@MultipartConfig
public class AddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArticle() {
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
//		2. ��ѯ���еķ���
		String sqlString = " select * from category";
		ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String,Object>>();
		clist = Dao.query(sqlString);
		request.setAttribute("clist", clist);
//		3.����ת��
		request.getRequestDispatcher("/admin/article/addarticle.jsp").forward(request, response);
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
		SimpleDateFormat fm=new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateString = fm.format(date);
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String parentid = request.getParameter("parentid").equals("")?"0":request.getParameter("parentid");
		
		String content = request.getParameter("content");
		String titleimgUrl = uploadimg(request, response);
		System.out.println("parentid:"+parentid);

		HashMap<String, Object> article = new HashMap<String, Object>();
		article.put("a_title",title);
		article.put("a_author",author);
		article.put("a_cid",parentid);
		article.put("a_pubtime",dateString);
		article.put("a_content",content);
		article.put("a_title",title);
		article.put("a_readnum", 0);
		article.put("a_titleimg", titleimgUrl);
		int result = Dao.insertObj("article", article);
		if (result > 0) {
			request.setAttribute("httpUrl", "/admin/article/articlelist");
			request.setAttribute("info", "添加文章成功！");
			request.setAttribute("title", "添加文章");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/article/articlelist");
			request.setAttribute("info", "添加文章失败，正在跳转页面");
			request.setAttribute("title", "添加文章");
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
	
	
	String getfilename(String header){
		String filename = null;
		String[] splitArr = header.split(";");
		String[] split = splitArr[2].split("=");
		//System.out.println(splitArr[2]);
		filename = split[1].substring(1, split[1].length()-1);
		return filename;
	}

}
