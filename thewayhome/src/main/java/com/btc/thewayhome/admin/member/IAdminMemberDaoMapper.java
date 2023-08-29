package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMemberDaoMapper {


    public boolean isAdmin(String a_m_id);

    public void insertNewAccount(AdminMemberDto adminMemberDto);

    public AdminMemberDto selectAdminForLogin(AdminMemberDto adminMemberDto);

    int updateAccount(AdminMemberDto adminMemberDto);

    AdminMemberDto getLatestAccountInfo(AdminMemberDto adminMemberDto);
}
