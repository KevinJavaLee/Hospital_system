package cn.zsyy.Thread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class InsertThread extends  Thread {
	public void run(){
		String url = "jdbc:mysql://localhost:3306/collegeinfo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true"; //���ݿ����ӵ�ַ
        String name = "com.mysql.cj.jdbc.Driver";
        String user = "root"; 
        String password = "a18713837118";//����
        Connection conn = null;
			try {	
			    Class.forName(name);
				conn=DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);//�ر��Զ��ύ
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long begin=new Date().getTime();
			String sql= "INSERT INTO teachers (t_name,t_password,sex,description,pic_url,school_name,regist_date,remark) VALUES(?,?,?,?,?,?,?,?)";
			try {
				StringBuilder sqls= new StringBuilder(); 
				conn.setAutoCommit(false);
				PreparedStatement pst=conn.prepareStatement(sql);
                //��д����
				for(int i=1;i<=10;i++){
 
					for(int j=1;j<=1000000;j++){
						pst.setString(1,"teacher122");
						pst.setString(2, "12223444");
						pst.setString(3, "��");
						pst.setString(4, "��ʦ");
						pst.setString(5, "www.bbb.com");
						pst.setString(6, "java��ѧ");
						pst.setString(7, "2019-08-16 14:43:26");
						pst.setString(8, "��ð�");
						pst.addBatch();//���������Ϣ
					}
                    // ִ����������
                    pst.executeBatch();
                    // �ύ����
                    conn.commit();
				}
				pst.close();
                conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // ����ʱ��
            Long end = new Date().getTime();
            // ��ʱ
            System.out.println("100�������ݲ��뻨��ʱ�� : " + (end - begin) / 1000 + " s"+"  �������"); 
	}
}

