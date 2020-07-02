package cn.zsyy.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sun.javafx.collections.MappingChange.Map;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/admin/userinfo")
@MultipartConfig
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
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
		//		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
//		2.sql ���
		String strSql = "select * from user where username= ?";
		Object[] objects = {username};
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		result = Dao.query(strSql, objects);
		HashMap<String, Object> user = result.get(0);
//		3.sesion����
		session.setAttribute("user", user);
//		3.sesion����
		request.getRequestDispatcher("./userinformation.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//2.��ȡ��Ϣ
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String userType= request.getParameter("userType");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String brief = request.getParameter("brief");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String imgUrl = uploadimg(request, response);
		String sectionid = request.getParameter("sectionid");

		System.out.println(username);
		System.out.println("imgurl:"+imgUrl);

		String sqlString = "update user set username=?,name=?,password=?,userType=?,sex=?,age=?,brief=?,phone=?,mail=?,imgurl=?,sectionid=? where username=?";
		Object[] objects = {username,name,password,userType,sex,age,brief,phone,mail,imgUrl,sectionid,username};
		int excute = Dao.execute(sqlString,objects);
		System.out.println("excute:"+excute);
//		2.������Ϣ
//		request.setAttribute("httpUrl", "./admin/edituser"+username);
		request.setAttribute("httpUrl", "/admin/userlist");
		request.setAttribute("info", "修改用户信息成功，正在跳转！");
		request.setAttribute("title", "修改用户");
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		
	}
	String uploadimg(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//��ȡ����,multipart/form-data�Ĳ���������·�������±�׷��@MultipartConfig
		
		
		//��ȡ�ļ�
		Part part = request.getPart("imgurl");
		
		//��ȡ�ļ�����
		String header = part.getHeader("content-disposition");
		
		String filename = getfilename(header);
		
		//��ȡuploadĿ¼�����λ��
		String realPath = request.getServletContext().getRealPath("upload");
		
		long time = new Date().getTime();
		//д�뵽����ĳ��λ��part.write(����Ĵ���λ��)
		
		String path = realPath+"/"+time+filename;
		
		part.write(path);
		
		//��������ݿ������·��
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
