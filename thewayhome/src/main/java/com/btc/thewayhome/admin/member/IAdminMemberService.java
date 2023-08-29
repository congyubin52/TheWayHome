package com.btc.thewayhome.admin.member;

public interface IAdminMemberService {

    public void shelterRegistNum(String result, ShelterNumDto shelterNumDto);

    public void shelterRegistInfo(String result, ShelterInfoDto shelterInfoDto);

//    public AdminMemberDto createAccountConfirm(AdminMemberDto adminMemberDto);

    public AdminMemberDto ShelterNameJoin(AdminMemberDto adminMemberDto);
}
