package cn.zsyy.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class DataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
		    calendar.setTime(date);
		    calendar.add(calendar.DATE,5);//把日期往后增加一天.整数往后推,负数往前移动
		    date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    String futureTime = formatter.format(date);
		    String currentTime = formatter.format(new Date());
		    System.out.println(futureTime);
		    System.out.println(currentTime);
	}

}
