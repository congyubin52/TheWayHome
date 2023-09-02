package com.btc.thewayhome.admin.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminMemberDaoMapper {

    public int insertShelterNum(ShelterNumDto shelterNumDto);

    public void insertShelterInfo(ShelterInfoDto shelterInfoDto);


    public List<ShelterSearchDto> selectSearchShelterName(String ShelterName);

    public List<ShelterSearchDto> selectSearchShelterNo(String ShelterNo);

    public List<ShelterSearchDto> selectSearchShelterAddress(String ShelterAddress);

    public List<ShelterSearchDto> selectSearchShelterPhone(String ShelterPhone);

    //회원가입 - 중복체크
    public boolean isAdmin(Map<String, String> isAdminMap);

    //회원가입 - 계정생성
    public int insertNewAccount(AdminMemberDto adminMemberDto);

    //로그인
    public AdminMemberDto selectAdminForLogin(AdminMemberDto adminMemberDto);
//    public AdminMemberDto selectAdminForLogin(String adminname);

    //회원 정보 수정
    public int updateAccount(AdminMemberDto adminMemberDto);
    // public int updateAccount(Map<String, String> msgMap);

    //회원 정보 수정 - 최신화
    public AdminMemberDto getLatestAccountInfo(AdminMemberDto adminMemberDto);
    //public Map<String,Object> getLatestAccountInfo(Map<String, String> msgMap);

    //회원탈퇴
    public int deleteAccount(int a_m_no);

    //관리자 정보 리스트
    public List<AdminMemberDto> selectAdminForApproval();

    public String selectAdminForApprovalFromNo(int a_m_no);

    //관리자 승인 처리
    public int updateAdminForApporoval(int a_m_no);

    public List<AdminMemberDto> searchAdminInfoForApproval(Map<String, String> msgMap);
}
