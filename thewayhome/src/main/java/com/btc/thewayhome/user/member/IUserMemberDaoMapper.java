package com.btc.thewayhome.user.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMemberDaoMapper {

    public int deleteUserMember(int u_m_no);
}
