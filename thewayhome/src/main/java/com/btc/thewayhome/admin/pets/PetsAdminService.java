package com.btc.thewayhome.admin.pets;

import com.btc.thewayhome.admin.member.ShelterInfoDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetsAdminService implements IPetsAdminService{

    @Autowired
    IPetsAdminDaoMapper iPetsAdminDaoMapper;

    @Override
    public void petsRegistInfo(String responseString, PetsAdminDto petsAdminDto) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

            JSONArray array = (JSONArray) items.get("item"); // item is a JSONArray inside items

            for (int i = 0; i < array.size(); i++) {
                JSONObject jObj = (JSONObject) array.get(i);
//                petsAdminDto.setS_name(jObj.get("careNm").toString());
//                petsAdminDto.setS_phone(jObj.get("careTel").toString());
//                petsAdminDto.setS_address(jObj.get("careAddr").toString());
//                iPetsAdminDaoMapper.insertPetsInfo(shelterInfoDto);


            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
