package com.qst.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.qst.sys.constast.SysConstast;
import com.qst.sys.domain.User;
import com.qst.sys.service.LogInfoService;
import com.qst.sys.service.UserService;
import com.qst.sys.utils.WebUtils;
import com.qst.sys.vo.LogInfoVo;
import com.qst.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogInfoService logInfoService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "sys/main/login";
    }

    /**
     * 添加日志
     */
//    @RequestMapping("addLogInfo")
//    public ResultObj addLogInfo(LogInfoVo logInfoVo) {
//        try {
//            this.logInfoService.addLogInfo(logInfoVo);
//            return ResultObj.ADD_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultObj.ADD_ERROR;
//        }
//    }

    @RequestMapping("login.action")
    public String login(UserVo userVo,Model model) {

        String code = (String) WebUtils.getHttpSession().getAttribute("code");

        if (userVo.getCode().equals(code)) {

            User user = this.userService.login(userVo);
            if (null != user) {
                //放到session
                WebUtils.getHttpSession().setAttribute("user", user);
                LogInfoVo logInfoVo = new LogInfoVo();
                logInfoVo.setLogintime(new Date());
                logInfoVo.setLoginname(user.getRealname() + "-" + user.getLoginname());
                //记录ip地址
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                logInfoService.addLogInfo(logInfoVo);
                //记录登陆日志 向sys_login_log里面插入数据
                return "sys/main/index";
            } else {
                model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
                return "sys/main/login";
            }
        } else {
            model.addAttribute("error", SysConstast.USER_LOGIN_CODE_ERROR_MSG);
            return "sys/main/login";
        }

    }

    //得到登录验证码
    @RequestMapping("getCode")
    public  void  getCode(HttpServletResponse response, HttpSession session){

        LineCaptcha lineCaptcha= CaptchaUtil.createLineCaptcha(116 ,36,4,5);

        session.setAttribute("code",lineCaptcha.getCode());

        ServletOutputStream outputStream= null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
