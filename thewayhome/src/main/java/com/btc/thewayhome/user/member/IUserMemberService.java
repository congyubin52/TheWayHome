package com.btc.thewayhome.user.member;


import org.springframework.beans.factory.annotation.Autowired;

public interface IUserMemberService {

    public UserMemberDto userMemeberModifyConfirm(UserMemberDto userMemberDto);

    public int userMemeberDeleteConfirm(int uMNo);


    public int createAccountConfirm(UserMemberDto userMemberDto);

}
