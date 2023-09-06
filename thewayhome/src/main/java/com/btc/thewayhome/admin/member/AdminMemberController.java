package com.btc.thewayhome.admin.member;

import com.btc.thewayhome.admin.config.GetAreaData;
import com.btc.thewayhome.admin.config.GetPetsData;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;

    @Autowired
    GetAreaData getAreaData;

    @Autowired
    GetPetsData getPetsData;

    /*
     * API DB에 저장 START
     */

    // 보호소 api db에 삽입
    @RequestMapping("/")
    public Object shelterRegistNum() {
        log.info("shelterRegistNum()");

        // DB에 시도, 시군구 데이터를 통해 보호소 api삽입
        // getAreaData.getData();

        // DB에 유기동물 데이터 api삽입
        getPetsData.getpets();

        return null;

        }

    // 보호소 이름을 DB에서 비동기 통신을 위한 매핑
    @PostMapping("/searchShelterName")
    @ResponseBody
    public Object searchShelterName(@RequestParam Map<String, String> shelterNameMap) {
        log.info("searchShelterName()");

        Map<String, Object> map = adminMemberService.searchShelterName(shelterNameMap);

        return map;

    }

    // 보호소 고유번호를 DB에서 비동기 통신을 위한 매핑
    @PostMapping("/searchShelterNo")
    @ResponseBody
    public Object searchShelterNo(@RequestParam Map<String, String> shelterNoMap) {
        log.info("searchShelterNo()");

        Map<String, Object> map = adminMemberService.searchShelterNo(shelterNoMap);

        return map;

    }

    // 보호소 주소를 DB에서 비동기 통신을 위한 매핑
    @PostMapping("/searchShelterAddress")
    @ResponseBody
    public Object searchShelterAddress(@RequestParam Map<String, String> shelterAddressMap) {
        log.info("searchShelterAddress()");

        Map<String, Object> map = adminMemberService.searchShelterAddress(shelterAddressMap);

        return map;

    }

    // 보호소 전화번호를 DB에서 비동기 통신을 위한 매핑
    @PostMapping("/searchShelterPhone")
    @ResponseBody
    public Object searchShelterPhone(@RequestParam Map<String, String> shelterPhoneMap) {
        log.info("searchShelterPhone()");

        Map<String, Object> map = adminMemberService.searchShelterPhone(shelterPhoneMap);

        return map;

    }

    /*
     * API DB에 저장 END
     */

    // 보호소 회원가입(admin 회원가입)
    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("createAccountForm()");

        String nextPage = "admin/member/create_account_form";

        return nextPage;

    }

    @PostMapping("/create_account_confirm")
    public String createAccountConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] createAccountConfirm()");

        String nextPage = "redirect:/admin/member/create_account_form";

        int result = adminMemberService.createAccountConfirm(adminMemberDto);

        if(result > adminMemberService.INSERT_FAIL_AT_DATABASE) {
            nextPage = "/admin/member/create_account_success";

        }
        return nextPage;

    }

    // 관리자 로그인 기능
    @GetMapping("/member_login_form")
    public String memberLoginForm() {
        log.info("memberLoginForm()");

        String nextPage = "admin/member/member_login_form";

        return nextPage;

    }

    // 관리자 로그아웃 기능
    @GetMapping("/member_logout_comfirm")
    public String memberLogoutConfirm(HttpSession session) {
        log.info("memberLogoutConfirm()");

        String nextPage = "redirect:/admin";

        session.removeAttribute("loginedAdminMemberDto");

        return nextPage;

    }

    // 관리자 회원정보 수정 기능
    @GetMapping("/member_modify_form")
    public String memberModifyForm() {
        log.info("memberModifyForm()");

        String nextPage = "admin/member/member_modify_form";

        return nextPage;

    }
    
    // 관리자 정보수정 기능
   /* @PostMapping("/member_modify_confirm")
    public void memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto, HttpServletResponse response) throws IOException {
        log.info("memberModifyConfirm()");

        // 정보 수정한 것을 Dto에 담아줌
        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        // 위의 메서드에서 수정된 것이 있다면 session에 키,값 쌍으로 저장
        if (loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            // alert를 띄우기 위한 코드
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            // 성공 시 alert 메시지를 띄우고 페이지를 이동
                out.println("<script>alert('수정이 완료되었습니다.'); location.href = \"/admin/member/member_modify_form\";</script>");
                out.flush();
        } else {
            // 실패 시 alert 메시지를 띄우고 페이지를 이동
            PrintWriter out = response.getWriter();
            out.println("<script>alert(''); location.href = \"/admin/member/member_modify_form\";</script>");
            out.flush();

        }
    }*/

    // 관리자 정보수정 기능
    @PostMapping("/member_modify_confirm")
    @ResponseBody
    public AdminMemberDto memberModifyConfirm(@RequestBody Map<String, String> msgMap,HttpSession session) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        //service에서 adminMemberDto로 받으므로 dto로 변환해서 들고 가야 함
        AdminMemberDto adminMemberDto = new AdminMemberDto();
        adminMemberDto.setA_m_no(Integer.parseInt(msgMap.get("a_m_no")));
        adminMemberDto.setA_m_id(msgMap.get("a_m_id"));
        adminMemberDto.setA_m_pw(msgMap.get("a_m_pw"));

        adminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if(adminMemberDto != null) {
            AdminMemberDto loginedAdminMemberDto = adminMemberDto;
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);

            return adminMemberDto;

        }
        return null;
    }

    
    // 관리자 계정 삭제 기능
    @GetMapping("/member_delete_confirm")
    public String memberDeleteConfirm(HttpSession session) {
        log.info("memberDeleteConfirm()");

        String nextPage = "redirect:/admin";

        //로그인되어있는 사람만 삭제를 할 수 있기 때문에 확인하기 위해서 세션 정보 들고와줌
        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto)session.getAttribute("loginedAdminMemberDto");

        //분기 태우기
        if(loginedAdminMemberDto == null) { 	//로그인 되어 있지 않다면
            return "redirect:/admin";

        }
        int result = adminMemberService.memberDeleteConfirm(loginedAdminMemberDto.getA_m_no());

        if(result > 0) {  //admin 멤버 삭제시
            session.removeAttribute("loginedAdminMemberDto");	//세션 날려줘야 함

        } else {
            nextPage = "admin/delete_fail";

        }
        return nextPage;

    }

    //관리자 정보 리스트 기능(모든 관리자 목록)
    @GetMapping("/search_admin_list")
    public String searchAdminList(Model model, HttpSession session) {
        log.info("searchAdminList()");

        String nextPage = "/admin/member/search_admin_list";

        List<AdminMemberDto> adminMemberDtos = adminMemberService.searchAdminList();
        model.addAttribute("adminMemberDtos", adminMemberDtos);

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        if (loginedAdminMemberDto == null) {
            return "redirect:/admin";

        }
        return nextPage;

    }

    // 로그인을 위한 관리자 승인 처리 기능 (Super가 일반 관리자 승인시 로그인 가능)
    @PostMapping("/member_approval_confirm")
    @ResponseBody
    public Object memberApprovalConfirm(AdminMemberDto adminMemberDto) {
        log.info("memberApprovalConfirm()");

        // 비동기 통신시 키, 값 쌍을 통해 원하는 값을 한번에 찾기 위해 Map 사용
        Map<String, Object> map = adminMemberService.memberApprovalConfirm(adminMemberDto.getA_m_no());

        return map;

    }

}
