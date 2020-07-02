package cn.zsyy.admin.doctor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/admin/doctor/finishreservation")
public class FinishReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishReservation() {
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
//		2.��ȡ����
		String userid = request.getParameter("id");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		Object[] objects = {userid};
		System.out.println(userid);
		String sqlString = "update reservation set r_status ='完成' where r_id = ?";
		int excute = Dao.execute(sqlString,objects);
		if (excute > 0) {
			request.setAttribute("httpUrl", "/admin/doctor/reservelist?username="+username);
			request.setAttribute("info", "已完成该订单的预约");
			request.setAttribute("title", "预约订单");
		}else {
			request.setAttribute("httpUrl", "/admin/doctor/reservelist?username="+username);
			request.setAttribute("info", "该完成预约订单失败");
			request.setAttribute("title", "预约订单");
		}
		request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");

			String[] idList = request.getParameterValues("ids[]");
			System.out.println(idList);
			
			String sqlString = "update reservation set r_status = '完成'  where r_id in(";
			
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
				System.out.println("出现异常！");
			}
			
//			3.ִ��sql ���
			int execute = Dao.execute(sqlString);
			if (execute == 0) {
				System.out.println("ɾ��ʧ��");
			}
			System.out.println(sqlString);
		
	}

}
