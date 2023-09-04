package com.btc.thewayhome.admin.pets.admin;

import com.btc.thewayhome.admin.member.AdminMemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPetsAdminDaoMapper {

    public void insertPetsInfo(PetsApiDto petsApiDto);

    //보호소 전체 리스트 - 관리자
    public List<AdminShelterListInfoDto> selectShelter(AdminMemberDto loginedAdminMemberDto);
    //보호소 전체 리스트 - super인 경우
    public boolean isAdminMemberSuper(String a_m_approval);
    //보호소 전체 리스트 - 일반 admin인 경우
    public boolean isAdminMemberBasic(String a_m_id, String a_m_approval);
    //보호소 전체 리스트 - SUPER
    public List<AdminShelterListInfoDto> selectShelterSuper();

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    public List<PetsAdminDto> selectPets(String s_no);

    //보호 동물 리스트 - 일반 관리자만 볼 수 있음(보호동물 리스트 눌렀을 때)
    public List<PetsAdminDto> selectAllPets(AdminMemberDto adminMemberDto);
    public List<PetsAdminDto> selectAllPetsSuper(AdminMemberDto loginedAdminMemberDto);

    //보호 동물 상세 페이지 - 조회수
    public int updatePetsListDetailHits(String an_no);

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    public PetsAdminDto selectPetsListDetail(String an_no);


    public int deletePets(String an_no);

    public int selectPetsForDelete(String an_no);
}
