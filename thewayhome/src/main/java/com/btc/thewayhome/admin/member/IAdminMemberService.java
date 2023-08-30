package com.btc.thewayhome.admin.member;

import java.util.Map;

public interface IAdminMemberService {

    //회원가입
    public int createAccountConfirm(AdminMemberDto memberDto);

    //로그인
    //public AdminMemberDto loginConfirm(AdminMemberDto adminMemberDto);
    public Map<String, Object> loginConfirm(Map<String, String> msgMap);

    //회원정보 수정
    public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto);
    //public Map<String, Object> memberModifyConfirm(Map<String, String> msgMap);

}
