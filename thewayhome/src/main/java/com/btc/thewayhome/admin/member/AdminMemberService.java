package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class AdminMemberService implements IAdminMemberService {

    final static public int DATABASE_COMMUNICATION_TROUBLE = -1;
    final static public int INSERT_FAIL_AT_DATABASE = 0;
    final static public int INSERT_SUCCESS_AT_DATABASE = 1;

    @Autowired
    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
//    @Override
    /*public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberService] memberModifyConfirm()");

        int result = iAdminMemberDaoMapper.updateAccount(adminMemberDto);
        if(result > 0) {
            return iAdminMemberDaoMapper.getLatestAccountInfo(adminMemberDto);
        } else {
            return null;
        }

    }*/


    @Override
    public Map<String, Object> memberModifyConfirm(Map<String, String> msgMap) {
        log.info("[AdminMemberService] memberModifyConfirm()");
        Map<String, Object> map = new HashMap<>();

        int result = iAdminMemberDaoMapper.updateAccount(msgMap);

        if(result > 0) {
            AdminMemberDto adminMemberDto = iAdminMemberDaoMapper.getLatestAccountInfo(msgMap);

            if (adminMemberDto != null) {
                map.put("adminMemberDto", adminMemberDto);
                return map;
            } else {
                return null;
            }

        }
        return null;
    }


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

    }

