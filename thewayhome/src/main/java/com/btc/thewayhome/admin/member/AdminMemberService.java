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
    public Map<String, Object> searchShelterName(Map<String, String> msgMap){
        log.info("[MemberService] ShelterNameJoin()");

        Map<String, Object> map = new HashMap<>();

       List<AdminMemberDto> adminMemberDtos = iAdminMemberDaoMapper.selectsSearchShelterName(msgMap.get("ShelterNo"));

        map.put("adminMemberDtos", adminMemberDtos);

        return map;

    }

    //회원가입
    @Override
    public int createAccountConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] createAccountConfirm()");

        //회원 유효성 검사
        boolean isAdmin = iAdminMemberDaoMapper.isAdmin(adminMemberDto.getA_m_id());

        if (!isAdmin) {
            adminMemberDto.setA_m_pw(passwordEncoder.encode(adminMemberDto.getA_m_pw()));

            int result = DATABASE_COMMUNICATION_TROUBLE;

            try {
                iAdminMemberDaoMapper.insertNewAccount(adminMemberDto);

            } catch (Exception e) {
                e.printStackTrace();

            }

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

        }

    }

    // 로그인
    @Override
    public Map<String, Object> loginConfirm(Map<String, String> msgMap) {
        log.info("[AdminMemberService] loginConfirm()");

        Map<String, Object> map = new HashMap<>();
        AdminMemberDto adminMemberDto = iAdminMemberDaoMapper.selectAdminForLogin(msgMap);

        if (adminMemberDto != null) {
            map.put("adminMemberDto", adminMemberDto);
            return map;

        } else {
            return null;

        }


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

    }

    //회원정보 수정
    @Override
    public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] memberModifyConfirm()");

        int result = iAdminMemberDaoMapper.updateAccount(adminMemberDto);

        if(result > 0) {
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
    public Map<String, Object> memberDeleteConfirm(int a_m_no) {
        log.info("[AdminMemberService] memberDeleteConfirm()");

        Map<String, Object> map = new HashMap<>();

        int result = -1;
        result = iAdminMemberDaoMapper.deleteAccount(a_m_no);

        switch (result) {
            case -1:
                log.info("DATABASE COMMUNICATION ERROR!!");
                break;

            case 0:
                log.info("DATABASE DELETE FAIL!!");
                break;

            case 1:
                log.info("DATABASE DELETE SUCCESS!!");
                break;

        }

        map.put("result", result);

        return map;

    }

    // 관리자 정보 리스트
    @Override
    public List<AdminMemberDto> searchAdminList() {
        log.info("[AdminMemberService] searchAdminList()");

        return iAdminMemberDaoMapper.selectAdminForApproval();

    }

    //관리자 승인 처리
    public void memberApprovalConfirm(int a_m_no) {
        log.info("[AdminMemberService] memberApprovalConfirm()");
        System.out.println("a_m_no: " + a_m_no);

        iAdminMemberDaoMapper.updateAdminForApporoval(a_m_no);

    }

}
