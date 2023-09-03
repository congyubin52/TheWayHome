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
    public void petsRegistInfo(String responseString, PetsApiDto petsApiDto) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);

            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

            JSONArray array = (JSONArray) items.get("item"); // item is a JSONArray inside items

            for (int i = 0; i < array.size(); i++) {
                JSONObject jObj = (JSONObject) array.get(i);

                petsApiDto.setAn_no(jObj.get("desertionNo").toString());
                petsApiDto.setAn_thumbnail(jObj.get("filename").toString());
                petsApiDto.setAn_happen_date(jObj.get("happenDt").toString());
                petsApiDto.setAn_happen_place(jObj.get("happenPlace").toString());
                petsApiDto.setAn_k_kind(jObj.get("kindCd").toString());
                petsApiDto.setAn_color(jObj.get("colorCd").toString());
                petsApiDto.setAn_age(jObj.get("age").toString());
                petsApiDto.setAn_weight(jObj.get("weight").toString());
                petsApiDto.setAn_n_notice_no(jObj.get("noticeNo").toString());
                petsApiDto.setAn_n_start(jObj.get("noticeSdt").toString());
                petsApiDto.setAn_n_end(jObj.get("noticeEdt").toString());
                petsApiDto.setAn_image(jObj.get("popfile").toString());
                petsApiDto.setAn_p_s_state(jObj.get("processState").toString());
                petsApiDto.setAn_g_gender(jObj.get("sexCd").toString());
                petsApiDto.setAn_ne_neuter(jObj.get("neuterYn").toString());
                petsApiDto.setAn_special_mark(jObj.get("specialMark").toString());
                petsApiDto.setS_name(jObj.get("careNm").toString());
                petsApiDto.setS_phone(jObj.get("careTel").toString());
                petsApiDto.setS_address(jObj.get("careAddr").toString());
                petsApiDto.setAn_o_organization(jObj.get("orgNm").toString());
                petsApiDto.setAn_o_charge(jObj.get("chargeNm").toString());
                petsApiDto.setAn_o_charge_tel(jObj.get("officetel").toString());
                iPetsAdminDaoMapper.insertPetsInfo(petsApiDto);

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
