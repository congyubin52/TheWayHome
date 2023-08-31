package com.btc.thewayhome.admin.member;

import java.util.List;
import java.util.Map;

public interface IAdminMemberService {

    public void shelterRegistNum(String result, ShelterNumDto shelterNumDto);

    public void shelterRegistInfo(String result, ShelterInfoDto shelterInfoDto);

//    public AdminMemberDto createAccountConfirm(AdminMemberDto adminMemberDto);

    public Map<String, Object> ShelterList();

    public Map<String, Object> searchShelterName(Map<String, String> shelterNameMap);
}
