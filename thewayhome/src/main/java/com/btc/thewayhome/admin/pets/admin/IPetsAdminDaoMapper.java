package com.btc.thewayhome.admin.pets.admin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPetsAdminDaoMapper {

    //보호소 전체 리스트 - 관리자
    public List<AdminShelterListInfoDto> selectShelter();
    //보호소 전체 리스트 - super인 경우
    public boolean isAdminMember(String a_m_approval);
    //보호소 전체 리스트 - SUPER
    public List<AdminShelterListInfoDto> selectShelterSuper();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsAdminDto> selectPets(String s_no);

    //보호 동물 상세 페이지 - 조회수
    public int updatePetsListDetailHits(String an_no);

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    PetsAdminDto selectPetsListDetail(String an_no);


}
