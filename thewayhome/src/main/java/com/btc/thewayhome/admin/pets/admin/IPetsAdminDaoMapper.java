package com.btc.thewayhome.admin.pets.admin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPetsAdminDaoMapper {

    //보호소 전체 리스트
    public List<AdminShelterListInfoDto> selectShelter();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsAdminDto> selectPets(String s_no);

    //보호 동물 상세 페이지 - 조회수
    public int updatePetsListDetailHits(int an_no);

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    PetsAdminDto selectPetsListDetail(int an_no);
}
