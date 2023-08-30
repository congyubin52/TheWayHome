package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class AdminMemberService implements IAdminMemberService {

    @Autowired
    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Override
    public void shelterRegistNum(String result, ShelterNumDto shelterNumDto) {
        log.info("shelterRegist()");
        System.out.println("result----------> " + result);


        try {

            //String responseString = result.toString();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            System.out.println("service ------------------------------------> " + jsonObj);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

            JSONArray array = (JSONArray) items.get("item"); // item is a JSONArray inside items


            for (int i = 0; i < array.size(); i++) {
                JSONObject jObj = (JSONObject) array.get(i);
                shelterNumDto.setS_no(jObj.get("careRegNo").toString());
                shelterNumDto.setS_name(jObj.get("careNm").toString());
                System.out.println("s_no!!!!!!!!!!!!!!!!! " + shelterNumDto.getS_no());
                System.out.println("s_name!!!!!!!!!!!!!!!!! " + shelterNumDto.getS_name());
                iAdminMemberDaoMapper.insertShelterNum(shelterNumDto);
            }
//            Object item = items.get("item");
//
//            if (item instanceof JSONArray) {
//                JSONArray array = (JSONArray) item; // item is a JSONArray
//
//                for (int i = 0; i < array.size(); i++) {
//                    JSONObject jObj = (JSONObject) array.get(i);
//                    shelterNumDto.setS_no(jObj.get("careRegNo").toString());
//                    shelterNumDto.setS_name(jObj.get("careNm").toString());
//                    iAdminMemberDaoMapper.insertShelterNum(shelterNumDto);
//                }
//            } else if (item instanceof JSONObject) {
//                JSONObject jObj = (JSONObject) item; // item is a single JSONObject
//                shelterNumDto.setS_no(jObj.get("careRegNo").toString());
//                shelterNumDto.setS_name(jObj.get("careNm").toString());
//                iAdminMemberDaoMapper.insertShelterNum(shelterNumDto);
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void shelterRegistInfo(String result, ShelterInfoDto shelterInfoDto) {
        log.info("shelterRegistInfo()");

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

            JSONArray array = (JSONArray) items.get("item"); // item is a JSONArray inside items

            for (int i = 0; i < array.size(); i++) {
                JSONObject jObj = (JSONObject) array.get(i);
                shelterInfoDto.setS_name(jObj.get("careNm").toString());
                shelterInfoDto.setS_phone(jObj.get("careTel").toString());
                shelterInfoDto.setS_address(jObj.get("careAddr").toString());
                iAdminMemberDaoMapper.insertShelterInfo(shelterInfoDto);


            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public AdminMemberDto createAccountConfirm(AdminMemberDto adminMemberDto) {
//        log.info("createAccountConfirm()");
//
//        System.out.println("[MemberService] createAccountConfirm");
//
//        boolean isAdminMember = iAdminMemberDaoMapper.isAdminMember(adminMemberDto.getA_m_id(), adminMemberDto.getS_no());


//        if (!isAdminMember) {
//
//            int result = iAdminMemberDaoMapper.insertMember(adminMemberDto);
//
//            switch (result) {
//                case -1:
//                    System.out.println("[MemberService] DATABASE COMMUNICATION TROUBLE");
//                    break;
//
//                case 0:
//                    System.out.println("[MemberService] INSERT FAIL AT DATABASE");
//                    break;
//
//                case 1:
//                    System.out.println("[MemberService] INSERT SUCCESS AT DATABASE");
//                    break;
//
//            }
//
//            return result;
//        } else {
//            return 0;
//        }
//

//        }
//}
        @Override
    public List<AdminMemberDto> ShelterList(AdminMemberDto adminMemberDto) {
        log.info("[MemberService] ShelterNameJoin()");

        Map<String, String> shelterNumMap = new HashMap<>();
        shelterNumMap.put(adminMemberDto.getS_no(), adminMemberDto.getS_name());

        Map<String, String> shelterInfoMap = new HashMap<>();
            shelterInfoMap.put(adminMemberDto.getS_phone(), adminMemberDto.getS_address());

        List<AdminMemberDto> shelterNumDtos = iAdminMemberDaoMapper.ShelterNumList(shelterNumMap);
        List<AdminMemberDto> shleterInfoDtos = iAdminMemberDaoMapper.ShelterInfoList(shelterInfoMap);

        return null;
    }


    @Override
    public Map<String, Object> searchShelterName(Map<String, String> msgMap){
        log.info("[MemberService] ShelterNameJoin()");

        Map<String, Object> map = new HashMap<>();

       List<AdminMemberDto> adminMemberDtos = iAdminMemberDaoMapper.selectsSearchShelterName(msgMap.get("ShelterNo"));

        map.put("adminMemberDtos", adminMemberDtos);

        return map;

    }
}
