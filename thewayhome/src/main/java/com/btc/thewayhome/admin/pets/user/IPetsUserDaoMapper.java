package com.btc.thewayhome.admin.pets.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPetsUserDaoMapper {
    //보호소 리스트
    public List<UserShelterListInfoDto> selectShelter();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsUserDto> selectPets(String s_no);

    //보호 동물 리스트(보호소 리스트 상세 페이지 xx)
    public List<PetsUserDto> selectAllPets();

    //보호 동물 상세 페이지 - 조회수
    public int updatePetsListDetailHits(String an_no);

    //보호 동물 상세 페이지
    public PetsUserDto selectPetsListDetail(String an_no);


}
