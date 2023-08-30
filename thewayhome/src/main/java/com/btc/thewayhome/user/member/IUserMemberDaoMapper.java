package com.btc.thewayhome.user.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberDaoMapper {

    public boolean isMember(String u_m_id);

    public int insertUserMember(UserMemberDto userMemberDto);

    public int updateUserMember(UserMemberDto userMemberDto);

    public UserMemberDto getLatestMemberInfo(UserMemberDto userMemberDto);

    public int deleteUserMember(int u_m_no);

    public UserMemberDto selectUserMemberForLogin(UserMemberDto userMemberDto);
}
