package com.btc.thewayhome.user.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserMemberService implements IUserMemberService{

    final static public int DATABASE_COMMUNICATION_TROUBLE = -1;
    final static public int INSERT_FAIL_AT_DATABASE = 0;
    final static public int INSERT_SUCCESS_AT_DATABASE = 1;

    @Autowired
    IUserMemberDaoMapper iUserMemberDaoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public int createAccountConfirm(UserMemberDto userMemberDto) {
        log.info("[UserMemberService] createAccountConfirm()");

        boolean isMember = iUserMemberDaoMapper.isMember(userMemberDto.getU_m_id());

        if(!isMember) {

            userMemberDto.setU_m_pw(passwordEncoder.encode(userMemberDto.getU_m_pw()));

            int result = iUserMemberDaoMapper.insertMember(userMemberDto);

            switch (result) {
                case DATABASE_COMMUNICATION_TROUBLE:
                    System.out.println("[MemberService] DATABASE COMMUNICATION TROUBLE");
                    break;

                case INSERT_FAIL_AT_DATABASE:
                    System.out.println("[MemberService] INSERT FAIL AT DATABASE");
                    break;

                case INSERT_SUCCESS_AT_DATABASE:
                    System.out.println("[MemberService] INSERT SUCCESS AT DATABASE");
                    break;
            }

            return result;

        } else {
            return 0;
        }
    }

    public int userMemeberDeleteConfirm(int u_m_no) {
        log.info("[UserMemberService] userMemeberDeleteConfirm()");

        return iUserMemberDaoMapper.deleteUserMember(u_m_no);

    }

    public UserMemberDto userMemeberModifyConfirm(int u_m_no, UserMemberDto userMemberDto) {
        log.info("[UserMemberService] userMemeberModifyConfirm()");

        userMemberDto.setU_m_no(u_m_no);

        return iUserMemberDaoMapper.updateUserMember(userMemberDto);
    }
}
