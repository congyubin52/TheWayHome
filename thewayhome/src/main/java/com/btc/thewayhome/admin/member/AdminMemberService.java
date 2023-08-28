package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AdminMemberService implements IAdminMemberService{

    @Autowired
    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Override
    public void shelterRegistNum(String result, ShelterNumDto shelterNumDto) {
        log.info("shelterRegist()");

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

            JSONArray array = (JSONArray) items.get("item"); // item is a JSONArray inside items

            for (int i = 0; i < array.size(); i++) {
                JSONObject jObj = (JSONObject) array.get(i);
                shelterNumDto.setS_no(jObj.get("careRegNo").toString());
                shelterNumDto.setS_name(jObj.get("careNm").toString());
                iAdminMemberDaoMapper.insertShelterNum(shelterNumDto);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void shelterRegistInfo(String result, ShelterInfoDto shelterInfoDto){
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
}
