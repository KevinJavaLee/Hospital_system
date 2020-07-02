package cn.zsyy.desktop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.zsyy.db.Dao;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/desktop/*")
public class DesktopFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DesktopFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		1.���ñ��뼯
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("-------patient  filter-------");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		
		if(servletPath.equals("/desktop/register")||servletPath.equals("/desktop/login")||servletPath.equals("/desktop/loginout")) {
			chain.doFilter(request, response);
		}else {
			
			HttpSession session = req.getSession();
			if(session.getAttribute("username")!=null) {
				
				//chain.doFilter(request, response);
				String username =(String) session.getAttribute("username");
				System.out.println(username);
				String sqlStr = "select * from patient where p_isDel='false' and p_username = ?";
				String[] params = {username};

				ArrayList<HashMap<String, Object>> res = Dao.query(sqlStr, params);
				HashMap<String, Object> patient = res.get(0);
				System.out.println(patient);
				System.out.println(patient.get("userType"));
				if(patient.get("userType").equals("patient")) {
					chain.doFilter(request, response);
				}
			}else {
				
				req.setAttribute("httpUrl", "/desktop/login");
				req.setAttribute("info", "你还未登录，请先登录账户");
				req.setAttribute("title", "还未登陆");
				req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
