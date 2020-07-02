package cn.zsyy.admin.section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.ldap.LdapClient;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class SectionList
 */
@WebServlet("/admin/section/sectionlist")
public class SectionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionList() {
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
//		2.�õ�ҳ����
		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		String sqlString = "";
		String sqlSize = "";
		String likeuser = request.getParameter("likeuser");
		String sectionType = request.getParameter("sectionType");
		if (sectionType==null) {
			sectionType = "all";
		}
		System.out.println("likeuser:"+likeuser);
		System.out.println("sectionType:"+sectionType);
		
		int num = (Integer.parseInt(page) - 1) * 5;
		if (!sectionType.equals("all")&&likeuser==null) {
			sqlString = "SELECT * from section  where s_isDel= 'false' and  s_type='"+sectionType+"' order by s_id DESC limit "+num+",5";
			sqlSize = "select * from section where s_isDel= 'false' and s_type='"+sectionType+"'";
		} else if(!sectionType.equals("all")&&likeuser!=null){
			sqlString = "SELECT * from section  where s_isDel= 'false' and s_type='"+sectionType+"' and s_name like '%"+likeuser+"%' order by s_id DESC limit "+num+",5";
			sqlSize = "select * from section where s_isDel= 'false' and s_type='"+sectionType+"' and s_name like '%"+likeuser+"%'";
//			 sqlString = "SELECT * from section order by s_id desc LIMIT "+num+" ,5;";
//			 sqlSize = "select * from section ";
		} else if(sectionType.equals("all")&&likeuser!=null) {
			sqlString = "SELECT * from section  where s_isDel= 'false' and s_name like '%"+likeuser+"%' order by s_id DESC limit "+num+",5";
			sqlSize = "select * from section where s_isDel= 'false' and s_name like '%"+likeuser+"%'";
		} else {
			 sqlString = "SELECT * from section where s_isDel= 'false'  order by s_id desc LIMIT "+num+" ,5;";
			 sqlSize = "select * from section where s_isDel= 'false' ";
		}
	   
		
		ArrayList<HashMap<String, Object>> sectionlist = new ArrayList<HashMap<String,Object>>();
		sectionlist = Dao.query(sqlString);
//		
		
		ArrayList<HashMap<String, Object>> listNum = new ArrayList<HashMap<String,Object>>();
//		��ѯ���������� 
		listNum = Dao.query(sqlSize);
		int totalSize = listNum.size();
		int allpage = (int) Math.ceil(totalSize/5.0);
		System.out.println(allpage);
		System.out.println("page？"+page);
//		System.out.println(listNum.size());
//		System.out.println(allpage);
		request.setAttribute("total", totalSize);
		request.setAttribute("allpage", allpage);
		
		
		request.setAttribute("sectionlist", sectionlist);
		System.out.println(sectionlist);
		request.setAttribute("page", page);
//		2.����ת��
		request.getRequestDispatcher("/admin/section/sectionlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
