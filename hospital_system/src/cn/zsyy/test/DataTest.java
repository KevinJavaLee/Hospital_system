package cn.zsyy.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class DataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Date date=new Date();//ȡʱ��
			Calendar calendar = new GregorianCalendar();
		    calendar.setTime(date);
		    calendar.add(calendar.DATE,5);//��������������һ��.����������,������ǰ�ƶ�
		    date=calendar.getTime(); //���ʱ���������������һ��Ľ�� 
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String futureTime = formatter.format(date);
		    String currentTime = formatter.format(new Date());
		    System.out.println(futureTime);
		    System.out.println(currentTime);
	}

}
