package com.btc.thewayhome.admin.member;

public interface IAdminMemberDaoMapper {

    public int insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);

    public List<AdminMemberDto> ShelterNumList();

    public List<AdminMemberDto> ShelterInfoList();

    public boolean isAdminMember(String aMId, String sNo);

    public List<AdminMemberDto> selectsSearchShelterName(String ShelterNo);



}
