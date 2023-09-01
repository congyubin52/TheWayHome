package com.btc.thewayhome.user.member;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface IUserMemberService {

    //회원 가입
    public int createAccountConfirm(UserMemberDto userMemberDto);

    //회원 정보 수정
    public UserMemberDto userMemeberModifyConfirm(UserMemberDto userMemberDto);

    //비밀번호 변경
    public UserMemberDto userMemeberPasswordModifyConfirm(UserMemberDto userMemberDto, String currentPw, String changePw);

    //계정 삭제
    public int userMemeberDeleteConfirm(int uMNo);

}
