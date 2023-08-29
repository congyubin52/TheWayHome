package com.btc.thewayhome.admin.member;

public interface IAdminMemberService {

    //회원가입
    public int createAccountConfirm(AdminMemberDto memberDto);

    //로그인
    public AdminMemberDto loginConfirm(AdminMemberDto adminMemberDto);

    //회원정보 수정
    public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto);

}
