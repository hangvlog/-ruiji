package com.example.ruiji.filter;

import com.alibaba.fastjson.JSON;
import com.example.ruiji.common.BaseContext;
import com.example.ruiji.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//检查用户是否登录
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class loginCheckFilter implements Filter {
    //路径批评器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //get url
        String requestURI = request.getRequestURI();
        //
//        log.info("拦截到请求:{}", requestURI);

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"

//                "/employee/page"
        };
        boolean check = check(urls, requestURI);

        //如果匹配成功：是我们的urls中的直接放行的URI
        if (check) {

            log.info("本次请求{}不需要处理",requestURI);

            filterChain.doFilter(request, response);
            return;
        }
        //需要权限的页面
        if (request.getSession().getAttribute("employee") != null) {

            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));

            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        if (request.getSession().getAttribute("user") != null) {

            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));

            Long userid = (Long)request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userid);

            filterChain.doFilter(request, response);
            return;
        }
        //没有登录：通过输出流的形式向前端响应数据
        log.info("没有登录：通过输出流的形式向前端响应数据");

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


//        log.info("拦截到请求",request.getRequestURI());
        //放行
//        filterChain.doFilter(request,response);
    }

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

}
