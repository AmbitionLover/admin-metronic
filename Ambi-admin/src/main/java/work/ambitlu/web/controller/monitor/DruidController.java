package work.ambitlu.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import work.ambitlu.common.core.controller.BaseController;

/**
 * Druid监控
 *
 * @author Ambi
 * @Description:
 * @ate: 2020/5/25 18:56
 * @since JDK 1.11
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/druid";

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index()
    {
        return redirect(prefix + "/index");
    }
}
