package com.btc.thewayhome.user.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberDaoMapper {

    boolean isMember(String u_m_id);

    int insertUserMember(UserMemberDto userMemberDto);

    public int deleteUserMember(int u_m_no);
}
