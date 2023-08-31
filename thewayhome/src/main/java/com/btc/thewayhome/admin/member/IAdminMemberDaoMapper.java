package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminMemberDaoMapper {

    public int insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);

    public List<AdminMemberDto> ShelterNumList();

    public List<AdminMemberDto> ShelterInfoList();

    public boolean isAdminMember(String aMId, String sNo);

    public List<ShelterSearchDto> selectSearchShelterName(String ShelterName);



}
