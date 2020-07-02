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
import javax.servlet.http.Part;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/adduser")
@MultipartConfig
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
//		2.��ѯ���еĿ�����Ϣ
		String sqlString = "select * from section where s_isDel='false' ";
		ArrayList<HashMap<String, Object>> sectionList = new ArrayList<HashMap<String,Object>>();
		sectionList = Dao.query(sqlString);
		request.setAttribute("sectionList", sectionList);
		request.getRequestDispatcher("/admin/adduser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.�����ַ����뼯
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
//			String imgurl = request.getParameter("imgurl");
			System.out.println(username);
			System.out.println("imgurl:"+imgUrl);
			System.out.println("sectionid:"+sectionid);
			String sqlUsername ="select * from user where username = ?";
			Object[] objects= {username};
			ArrayList<HashMap<String, Object>> result= new ArrayList<HashMap<String,Object>>();
			result = Dao.query(sqlUsername, objects);
			int size = result.size();
			if (size == 0) {
//				
				HashMap<String, Object> user = new HashMap<String, Object>();
				user.put("username", username);
				user.put("name", name);
				user.put("password", password);
				user.put("userType", userType);
				user.put("imgurl", imgUrl);
				user.put("sex", sex);
				user.put("age", age);
				user.put("brief", brief);
				user.put("phone", phone);
				user.put("mail", mail);
				user.put("isDel", "false");
				user.put("sectionid", sectionid);
				Dao.insertObj("user", user);
				request.setAttribute("httpUrl", "/admin/userlist");
				request.setAttribute("info", "添加用户信息成功");
				request.setAttribute("title", "添加用户");
//				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			} else if (result.get(0).get("isDel").equals("true") ) {
				String sqlString = "update user set username=?,name=?,password=?,userType=?,sex=?,age=?,brief=?,phone=?,mail=?,isDel=false where username=?";
				Object[] paraObjects= {username,name,password,userType,sex,age,brief,phone,mail,username};
				int excute = Dao.execute(sqlString,paraObjects);
				request.setAttribute("httpUrl", "/admin/userlist");
				request.setAttribute("info", "添加用户信息成功");
				request.setAttribute("title", "添加用户");
//				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				
			} else {
				request.setAttribute("httpUrl", "/admin/userlist");
				request.setAttribute("info", "添加用户信息失败！");
				request.setAttribute("title", "添加用户");
//				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
	}
	String uploadimg(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		
		//��ȡ�ļ�
		Part part = request.getPart("imgurl");
		
		//��ȡ�ļ�����
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
