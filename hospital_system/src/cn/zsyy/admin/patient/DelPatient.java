package cn.zsyy.admin.patient;

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
@WebServlet("/admin/patient/delpatient")
public class DelPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelPatient() {
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
		String sqString ="update patient set p_isDel='true' where p_id="+id;
		int result = Dao.execute(sqString);
		if (result > 0 ) {
			request.setAttribute("httpUrl", "/admin/patient/patientlist");
			request.setAttribute("info", "删除患者信息成功，正在跳转页面！");
			request.setAttribute("title", "删除患者信息");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}else {
			request.setAttribute("httpUrl", "/admin/patient/patientlist");
			request.setAttribute("info", "删除患者信息失败，正在条转页面！");
			request.setAttribute("title", "删除患者信息");
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
			
			String sqlString = "update patient set p_isDel='true' where p_id in(";
			
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
				System.out.println("ԤԼ���ʧ��");
			}
			System.out.println(sqlString);
		
	}

}
