package com.brother.graduationwork.domain.KakaoPay;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(String money) {

        log.info("KakaoPay.kakaoPayReady");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header Part
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "6ba0e19e8c41f8a0a5d889a3f709e58c");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body Part
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME"); // cid must be 'TC0ONETIME' in test case
        params.add("partner_order_id", "1000");
        params.add("partner_user_id", "UserA");
        params.add("item_name", "여기모여 머니 충전");
        params.add("quantity", "1");
        params.add("total_amount", money);
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            log.info("" + kakaoPayReadyVO);
            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException | URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";

    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, String money) {
        log.info("KakaoPay.kakaoPayInfo");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "6ba0e19e8c41f8a0a5d889a3f709e58c");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        // APPLICATION_JSON_UTF8_VALUE is deprecated
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1000");
        params.add("partner_user_id", "UserA");
        params.add("pg_token", pg_token);
        params.add("total_amount", money);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
