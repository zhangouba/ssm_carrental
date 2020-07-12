package com.qst.stat.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

public class Testcode {
    public static void main(String[] args) {
        LineCaptcha lineCaptcha= CaptchaUtil.createLineCaptcha(200,100);

        lineCaptcha.write("D:/1.jpg");
        System.out.println(lineCaptcha.getCode());
    }
}
