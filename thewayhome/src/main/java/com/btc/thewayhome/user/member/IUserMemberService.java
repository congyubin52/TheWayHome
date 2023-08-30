package com.btc.thewayhome.user.member;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface IUserMemberService {

    public int userMemeberDeleteConfirm(int uMNo);

    public int createAccountConfirm(UserMemberDto userMemberDto);

    public Map<String, Object> memberLoginConfirm(Map<String, String> msgMap);
}
