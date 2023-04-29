package com.example.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ruiji.common.R;
import com.example.ruiji.entity.User;
import com.example.ruiji.service.UserService;
import com.example.ruiji.utils.SMSUtils;
import com.example.ruiji.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     * 用户登录名称 test0@1240868827890217.onaliyun.com
     * AccessKey ID LTAI5t8pgWMzCmdgzD6wR6Pm
     * AccessKey Secret UCMbjNLXT0WJXzGCvLw9djlSC97tSN
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机好
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //生成验证吗
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //发送验证码
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);
            //保存
            session.setAttribute(phone,code);
            return R.success("短信发送成功");
        }
        return R.error("短信发送失败");
    }

    /**
     * 用户登录/注册
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        log.info(map.toString());
//        获取手机号和验证码
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        //比对
        Object codeInSession = session.getAttribute(phone);
        if (codeInSession!=null && codeInSession.equals(code)){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getPhone,phone);

            User one = userService.getOne(userLambdaQueryWrapper);
            if (one == null){
                User user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",one.getId());
            return R.success(one);

        }
        return R.error("登录失败");
    }
}
