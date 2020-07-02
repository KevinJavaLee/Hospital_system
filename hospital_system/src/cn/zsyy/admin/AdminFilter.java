package cn.zsyy.admin;

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
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("-------admin  filter-------");

		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		
		if(servletPath.equals("/admin/register")||servletPath.equals("/admin/login")) {
			chain.doFilter(request, response);
		}else {
			
			HttpSession session = req.getSession();
			if(session.getAttribute("username")!=null) {
				
				//chain.doFilter(request, response);
				String username =(String) session.getAttribute("username");
				System.out.println(username);
				String sqlStr = "select * from user where username =?";
				String[] params = {username};

				ArrayList<HashMap<String, Object>> res = Dao.query(sqlStr, params);
				HashMap<String, Object> user = res.get(0);
				System.out.println(user);
				System.out.println(user.get("userType"));
				if(user.get("userType").equals("admin")||user.get("userType").equals("doctor")||user.get("userType").equals("patient")) {
					chain.doFilter(request, response);
					//System.out.println("servlet path:"+req.getServletPath());
				}else {
					req.setAttribute("httpUrl", "/admin/login");
					req.setAttribute("info", "你的权限的不够，页面正在跳转页面");
					req.setAttribute("title", "权限拦截");
					req.getRequestDispatcher("/admin/info.jsp").forward(req, response);
				}
				
			}else {
				//���û�е�¼����ô��ת����¼ҳ��
				req.setAttribute("httpUrl", "/admin/login");
				req.setAttribute("info", "你还未登陆，请先登录，页面正在跳转！");
				req.setAttribute("title", "权限拦截");
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
