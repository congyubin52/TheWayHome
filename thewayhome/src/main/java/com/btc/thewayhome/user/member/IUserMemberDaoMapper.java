package com.btc.thewayhome.user.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberDaoMapper {

    boolean isMember(String uMId);

    int insertMember(UserMemberDto userMemberDto);

    public UserMemberDto updateUserMember(UserMemberDto userMemberDto);

    public int deleteUserMember(int u_m_no);
}
