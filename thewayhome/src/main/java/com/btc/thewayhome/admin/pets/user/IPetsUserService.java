package com.btc.thewayhome.admin.pets.user;

import java.util.List;

public interface IPetsUserService {
    public void petsRegistInfo(String responseString, PetsUserDto petsAdminDto);

    ////보호 동물 리스트
    public List<UserShelterListInfoDto> searchShelterList();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsUserDto> searchPetsList(String s_no);

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    public PetsUserDto searchPetsListDetail(String an_no);
}
