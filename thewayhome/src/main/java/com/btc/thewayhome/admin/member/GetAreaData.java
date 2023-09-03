package com.btc.thewayhome.admin.member;

import com.btc.thewayhome.admin.pets.user.PetsUserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class GetAreaData {

    @Autowired
    AdminMemberService adminMemberService;

    @Autowired
    PetsUserService petsAdminService;

    public void getData() {

        List<String> result = new ArrayList<>();
        String responseString = "";
        String orgCd = "";
//        String uprCd = "";
        String sigunguCd = "";
        List<String> orgCdList = new ArrayList<>();
        List<String> uprCdList = new ArrayList<>();
        List<String> sigunguCdList = new ArrayList<>();
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
        // result 리스트에 "orgCd" 값들이 저장되어 있음 ---------> [6110000, 6260000, 6270000, 6280000, 6290000]
        Map<String, Object> resultMap = new HashMap<>();

        StringBuilder sigungu = new StringBuilder();
        for (int i = 0; i < orgCdList.size(); i++) {
            try {
                List<String> kindCollectList = new ArrayList<>();
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sigungu?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&upr_cd=" + orgCdList.get(i) +
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
//                    System.out.println("response : " + response);
//                    System.out.println("response : " + response);

//                    System.out.println("responseString : " + responseString);

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString); // JSON 문자열 responseString을 파싱하여 JSONObject로 변환하는 코드
                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");
                JSONObject items = (JSONObject) parseBody.get("items"); // parseBody라는 JSONObject에서 "items"라는 키를 사용하여 또 다른 JSONObject를 추출하는 코드
//                    System.out.println("items : " + items);

                JSONArray array = (JSONArray) items.get("item"); // items라는 JSONObject에서 "item"이라는 키를 사용하여 JSONArray를 추출하는 코드

                for (int j = 0; j < array.toArray().length; j++) { // JSONArray를 Java 배열로 변환한 후, 해당 배열의 길이(크기)를 반환하는 코드
                    JSONObject jObj = (JSONObject) array.get(j);
//                        uprCd = jObj.get("uprCd").toString(); // 상위 시도 코드
                    sigunguCd = jObj.get("orgCd").toString(); // 시군구 코드
//                        System.out.println("orgCd : " + orgCdList.get(j));
//                        System.out.println("uprCd : " + uprCdList.get(i));
                    //sigunguCdList.add(jObj.get("orgCd").toString());
                    kindCollectList.add(jObj.get("orgCd").toString());
                }
                resultMap.put(orgCdList.get(i), kindCollectList); // map의 키에 시도코드의 리스트의 값들을 넣어준다.
                // value에는 시도코드에 따른 시군구 코드의 list를 넣어준다.
                System.out.println("resultMap!!!!!!!!!!" + resultMap.get(orgCdList.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // try 반복문 끝

//        System.out.println("resultMap!!!!!!!!!!" + resultMap);
        System.out.println("sigungu!!!!---------> " + sigunguCdList); // 시군구 코드 출력
        System.out.println("orgCdList!!!!---------> " + orgCdList); // 시군구 코드 출력

        List<String> keyList = new ArrayList<>();

        Iterator<String> keys = resultMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            resultMap.get(key);
            System.out.println("key-------->" + key);
            keyList.add(key);
        }

        System.out.println("keyList!!!!" + keyList);


//        int h = 0;
//        for (int k = 0; k < orgCdList.size(); k++) {
//            for(String)
//            for (int j = 0; j < sigunguCdList.size(); j++) {
//                String uprCd = orgCdList.get(k);
        //String orgsigunguCd = sigunguCdList.get(h);
//                String orgsigunguCd = sigunguCdList.get(i).;
        //h++;

//                System.out.println("uprCd=======>" + uprCd);
//                System.out.println("orgsigunguCd=====>" + orgsigunguCd);

        for (int q = 0; q < keyList.size(); q++) {
            Object arr = resultMap.get(keyList.get(q));
            List<String> array =(List<String>) arr;

            for (int k = 0; k < array.size(); k++) {
                System.out.println("keyList(i)!!!!!!!!!!!!!!!!" + keyList.get(q));
                System.out.println("!!!!!!!!!!!!++++++++++++++++" + array.get(k));

//    System.out.println("arr---->" + arr.length);
                try {
                    String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/shelter?" +
                            "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                            "&upr_cd=" + keyList.get(q) +
                            "&org_cd=" + array.get(k) +
                            "&_type=json" +
                            "&pageNo=1" +
                            "&numOfRows=5";

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
                System.out.println("responseString===================>responseString" + responseString);


                adminMemberService.shelterRegistNum(responseString, shelterNumDto);
            }

        }
//
//            }
//            }
//        ShelterInfoDto shelterInfoDto = new ShelterInfoDto();
//
//        try {
//            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" +
//                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
//                    "&_type=json" +
//                    "&pageNo=1" +
//                    "&numOfRows=100";
////            System.out.println(">>url: " + apiUrl);
//            URL url = new URL(apiUrl);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            BufferedReader br;
//
//            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//            String returnLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((returnLine = br.readLine()) != null) {
//                result.add(returnLine);
//                response.append(returnLine);
//            }
//            urlConnection.disconnect();
//            responseString = response.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        adminMemberService.shelterRegistInfo(responseString, shelterInfoDto);
//
//
//        petsAdminService.getPetsData();


    }


}
