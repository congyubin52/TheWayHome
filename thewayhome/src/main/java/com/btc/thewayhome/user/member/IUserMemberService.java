package com.btc.thewayhome.user.member;


public interface IUserMemberService {

    public UserMemberDto userMemberModifyConfirm(UserMemberDto userMemberDto);

    public UserMemberDto userMemberPasswordModifyConfirm(UserMemberDto userMemberDto, String currentPw, String changePw);

    public int userMemberDeleteConfirm(int uMNo);


    public int createAccountConfirm(UserMemberDto userMemberDto);

}
