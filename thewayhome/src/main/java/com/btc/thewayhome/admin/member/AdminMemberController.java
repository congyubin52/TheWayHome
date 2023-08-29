package com.btc.thewayhome.admin.member;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;

    // 보호소 api db에 삽입
    @RequestMapping("/")
    public Object shelterRegistNum() {
        log.info("shelterRegist()");
//        StringBuilder result = new StringBuilder();
        List<String> result = new ArrayList<>();
        ShelterNumDto shelterNumDto = new ShelterNumDto();

        try {
            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido?" +
                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                    "&_type=json" +
                    "&pageNo=1" +
                    "&numOfRows=5";
            System.out.println(">>url: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            StringBuilder response = new StringBuilder();

            while ((returnLine = br.readLine()) != null) {
                result.add(returnLine);
            }
            urlConnection.disconnect();


            // Jackson ObjectMapper를 사용하여 JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.toString());

            JsonNode itemArrayNode = rootNode
                    .get("response")
                    .get("body")
                    .get("items")
                    .get("item");

            for (JsonNode itemNode : itemArrayNode) {
                String orgCd = itemNode.get("orgCd").asText();
                result.add(orgCd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // result 리스트에 "orgCd" 값들이 저장되어 있음
        for (String orgCd : result) {
            System.out.println("orgCd: " + orgCd);
        }
//
//        log.info(result.size());

        try {
            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sigungu?" +
                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                    "&upr_cd=" +
                    "&_type=json" +
                    "&pageNo=1" +
                    "&numOfRows=5";
            System.out.println(">>url: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.add(returnLine);
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/shelter?" +
                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                    "&_type=json" +
                    "&pageNo=1" +
                    "&numOfRows=5";
            System.out.println(">>url: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.add(returnLine);
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMemberService.shelterRegistNum(result.toString(), shelterNumDto);
        return "result";
    }

    @RequestMapping("/admin_member_confirm")
    public Object shelterRegistInfo(){
        log.info("shelterRegistInfo()");
        StringBuilder result = new StringBuilder();
        ShelterInfoDto shelterInfoDto = new ShelterInfoDto();
        try {
            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" +
                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                    "&_type=json" +
                    "&pageNo=1" +
                    "&numOfRows=5";
//            System.out.println(">>url: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br;

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMemberService.shelterRegistInfo(result.toString(), shelterInfoDto);
        return "result";
    }


//    @GetMapping("/create_account_form")
//    public String createAccountForm(){
//        log.info("createAccountForm()");
//
//        String nextPage = "/member/member/create_account_form";
//
//        return nextPage;
//
//    }
}
