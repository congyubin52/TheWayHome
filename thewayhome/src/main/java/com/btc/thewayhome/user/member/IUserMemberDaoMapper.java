package com.btc.thewayhome.user.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IUserMemberDaoMapper {

    public boolean isMember(String u_m_id);

    public int insertUserMember(UserMemberDto userMemberDto);

    public int updateUserMember(UserMemberDto userMemberDto);

    public int updateUserMemberPassword(UserMemberDto userMemberDto);

    public UserMemberDto getLatestMemberInfo(UserMemberDto userMemberDto);

    public int deleteUserMember(int u_m_no);

    public UserMemberDto selectUserMemberForLogin(UserMemberDto userMemberDto);

    public UserMemberDto selectUserForLogin(UserMemberDto userMemberDto);
}
