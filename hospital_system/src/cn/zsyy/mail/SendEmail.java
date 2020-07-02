package cn.zsyy.mail;



import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmail {

//	��װ�ʼ�
    public void  sendMail(String patientname,String doctorname,String toEmail,String time,String section,String registernum ) throws Exception {


        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// ����QQ�ʼ�������
        prop.setProperty("mail.transport.protocol", "smtp"); // �ʼ�����Э��
        prop.setProperty("mail.smtp.auth", "true"); // ��Ҫ��֤�û�������

        // ����QQ���䣬��Ҫ����SSL���ܣ��������´��뼴��
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //ʹ��JavaMail�����ʼ���5������

        //������������Ӧ�ó�������Ļ�����Ϣ�� Session ����

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //�������ʼ��û�������Ȩ��
                return new PasswordAuthentication("1401597760@qq.com", "vcrpybhlzaabhhai");
            }
        });


        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);

        //2��ͨ��session�õ�transport����
        Transport ts = session.getTransport();

        //3��ʹ��������û�������Ȩ�������ʼ�������
        ts.connect("smtp.qq.com", "1401597760@qq.com", "vcrpybhlzaabhhai");

        

        
        MimeMessage message = new MimeMessage(session);

        
        message.setFrom(new InternetAddress("1401597760@qq.com"));

        
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        
        message.setSubject("康健服务管理平台用户预约提醒");
        
        String sendBody = "<h1>尊敬的用户："+patientname+"</h1>"
        		+ "<div>"
        		+ "<li> 你好！</li>"
        		+ "<li> 你于"+time+"在康健服务管理平台预约了"+section+"的"+doctorname+"医生，请于8：00-12：00，14：00-18：00 及时赴医院就诊。</li>"
        		+ "<li>你可以乘坐26、22、60、21、66等公交车到达唐院站下车。到达后，请记得凭借你的预约号码<h3>"+registernum+"</h3>去相关科室排队等候！</li>"
        		+ "<h3>                                     <tr>本邮件仅仅用于测试----------------------------------------Kevin xiao ----------------</tr></h3>"
        		+ "</div>";
        //�ʼ����ı�����
        message.setContent(sendBody, "text/html;charset=UTF-8");

        //5�������ʼ�
        ts.sendMessage(message, message.getAllRecipients());

        ts.close();
    
    }

}
