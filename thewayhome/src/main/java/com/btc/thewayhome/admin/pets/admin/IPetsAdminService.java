package com.btc.thewayhome.admin.pets.admin;

import com.btc.thewayhome.admin.member.AdminMemberDto;

import java.util.List;

public interface IPetsAdminService {

    //보호소 리스트
    public List<AdminShelterListInfoDto> searchShelterList(AdminMemberDto loginedAdminMemberDto);

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsAdminDto> searchPetsList(String s_no);

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    public PetsAdminDto searchPetsListDetail(String an_no);
}
