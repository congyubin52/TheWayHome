package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminMemberDaoMapper {

    public int insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);

    public List<AdminMemberDto> ShelterNumList(Map <String, String> shelterNumMap);

    public List<AdminMemberDto> ShelterInfoList(Map<String, String> shelterInfoMap);

    public boolean isAdminMember(String aMId, String sNo);

    public List<AdminMemberDto> selectsSearchShelterName(String ShelterNo);



}
