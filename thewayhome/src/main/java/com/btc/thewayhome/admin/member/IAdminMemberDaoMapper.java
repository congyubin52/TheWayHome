package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IAdminMemberDaoMapper {


    public boolean isAdmin(String a_m_id);

    public void insertNewAccount(AdminMemberDto adminMemberDto);

    public AdminMemberDto selectAdminForLogin(Map<String, String> msgMap);

    public int updateAccount(AdminMemberDto adminMemberDto);
    //public int updateAccount(Map<String, String> msgMap);


    public AdminMemberDto getLatestAccountInfo(AdminMemberDto adminMemberDto);

    public int deleteAccount(int a_m_no);
    //public AdminMemberDto getLatestAccountInfo(Map<String, String> msgMap);
}
