package com.llp.controller;

import com.llp.pojo.User1;
import com.llp.sdk.responseEntity.User;
import com.llp.service.UserService;
import com.llp.sdk.util.NetWorkBusiness;
import com.llp.sdk.requestEntity.SignInEntity;
import com.llp.sdk.responseEntity.base.BaseResponseEntity;
import com.llp.sdk.util.MyObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * description:用户登录控制
 * create by Administrator
 * create on 2021/06/20/19:39
 */
@Controller
public class LoginController {

    private static NetWorkBusiness netWorkBusiness;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginHtml(){
        return "login";
    }

    /**
     * 用户登录判断
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/checkLogin")
    public String login(String username, String password, Model model, HttpSession session){
        //查询是否有此用户
        User1 user = userService.findUser(username,password);
        //System.out.println("user==="+user);
        if(user!=null){
            session.setAttribute("uid",user.getUid());
            return "redirect:/index";
        }else{
            model.addAttribute("msg","账号密码错误,请重试");
            return "login";
        }

    }



}
