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


        @Override
    public Map<String, Object> ShelterList() {
        log.info("[MemberService] ShelterNameJoin()");

        Map<String, Object> msgMap = new HashMap<>();

        List<AdminMemberDto> shelterNumDtos = iAdminMemberDaoMapper.ShelterNumList();
        List<AdminMemberDto> shleterInfoDtos = iAdminMemberDaoMapper.ShelterInfoList();

        msgMap.put("shelterNumDtos", shelterNumDtos);
        msgMap.put("shleterInfoDtos", shleterInfoDtos);

        log.info("shelterNumDtos!!!!" +  shelterNumDtos);
        log.info("msgMap!!!!!!!!!!" + msgMap);



        return msgMap;
    }


    @Override
    public Map<String, Object> searchShelterName(Map<String, String> shelterNameMap){
        log.info("[AdminMemberService] searchShelterName()");
        log.info("----------------->{}", shelterNameMap.get("word").toString());
        Map<String, Object> map = new HashMap<>();

        List<ShelterSearchDto> shelterSearchDtos = iAdminMemberDaoMapper.selectSearchShelterName(shelterNameMap.get("word").toString());

        map.put("shelterSearchDtos", shelterSearchDtos);
//        log.info("shelterSearchDtos+++++++", shelterSearchDtos.get(0));
        return map;
    }
}
