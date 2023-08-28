package com.btc.thewayhome.user.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserMemberService implements IUserMemberService{

    @Autowired
    IUserMemberDaoMapper iUserMemberDaoMapper;

    public int userMemeberDeleteConfirm(int u_m_no) {
        log.info("[UserMemberService] userMemeberDeleteConfirm");

        return iUserMemberDaoMapper.deleteUserMember(u_m_no);


    }
}
