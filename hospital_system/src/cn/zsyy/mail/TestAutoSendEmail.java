package cn.zsyy.mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



import cn.zsyy.db.Dao;

public class TestAutoSendEmail {

	
	public static void autoSendEmail() {

		
		Date currentDate = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			currentDate = sf.parse(sf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(currentDate);
		Date reservationTime = null;
		int differDay = 0;
		String sql="select r_time,registernum,p_name,p_tel,u.`name`,sec.s_name from reservation res,patient pat,`user` u,section sec where res.pid=pat.p_id and res.uid=u.id and u.sectionid=sec.s_id and   res.r_isDel='false' and res.r_status='待诊' ";
		ArrayList<HashMap<String, Object>> result = Dao.query(sql);
		for (HashMap<String, Object> Maps : result) {
			reservationTime = (Date) Maps.get("r_time");
			
			
			differDay = differentDaysByMillisecond(currentDate, reservationTime);
			if (differDay==1) {
//				�����ʼ����û�
				SendEmail sendEmail = new SendEmail();
				String toEmail = (String) Maps.get("p_tel");
				String patientName = (String) Maps.get("p_name");
				String registerNum = (String) Maps.get("registernum");
				String sectionName = (String) Maps.get("s_name");
				String doctorName = (String) Maps.get("name");
				System.out.println(toEmail);
				System.out.println(patientName);
				System.out.println(doctorName);
				System.out.println(sf.format(new Date()));
				System.out.println(sectionName);
				System.out.println(registerNum);
				try {
					sendEmail.sendMail(patientName,doctorName , toEmail,sf.format(new Date()),sectionName,registerNum);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		}
		
	
	}
	public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

}
