package work.ambitlu.web.controller.system;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ambitlu.common.core.controller.BaseController;
import work.ambitlu.common.core.domain.AjaxResult;
import work.ambitlu.common.utils.ServletUtils;
import work.ambitlu.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 一些声明信息
 *
 * @author Ambi
 * @Description: 登陆 控制器
 * @ate: 2020/5/6 13:17
 * @since JDK 1.11
 */
@Controller
public class  SysLoginController extends BaseController
{
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1001\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "system/user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, String rememberMe)
    {
        Boolean remeber = true;
        if (StringUtils.isEmpty(rememberMe)){
            remeber = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remeber);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}

