package work.ambitlu.framework.config;

/**
 * 解决跨域问题springboot所需配置
 *
 * @author Ambi
 * @Description:
 * @ate: 2020/5/23 14:38
 * @since JDK 1.11
 */

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题springboot所需配置
 */
@Order(-100)
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "shiroLoginFilter")
public class CORSConfiguration implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 允许哪些Origin发起跨域请求
        String orgin = request.getHeader("Origin");
        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
        response.setHeader( "Access-Control-Allow-Origin", orgin );
        // 允许请求的方法
        response.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
        //多少秒内,不需要再发送预检验请求，可以缓存该结果
        response.setHeader( "Access-Control-Max-Age", "3600" );
        // 表明它允许跨域请求包含xxx头
        response.setHeader( "Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept" );
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        //prefight请求
        if (request.getMethod().equals( "OPTIONS" )) {
            response.setStatus( 200 );
            return;
        }
        chain.doFilter( servletRequest, response );
    }

    @Override
    public void destroy() {

    }
}
