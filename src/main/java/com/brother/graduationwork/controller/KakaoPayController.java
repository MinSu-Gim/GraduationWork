package com.brother.graduationwork.controller;


import com.brother.graduationwork.domain.KakaoPay.KakaoPay;
import com.brother.graduationwork.dto.KakaoPayDTO;
import com.brother.graduationwork.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.Setter;

@Slf4j
@Controller
public class KakaoPayController {

    private String totalMoney;
    private String user_email;

    @Autowired
    UserService userService;

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
        log.info("kakaopay get");
    }

    @PostMapping("/kakaoPay")
    @ResponseBody
    public String kakaoPay(@RequestBody KakaoPayDTO testDTO) {

//        System.out.println("id = " + id);
//        System.out.println("money = " + money);
        totalMoney = testDTO.getMoney();
        user_email = testDTO.getUser_email();
        System.out.println("totalMoney: " + totalMoney);
        System.out.println(user_email);
        log.info("kakaopay post");

        String nextURL = kakaopay.kakaoPayReady(totalMoney);
        log.info("nextURL is " + nextURL);
        return nextURL;
    }

    @PostMapping("/kakaoPaySuccess")
    @ResponseBody
    public int kakaoPaySuccess(@RequestBody String pg_token, Model model) {
        pg_token = pg_token.replace("=", "");
        log.info("KakaoPayController.kakaoPaySuccess");
        log.info("kakaoPaySuccess get");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        // model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, totalMoney));

        int money = kakaopay.kakaoPayInfo(pg_token, totalMoney).getAmount().getTotal();

        userService.increaseMoney(Integer.parseInt(totalMoney), user_email);

        return money;
        // return "KakaoPaySuccess";
    }
}
