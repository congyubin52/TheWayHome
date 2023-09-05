package com.btc.thewayhome.admin.pets.admin;

import com.btc.thewayhome.admin.member.AdminMemberDto;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PetsAdminService implements IPetsAdminService{

    final static public int PETS_REGISTER_SUCCESS = 1;		// 신규 도서 등록 성공
    final static public int PETS_REGISTER_FAIL = -1;

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

                String kindCd = jObj.get("kindCd").toString();
//                petsApiDto.setAn_k_kind(jObj.get("kindCd").toString());
                String[] splitKindCd = kindCd.split(" ");
                if (splitKindCd.length > 0) {
                    petsApiDto.setAn_an_kind(splitKindCd[0]);
                    if(splitKindCd.length > 1){
                        StringBuilder an_k_kind = new StringBuilder();
                        for (int j = 1; j < splitKindCd.length; j++) {
                            if (j > 1) {
                                an_k_kind.append(" "); // 중간에 공백 추가
                            }
                            an_k_kind.append(splitKindCd[j]); // 나머지 한 글자를 이어붙임
                        }
                        petsApiDto.setAn_k_kind(an_k_kind.toString());
                    }
                }

                log.info(petsApiDto.getAn_k_kind());
                log.info(petsApiDto.getAn_an_kind());

                petsApiDto.setAn_color(jObj.get("colorCd").toString());
                petsApiDto.setAn_age(jObj.get("age").toString());
                petsApiDto.setAn_weight(jObj.get("weight").toString());
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
//                petsApiDto.setAn_o_organization(jObj.get("orgNm").toString());
//                petsApiDto.setAn_o_charge(jObj.get("chargeNm").toString());
//                petsApiDto.setAn_o_charge_tel(jObj.get("officetel").toString());
                iPetsAdminDaoMapper.insertPetsInfo(petsApiDto);

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //보호소 리스트 -> SUPER, 일반 ADMIN에 따라 나눔
    @Override
    public List<AdminShelterListInfoDto> searchShelterList(AdminMemberDto loginedAdminMemberDto) {
        log.info("searchShelterList()");

        //일반 admin 찾기
        boolean isAdmin = iPetsAdminDaoMapper.isAdminMemberBasic(loginedAdminMemberDto.getA_m_id(), loginedAdminMemberDto.getA_m_approval());

        List<AdminShelterListInfoDto> adminShelterListInfoDtos;

        if(isAdmin) {
            log.info("isAdmin()");

            // 일반 ADMIN인 경우
            adminShelterListInfoDtos = iPetsAdminDaoMapper.selectShelter(loginedAdminMemberDto);

        } else {
            //super 계정 찾기
            boolean isSuper = iPetsAdminDaoMapper.isAdminMemberSuper(loginedAdminMemberDto.getA_m_approval());

            if (isSuper) {
                log.info("isSuper()");
                // SUPER ADMIN인 경우
                adminShelterListInfoDtos = iPetsAdminDaoMapper.selectShelterSuper();

            } else {
                return null;

            }

        }
        return adminShelterListInfoDtos;

    }

    //보호 동물 리스트 - 보호소 리스트 상세 페이지
    @Override
    public List<PetsAdminDto> searchPetsList(String s_no) {
        log.info("searchShelterList()");

        List<PetsAdminDto> petsAdminDtos = iPetsAdminDaoMapper.selectPets(s_no);

        return petsAdminDtos;

    }

    //보호 동물 리스트 - 메뉴바에서 보호 동물 클릭 시 나타나는 페이지
    @Override
    public List<PetsAdminDto> searchAllPetsList(AdminMemberDto loginedAdminMemberDto) {
        log.info("searchPetsListBasic()");

        //일반 admin 찾기
        boolean isAdmin = iPetsAdminDaoMapper.isAdminMemberBasic(loginedAdminMemberDto.getA_m_id(), loginedAdminMemberDto.getA_m_approval());

        List<PetsAdminDto> petsAdminDtos;

        if(isAdmin) {
            // 일반 ADMIN인 경우
            petsAdminDtos = iPetsAdminDaoMapper.selectAllPets(loginedAdminMemberDto);

        } else {
            //super 계정 찾기
            boolean isSuper = iPetsAdminDaoMapper.isAdminMemberSuper(loginedAdminMemberDto.getA_m_approval());

            if (isSuper) {
                // SUPER ADMIN인 경우
                petsAdminDtos = iPetsAdminDaoMapper.selectAllPetsSuper(loginedAdminMemberDto);

            } else {
                return null;

            }
        }
        return petsAdminDtos;

    }

    // 보호 동물 상세 페이지
    @Override
    public PetsAdminDto searchPetsListDetail(String an_no) {
        log.info("searchPetsListDetail()");

        PetsAdminDto petsAdminDto = iPetsAdminDaoMapper.selectPetsListDetail(an_no);

        return petsAdminDto;

    }

    // 보호 동물 등록 성공 or 실패 확인
    @Override
    public int petsRegistConfirm(PetsApiDto petsApiDto) {
        log.info("petsRegistConfirm()");

        int result = iPetsAdminDaoMapper.registPets(petsApiDto);

        if(result > 0){
            return PETS_REGISTER_SUCCESS;

        } else {
            return PETS_REGISTER_FAIL;

        }
    }

    // 보호 동물 삭제
    @Override
    public int petsDeleteConfirm(String an_no) {
        log.info("petsDeleteConfirm()");

//        log.info("an_no----------------->{}", petsAdminDto.getAn_no());

        return iPetsAdminDaoMapper.deletePets(an_no);

    }

}
