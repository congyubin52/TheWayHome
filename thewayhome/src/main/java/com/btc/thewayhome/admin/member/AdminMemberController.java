package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Log4j2
@Controller
@RequestMapping("/member/admin")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;



    // 보호소 api db에 삽입
    @RequestMapping("/")
    public Object shelterRegistNum() {
        log.info("shelterRegist()");
        StringBuilder result = new StringBuilder();
        ShelterNumDto shelterNumDto = new ShelterNumDto();
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
                result.append(returnLine);
            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMemberService.shelterRegistNum(result.toString(), shelterNumDto);
        return "result";
    }

    @RequestMapping("/")
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
            System.out.println(">>url: " + apiUrl);
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


    @GetMapping("/create_account_form")
    public String createAccountForm(){
        log.info("createAccountForm()");

        String nextPage = "/admin/member/create_account_form";

        return nextPage;

    }
}
