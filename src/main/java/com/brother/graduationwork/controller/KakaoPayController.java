package com.brother.graduationwork.controller;


import com.brother.graduationwork.domain.KakaoPay.KakaoPay;
import com.brother.graduationwork.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Slf4j
@Controller
public class KakaoPayController {

    private String totalMoney;
    private Long userId;

    @Autowired
    UserService userService;

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
        log.info("kakaopay get");
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestParam("money") String money, @RequestParam("id") Long id) {
        System.out.println("id = " + id);
        System.out.println("money = " + money);
        totalMoney = money;
        userId = id;
        log.info("kakaopay post");

        return "redirect:" + kakaopay.kakaoPayReady(totalMoney);
    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        System.out.println("KakaoPayController.kakaoPaySuccess");
        log.info("kakaoPaySuccess get");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, totalMoney));

        userService.increaseMoney(Integer.parseInt(totalMoney), userId);
        return "KakaoPaySuccess";
    }
}
