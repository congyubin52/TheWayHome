package com.btc.thewayhome.admin.member;

import java.util.List;
import java.util.Map;

public interface IAdminMemberService {

    //회원가입
    public int createAccountConfirm(AdminMemberDto memberDto);

    //로그인
    //public AdminMemberDto loginConfirm(AdminMemberDto adminMemberDto);
    public Map<String, Object> loginConfirm(Map<String, String> msgMap);

    //회원정보 수정
    public AdminMemberDto memberModifyConfirm(AdminMemberDto adminMemberDto);
//    public Map<String, Object> memberModifyConfirm(Map<String, String> msgMap);

    //회원 탈퇴
    //public int memberDeleteConfirm(int a_m_no);
    public Map<String, Object> memberDeleteConfirm(int a_m_no);

    //관리자 정보 리스트
    public List<AdminMemberDto> searchAdminList();

    //사용자 승인 처리
    public void memberApprovalConfirm(int a_m_no);


    public void shelterRegistNum(String result, ShelterNumDto shelterNumDto);

    public void shelterRegistInfo(String result, ShelterInfoDto shelterInfoDto);

//    public AdminMemberDto createAccountConfirm(AdminMemberDto adminMemberDto);

    public Map<String, Object> ShelterList();

    public Map<String, Object> searchShelterName(Map<String, String> shelterNameMap);
}
