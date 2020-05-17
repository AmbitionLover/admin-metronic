package work.ambitlu.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ambitlu.common.config.Global;
import work.ambitlu.common.core.controller.BaseController;
import work.ambitlu.common.core.domain.AjaxResult;
import work.ambitlu.framework.util.ShiroUtils;
import work.ambitlu.system.domain.SysMenu;
import work.ambitlu.system.domain.SysUser;
import work.ambitlu.system.service.ISysConfigService;
import work.ambitlu.system.service.ISysMenuService;

import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi
 * @Description:
 * @ate: 2020/5/6 21:49
 * @since JDK 1.11
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
//        // 取身份信息
//        SysUser user = ShiroUtils.getSysUser();
//        // 根据用户id取出菜单
//        List<SysMenu> menus = menuService.selectMenusByUser(user);
//        mmap.put("menus", menus);
//        mmap.put("user", user);
//        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
//        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
//        mmap.put("copyrightYear", Global.getCopyrightYear());
//        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 布局文件
    @GetMapping("/menu")
    @ResponseBody
    public AjaxResult menu(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        return AjaxResult.success("操作成功", menus);
    }

    // 布局文件
    @GetMapping("/layout")
    public String layout(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        mmap.put("user", user);
        return "layout";
    }

    // 主页
    @GetMapping("/home")
    public String home(ModelMap mmap)
    {
        return "home";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统主页
    @GetMapping("/system/about")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "system/system/about";
    }
    // 系统主题
    @GetMapping("/system/theme")
    public String theme(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "system/system/theme";
    }
}
