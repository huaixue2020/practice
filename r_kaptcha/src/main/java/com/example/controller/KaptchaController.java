package com.example.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public Object validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        boolean validate;
        try {
//            validate = kaptcha.validate(code);    // 没有过期时间
            validate = kaptcha.validate(code, 300);  // 30s过期
        } catch (Exception e) {
            if (e instanceof KaptchaIncorrectException) {
                return "验证码不正确";
            } else if (e instanceof KaptchaNotFoundException) {
                return "验证码未找到";
            } else if (e instanceof KaptchaTimeoutException) {
                return "验证码过期";
            } else if (e instanceof NullPointerException) {
                return "验证码用过后销毁";
            } else {
                return "验证码渲染失败";
            }
        }
        return validate;
    }

}