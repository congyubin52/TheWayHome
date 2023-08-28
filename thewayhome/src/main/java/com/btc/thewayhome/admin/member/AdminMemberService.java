package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
