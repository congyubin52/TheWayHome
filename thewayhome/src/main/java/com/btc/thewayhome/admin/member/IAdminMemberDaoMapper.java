package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAdminMemberDaoMapper {

    public void insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);
}
