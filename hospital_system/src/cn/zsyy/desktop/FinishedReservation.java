package cn.zsyy.desktop;

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
 * Servlet implementation class FinishedReservation
 */
@WebServlet("/desktop/finishedreservation")
public class FinishedReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishedReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String num = request.getParameter("num");
		String confirmnum = request.getParameter("confirmnum");
		String time = request.getParameter("time");
		String patientname = request.getParameter("patientname");
//		
		System.out.println("id:"+id);
		System.out.println("username:"+username);
		System.out.println("num:"+num);
		System.out.println("time:"+time);
		System.out.println("patientname:"+patientname);
		boolean isEmptyOfUser=true;
		boolean isEmptyOfPatient=true;
		String sqlSize="select * from patient where p_username='"+patientname+"'";
		ArrayList<HashMap<String, Object>> patientList = new ArrayList<HashMap<String,Object>>();
		patientList = Dao.query(sqlSize);
		HashMap<String, Object> patientHashMap = new HashMap<String, Object>();
		patientHashMap = patientList.get(0);
		int pid = (int) patientHashMap.get("p_id");
//		
		String reservationSql = "select * from reservation where pid="+pid+" and r_isDel='false' and r_status='待诊'  and r_time='"+time+"'";
		ArrayList<HashMap<String, Object>> sizeArray = new ArrayList<HashMap<String,Object>>();
		sizeArray = Dao.query(reservationSql);
		int size = sizeArray.size();
		System.out.println("size:"+size);
		int reservenum = Integer.parseInt(num);
		int confirmednum = Integer.parseInt(confirmnum);
		if (size==0) {
			if (reservenum > confirmednum) {
			System.out.println("reservenum:"+reservenum);
			System.out.println("confirmednum:"+confirmednum);
			int userid = 0;
			int patientid = 0;

			String sqlString = "update scheduling set confirmnum=? where sid="+id;
			confirmednum = confirmednum + 1;
			Object[] objects = {confirmednum};
			int excute = Dao.execute(sqlString,objects);
			System.out.println("execute:"+excute);
			if (!username.equals("")&&username!=null) {
				
				String sql = "select * from user where name='"+username+"'";
				ArrayList<HashMap<String, Object>> result= Dao.query(sql);
				HashMap<String, Object> user = result.get(0);
				userid= (int) user.get("id");
				System.out.println("userid:"+userid);
				isEmptyOfUser = false;

				
			}
			if (patientname!=null&&!patientname.equals("")) {
				
				String sqlpatient = "select * from patient where p_username='"+patientname+"'";
				ArrayList<HashMap<String, Object>> result= Dao.query(sqlpatient);
				HashMap<String, Object> patient = result.get(0);
				patientid= (int) patient.get("p_id");
				System.out.println("patientid:"+patientid);
				isEmptyOfPatient = false;
			}
			System.out.println(isEmptyOfPatient);
			System.out.println(isEmptyOfUser);
			System.out.println("excute:"+excute);
//			�жϲ�ѯ�Ƿ���
			if(!isEmptyOfPatient&&!isEmptyOfUser) {
				
//				��ѯͬһ���Ѿ�ԤԼ��ҽ����������
				String registerSqlString="select count(*) as cnt from reservation where uid="+userid+" and r_isDel='false' and r_status='待诊' and r_time='"+time+"'";
				ArrayList<HashMap<String, Object>> registerNum = Dao.query(registerSqlString);
				Long registerNumSize =(Long) registerNum.get(0).get("cnt");
//				int registerNumSize = registerNum.size();
				System.out.println("regisgerNumSize:"+registerNumSize);
				registerNumSize++;
//				registerNumSize++;
				String regNum = time+"-"+userid+"-"+registerNumSize;
				System.out.println(regNum);
//				������Ϣ
				HashMap<String, Object> reservation = new HashMap<String, Object>();
				reservation.put("pid", patientid);
				reservation.put("r_time", time);
				reservation.put("r_status", "待诊");
				reservation.put("r_isDel", "false");
				reservation.put("uid", userid);
				reservation.put("registernum", regNum);
				int result = Dao.insertObj("reservation", reservation);
//				System.out.println(result);
				if (result > 0) {
					request.setAttribute("httpUrl", "/desktop/schedulinglist");
					request.setAttribute("info", "预约医生成功，正在跳转！");
					request.setAttribute("title", "预约挂号");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}else {
					request.setAttribute("httpUrl", "/desktop/schedulinglist");
					request.setAttribute("info", "预约医生失败，正在跳转！");
					request.setAttribute("title", "预约挂号");
					request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
				}
					
			}
			else {
				request.setAttribute("httpUrl", "/desktop/schedulinglist");
				request.setAttribute("info", "预约医生失败，正在跳转！");
				request.setAttribute("title", "预约挂号");
				request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
			}
//			
		}else {
			request.setAttribute("httpUrl", "/desktop/schedulinglist");
			request.setAttribute("info", "预约医生失败，该医生的预约人数已满！正在跳转！");
			request.setAttribute("title", "预约挂号");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
//				
//		}else {
//			request.setAttribute("httpUrl", "/desktop/schedulinglist");
//			request.setAttribute("info", "ԤԼ����ʧ��,�û�һ��ֻ��ԤԼһ��ҽ��һ��");
//			request.setAttribute("title", "ԤԼʧ�ܣ�������תԤԼ��Ϣ�б�ҳ��");
//			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}
		else {
			request.setAttribute("httpUrl", "/desktop/schedulinglist");
			request.setAttribute("info", "预约医生失败，一个医生不能在同一天多次预约同一个医生正在跳转！");
			request.setAttribute("title", "预约挂号");
			request.getRequestDispatcher("/admin/info.jsp").forward(request, response);
		}



		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
