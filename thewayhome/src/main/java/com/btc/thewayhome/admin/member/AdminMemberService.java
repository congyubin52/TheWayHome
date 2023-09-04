package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class AdminMemberService implements IAdminMemberService {

    final static public int DATABASE_COMMUNICATION_TROUBLE = -1;
    final static public int INSERT_FAIL_AT_DATABASE = 0;
    final static public int INSERT_SUCCESS_AT_DATABASE = 1;

    @Autowired
    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void shelterRegistNum(List<String> shelterNumLists, List<String> shelterNameLists, ShelterNumDto shelterNumDto) {
        log.info("shelterRegistNum()");



        for(int i = 0; i < shelterNumLists.size(); i++){
            shelterNumDto.setS_no(shelterNumLists.get(i));
            shelterNumDto.setS_name(shelterNameLists.get(i));

            boolean isShelterNameForNum = iAdminMemberDaoMapper.isShelterNameForNum(shelterNumDto);

            if (!isShelterNameForNum) {
                // 중복되는 값이 있으면 못 들어가게 한다.
                iAdminMemberDaoMapper.insertShelterNum(shelterNumDto);
            }


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


                boolean isShelterNameForInfo = iAdminMemberDaoMapper.isShelterNameForInfo(shelterInfoDto);

                if (!isShelterNameForInfo) {
                    // 중복되는 값이 있으면 못 들어가게 한다.
                    iAdminMemberDaoMapper.insertShelterInfo(shelterInfoDto);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Map<String, Object> searchShelterName(Map<String, String> shelterNameMap) {
        log.info("[AdminMemberService] searchShelterName()");
        log.info("----------------->{}", shelterNameMap.get("word").toString());
        Map<String, Object> map = new HashMap<>();

        List<ShelterSearchDto> shelterSearchDtos = iAdminMemberDaoMapper.selectSearchShelterName(shelterNameMap.get("word").toString());

        map.put("shelterSearchDtos", shelterSearchDtos);

        return map;
    }

    @Override
    public Map<String, Object> searchShelterNo(Map<String, String> shelterNoMap) {
        log.info("[AdminMemberService] searchShelterNo()");
        log.info("----------------->{}", shelterNoMap.get("word").toString());
        Map<String, Object> map = new HashMap<>();

        List<ShelterSearchDto> shelterSearchDtos = iAdminMemberDaoMapper.selectSearchShelterNo(shelterNoMap.get("word").toString());

        map.put("shelterSearchDtos", shelterSearchDtos);

        return map;
    }

    @Override
    public Map<String, Object> searchShelterAddress(Map<String, String> shelterAddressMap) {
        log.info("[AdminMemberService] searchShelterAddress()");
        log.info("----------------->{}", shelterAddressMap.get("word").toString());
        Map<String, Object> map = new HashMap<>();

        List<ShelterSearchDto> shelterSearchDtos = iAdminMemberDaoMapper.selectSearchShelterAddress(shelterAddressMap.get("word").toString());

        map.put("shelterSearchDtos", shelterSearchDtos);

        return map;
    }

    @Override
    public Map<String, Object> searchShelterPhone(Map<String, String> shelterPhoneMap) {
        log.info("[AdminMemberService] searchShelterPhone()");
        log.info("----------------->{}", shelterPhoneMap.get("word").toString());
        Map<String, Object> map = new HashMap<>();

        List<ShelterSearchDto> shelterSearchDtos = iAdminMemberDaoMapper.selectSearchShelterPhone(shelterPhoneMap.get("word").toString());

        map.put("shelterSearchDtos", shelterSearchDtos);

        return map;
    }


    //회원가입
    @Override
    public int createAccountConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] createAccountConfirm()");

        // 회원을담기
        Map<String, String> isAdminMap = new HashMap<>();

        isAdminMap.put("a_m_id", adminMemberDto.getA_m_id());
        isAdminMap.put("s_no", adminMemberDto.getS_no());

        log.info(isAdminMap);


        //회원 유효성 검사
        boolean isAdmin = iAdminMemberDaoMapper.isAdmin(isAdminMap);

        if (!isAdmin) {
            adminMemberDto.setA_m_pw(passwordEncoder.encode(adminMemberDto.getA_m_pw()));

//            int result = DATABASE_COMMUNICATION_TROUBLE;
            // 회원가입
            int result = iAdminMemberDaoMapper.insertNewAccount(adminMemberDto);


            switch (result) {
                case DATABASE_COMMUNICATION_TROUBLE:
                    log.info("[MemberService] DATABASE_COMMUNICATION_TROUBLE");
                    break;

                case INSERT_FAIL_AT_DATABASE:
                    log.info("[MemberService] INSERT_FAIL_AT_DATABASE");
                    break;

                case INSERT_SUCCESS_AT_DATABASE:
                    log.info("[MemberService] INSERT_SUCCESS_DATABASE");

                    break;
            }
            return result;
        } else {
            return INSERT_FAIL_AT_DATABASE;
            // 중복된 값이 있습니다
        }

    }

    // 로그인
//    @Override
//    public Map<String, Object> loginConfirm(Map<String, String> msgMap) {
//        log.info("[AdminMemberService] loginConfirm()");
//
//        Map<String, Object> map = new HashMap<>();
//        AdminMemberDto adminMemberDto = iAdminMemberDaoMapper.selectAdminForLogin(new AdminMemberDto());
//
//        if (adminMemberDto != null) {
//            map.put("adminMemberDto", adminMemberDto);
//            return map;
//
//        } else {
//            return null;
//
//        }

//        if(adminMemberDto != null && passwordEncoder.matches(adminMemberDto.getA_m_pw(), msgMap.get("a_m_pw"))) {
//            map.put("adminMemberDto", adminMemberDto);
//            return map;
//
//        }  else {
//            return null;
//        }

        /*if(idVerifiedAdminDto != null){
            return idVerifiedAdminDto;

        } else {
            return null;

        }*/

//    }

    //회원정보 수정
    @Override
    public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] memberModifyConfirm()");

        int result = iAdminMemberDaoMapper.updateAccount(adminMemberDto);

        if (result > 0) {
            log.info("[AdminMemberService] result success");
            return iAdminMemberDaoMapper.getLatestAccountInfo(adminMemberDto);
        } else {
            return null;
        }

    }

//    @Override
//    public Map<String, Object> memberModifyConfirm(Map<String, String> msgMap) {
//        log.info("[AdminMemberService] memberModifyConfirm()");
//        Map<String, Object> map = new HashMap<>();
//
//        int result = iAdminMemberDaoMapper.updateAccount(msgMap);
//
//        if(result > 0) {
//            AdminMemberDto adminMemberDto = iAdminMemberDaoMapper.getLatestAccountInfo(msgMap);
//
//            if (adminMemberDto != null) {
//                map.put("adminMemberDto", adminMemberDto);
//                return map;
//            } else {
//                return null;
//            }
//
//        }
//        return null;
//    }


    /*public Map<String, Object> memberModifyConfirm(String a_m_pw, AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] memberModifyConfirm()");
        Map<String, Object> map = new HashMap<>();

        int result = iAdminMemberDaoMapper.updateAccount(adminMemberDto);

        if(result > 0) {
            AdminMemberDto adminMemberDto = iAdminMemberDaoMapper.getLatestAccountInfo(adminMemberDto);

            if (adminMemberDto != null) {
                map.put("adminMemberDto", adminMemberDto);
                return map;
            } else {
                return null;
            }

        }
        return null;
    }*/

    //회원 탈퇴
    @Override
    public int memberDeleteConfirm(int am_no) {
        log.info("deleteConfirm()");

        return iAdminMemberDaoMapper.deleteAdmin(am_no);
    }

    // 관리자 정보 리스트
    @Override
    public List<AdminMemberDto> searchAdminList() {
        log.info("[AdminMemberService] searchAdminList()");

        return iAdminMemberDaoMapper.selectAdminForApproval();

    }

    //(로그인을 위한)관리자 승인 처리
    @Override
    public Map<String, Object> memberApprovalConfirm(int a_m_no) {
        log.info("[AdminMemberService] memberApprovalConfirm()");

        Map<String, Object> map = new HashMap<>();

        int result = -1;

        // update되면 1, 안되면 0 => 문제: ajax에서 if(result > 0) 값 변경. 승인대기, 승인완료일 때 모두 변경해야 하는데 문제가 생김
        result = iAdminMemberDaoMapper.updateAdminForApporoval(a_m_no);     // 0 , 1

        //위의 문제점을 해결하기 위한 것. a_m_approval 값만 꺼내기 위함
        if(result > 0)
            result = Integer.parseInt(iAdminMemberDaoMapper.selectAdminForApprovalFromNo(a_m_no)) ;

        map.put("result", result);

        return map;

    }





/*    public List<AdminMemberDto> searchAdminInfo(Map<String, String> msgMap) {
        log.info("[AdminMemberService] searchAdminInfo()");
        log.info("msgMap no" + msgMap.get("a_m_no"));

        List<AdminMemberDto> adminMemberDtos = iAdminMemberDaoMapper.searchAdminInfoForApproval(msgMap);
        return adminMemberDtos;

    }*/

}
