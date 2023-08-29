package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMemberDaoMapper {

    public int insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);

    public AdminMemberDto ShelterNameJoin(AdminMemberDto adminMemberDto);

    public boolean isAdminMember(String aMId, String sNo);
}
