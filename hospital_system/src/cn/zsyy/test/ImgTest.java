package cn.zsyy.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.zsyy.db.Dao;

/**
 * Servlet implementation class ImgTest
 */
@WebServlet("/imgtest")
public class ImgTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.…Ë÷√±‡¬ÎºØ
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String sqlString = "select * from user where name='Î¯Ë˜æ£'";
		ArrayList<HashMap<String, Object>> imgsArrayList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> imgHashMap = new HashMap<String, Object>();
		imgsArrayList = Dao.query(sqlString);
		imgHashMap = imgsArrayList.get(0);
		String srcString = (String) imgHashMap.get("imgurl");
		
		response.getWriter().println("<img src='"+srcString+"' width='150' height='150'  />");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
