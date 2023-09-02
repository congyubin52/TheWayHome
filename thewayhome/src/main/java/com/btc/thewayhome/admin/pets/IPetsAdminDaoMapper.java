package com.btc.thewayhome.admin.pets;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPetsAdminDaoMapper {
    //보호소 리스트
    public List<ShelterListInfoDto> selectShelter();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsAdminDto> selectPets();

    //보호 동물 상세 페이지 - 조회수
    public int updatePetsListDetailHits(int an_no);

    //보호 동물 상세 페이지
    public PetsAdminDto selectPetsListDetail(int an_no);
}
