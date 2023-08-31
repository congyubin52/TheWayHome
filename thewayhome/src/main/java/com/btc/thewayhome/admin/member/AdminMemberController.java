package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        while (true) {
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
                JSONArray array = (JSONArray) items.get("item");


                for (int i = 0; i < array.size(); i++) {
                    JSONObject jObj = (JSONObject) array.get(i);
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("orgCd : " + orgCd);
                    orgCdList.add(jObj.get("orgCd").toString());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("---------> " + orgCdList);
            // result 리스트에 "orgCd" 값들이 저장되어 있음
//
//        log.info(result.size());

            StringBuilder sigungu = new StringBuilder();
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
                System.out.println("response : " + response);
                System.out.println("response : " + response);

                System.out.println("responseString : " + responseString);

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);
                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");

                JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

                System.out.println("items : " + items);

                JSONArray array = (JSONArray) items.get("item");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject jObj = (JSONObject) array.get(i);
                    uprCd = jObj.get("uprCd").toString();
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("uprCd : " + uprCd);
                    System.out.println("orgCd : " + sigungu);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

//쉘터
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
                StringBuilder response = new StringBuilder();

                while ((returnLine = br.readLine()) != null) {
                    result.add(returnLine);
                    response.append(returnLine);
                }
                urlConnection.disconnect();
                responseString = response.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            adminMemberService.shelterRegistNum(responseString, shelterNumDto);

            ShelterInfoDto shelterInfoDto = new ShelterInfoDto();

            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&_type=json" +
                        "&pageNo=1" +
                        "&numOfRows=1000";
//            System.out.println(">>url: " + apiUrl);
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            adminMemberService.shelterRegistInfo(responseString, shelterInfoDto);


            return null;
        }
    }

        @GetMapping("/create_account_form")
        public String createAccountForm() {
            log.info("createAccountForm()");

            String nextPage = "admin/member/create_account_form";

//            Map<String, Object> msgMap = adminMemberService.ShelterList();
//            model.addAttribute("shelterNumDtos", msgMap.get("shelterNumDtos"));
//            model.addAttribute("shleterInfoDtos", msgMap.get("shleterInfoDtos"));

            return nextPage;

        }

//        @PostMapping ("/create_account_confirm")
//        public String createAccountConfirm(AdminMemberDto adminMemberDto, Model model){
//            log.info("createAccountConfirm()");
//
////            List<AdminMemberDto> shelterNameJoinDtos = adminMemberService.ShelterNameJoin(adminMemberDto);
//
//
////            log.info("[AdminMemberController] shelterNameJoinDto" + shelterNameJoinDtos);
//
//            String nextPage = "/admin/member/create_account_success";
//
////            int result = adminMemberService.createAccountConfirm(adminMemberDto);
////            if(result <= 0){
////                nextPage = "/admin/member/create_account_fail";
////            }
//
//            return nextPage;
//        }

        // 회원가입할 때 DB에 보호소명으로 조인된 테이블 데이터를 비동기로 출력을 위한 것
//    @PostMapping("/searchShelterName")
//    @ResponseBody
//    public Object searchShelterName(@RequestBody Map<String, String> msgMap){
//        Map<String, Object> map = adminMemberService.searchShelterName(msgMap);
//        List<String>
//        return map;
//    }




//    @RequestMapping("/admin_member_confirm")
//    public Object shelterRegistInfo(){
//        log.info("shelterRegistInfo()");
//        StringBuilder result = new StringBuilder();
//
//    }


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
