package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        while(true) {
            List<String> result = new ArrayList<>();
            String responseString = "";
            String orgCd = "";
            String uprCd = "";
            List<String> orgCdList = new ArrayList<>();
            ShelterNumDto shelterNumDto = new ShelterNumDto();
//        StringBuilder response = new StringBuilder();

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
                    response.append(returnLine);
                }
                urlConnection.disconnect();

                responseString = response.toString();


                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);
                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");
                JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject
//                JSONArray array = (JSONArray) items.get("item");
//
//
//                for (int i = 0; i < array.size(); i++) {
//                    JSONObject jObj = (JSONObject) array.get(i);
//                    orgCd = jObj.get("orgCd").toString();
//                    System.out.println("orgCd : " + orgCd);
//                    orgCdList.add(jObj.get("orgCd").toString());
//                }

                Object item = items.get("item");

                if (item instanceof JSONArray) {
                    JSONArray array = (JSONArray) item; // item is a JSONArray

                    for (int i = 0; i < array.size(); i++) {
                        JSONObject jObj = (JSONObject) array.get(i);
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("orgCd : " + orgCd);

                    }
                } else if (item instanceof JSONObject) {
                    JSONObject jObj = (JSONObject) item; // item is a single JSONObject
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("orgCd : " + orgCd);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("---------> " + orgCdList);
            // result 리스트에 "orgCd" 값들이 저장되어 있음
//
//        log.info(result.size());

            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sigungu?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&upr_cd=" + orgCd +
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
                    response.append(returnLine);
                }
                urlConnection.disconnect();

                responseString = response.toString();


                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);

                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");

                JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

//                JSONArray array = (JSONArray) items.get("item");
//
//                for (int i = 0; i < array.size(); i++) {
//                    JSONObject jObj = (JSONObject) array.get(i);
//                    uprCd = jObj.get("uprCd").toString();
//                    orgCd = jObj.get("orgCd").toString();
//                    System.out.println("uprCd : " + uprCd);
//                    System.out.println("orgCd : " + orgCd);
//                }

                Object item = items.get("item");

                if (item instanceof JSONArray) {
                    JSONArray array = (JSONArray) item; // item is a JSONArray

                    for (int i = 0; i < array.size(); i++) {
                        JSONObject jObj = (JSONObject) array.get(i);
                    uprCd = jObj.get("uprCd").toString();
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("uprCd : " + uprCd);
                    System.out.println("orgCd : " + orgCd);

                    }
                } else if (item instanceof JSONObject) {
                    JSONObject jObj = (JSONObject) item; // item is a single JSONObject
                    uprCd = jObj.get("uprCd").toString();
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("uprCd : " + uprCd);
                    System.out.println("orgCd : " + orgCd);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/shelter?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&upr_cd=" + uprCd +
                        "&org_cd=" + orgCd +
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
