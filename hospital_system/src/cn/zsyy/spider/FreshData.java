package cn.zsyy.spider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FreshData
 */
@WebServlet("/freshdata")
public class FreshData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreshData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		//�����������е�����
		response.addHeader("Access-Control-Allow-Content-Type", "*");
		CollectHistoryData collectHistoryData = new CollectHistoryData();
//		1.����ˢ�·���
//		2.���¸���ʡ������
		CollectData.collectData();	
//		3.������ʷ����
		collectHistoryData.HistoryData();
//		4.����������ʷ����
//		CollectDataOfWorld.CollectData();
		
		response.getWriter().println("�������ݳɹ���");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
