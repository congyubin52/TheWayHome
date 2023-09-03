package com.btc.thewayhome.admin.member;

import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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


    // 보호소 api db에 삽입
    @RequestMapping("/")
    public Object shelterRegistNum() {
        log.info("shelterRegistNum()");

        getAreaData.getData();
        getPetsData.getpets();

            return null;
        }


    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("createAccountForm()");

        String nextPage = "admin/member/create_account_form";

//            Map<String, Object> msgMap = adminMemberService.ShelterList();
//            model.addAttribute("shelterNumDtos", msgMap.get("shelterNumDtos"));
//            model.addAttribute("shleterInfoDtos", msgMap.get("shleterInfoDtos"));

        return nextPage;

    }

    @PostMapping("/searchShelterName")
    @ResponseBody
    public Object searchShelterName(@RequestParam Map<String, String> shelterNameMap) {
        log.info("searchShelterName()");
        Map<String, Object> map = adminMemberService.searchShelterName(shelterNameMap);

        return map;
    }

    @PostMapping("/searchShelterNo")
    @ResponseBody
    public Object searchShelterNo(@RequestParam Map<String, String> shelterNoMap) {
        log.info("searchShelterNo()");
        Map<String, Object> map = adminMemberService.searchShelterNo(shelterNoMap);

        return map;
    }

    @PostMapping("/searchShelterAddress")
    @ResponseBody
    public Object searchShelterAddress(@RequestParam Map<String, String> shelterAddressMap) {
        log.info("searchShelterAddress()");
        Map<String, Object> map = adminMemberService.searchShelterAddress(shelterAddressMap);

        return map;
    }

    @PostMapping("/searchShelterPhone")
    @ResponseBody
    public Object searchShelterPhone(@RequestParam Map<String, String> shelterPhoneMap) {
        log.info("searchShelterPhone()");
        Map<String, Object> map = adminMemberService.searchShelterPhone(shelterPhoneMap);

        return map;
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

    // 회원가입할 때 DB에 보호소명으로 조인된 테이블 데이터를 비동기로 출력을 위한 것
//    @PostMapping("/searchShelterName")
//    @ResponseBody
//    public Object searchShelterName(@RequestBody Map<String, String> msgMap){
//        Map<String, Object> map = adminMemberService.searchShelterName(msgMap);
//        List<String>
//        return map;
//    }


    // 로그인
    @GetMapping("/member_login_form")
    public String memberLoginForm() {
        log.info("[AdminMemberController] memberLoginForm()");

        String nextPage = "admin/member/member_login_form";

        return nextPage;

    }

 /*   @PostMapping("/member_login_confirm")

    public int memberLoginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
        log.info("[AdminMemberController] memberLoginConfirm()");

        // String nextPage = "redirect:/"; 쏴리

        AdminMemberDto loginedAdminMemberDto = adminMemberService.loginConfirm(adminMemberDto);

        if(loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);
            return 1;
        }
            log.info("loginedAdminMemberDto" + loginedAdminMemberDto.getA_m_id());

        return 0;

    }*/

   /* @PostMapping("/member_login_confirm")
    @ResponseBody
    public Object memberLoginConfirm(@RequestBody Map<String, String> msgMap, HttpSession session, Model model) {
        log.info("[AdminMemberController] memberLoginConfirm()");

        Map<String, Object> map = adminMemberService.loginConfirm(msgMap);

        AdminMemberDto loginedAdminMemberDto = null;
        if (map != null) {
            loginedAdminMemberDto = (AdminMemberDto) map.get("adminMemberDto");
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);
            return map;

        }

        model.addAttribute("loginedAdminMemberDto", loginedAdminMemberDto);

        return null;

    }*/

    //로그아웃
    @GetMapping("/member_logout_comfirm")
    public String memberLogoutConfirm(HttpSession session) {
        log.info("[AdminMemberController] memberLogoutConfirm()");

        String nextPage = "redirect:/admin";

        session.removeAttribute("loginedAdminMemberDto");


        return nextPage;

    }

    //회원정보 수정
    @GetMapping("/member_modify_form")
    public String memberModifyForm() {
        log.info("[AdminMemberController] memberModifyForm()");

        String nextPage = "admin/member/member_modify_form";

        return nextPage;

    }

    @PostMapping("/member_modify_confirm")
    @ResponseBody
    public AdminMemberDto memberModifyConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        //service에서 adminMemberDto로 받으므로 dto로 변환해서 들고 가야 함
        AdminMemberDto adminMemberDto = new AdminMemberDto();
        adminMemberDto.setA_m_no(Integer.parseInt(msgMap.get("a_m_no")));
        adminMemberDto.setA_m_id(msgMap.get("a_m_id"));
        adminMemberDto.setA_m_pw(msgMap.get("a_m_pw"));

        adminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if (adminMemberDto != null) {
            AdminMemberDto loginedAdminMemberDto = adminMemberDto;
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            return adminMemberDto;

        }
        return null;
    }

  /* @PostMapping("/member_modify_confirm")
   @ResponseBody
   public Map<String, Object> memberModifyConfirm(@RequestParam(value = "a_m_pw", required = false) String a_m_pw, HttpSession session, AdminMemberDto adminMemberDto) {
       log.info("[AdminMemberController] memberModifyConfirm()");

       Map<String, Object> resultMap = adminMemberService.memberModifyConfirm(a_m_pw, adminMemberDto);

        if(loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);
        }
       return resultMap;

   }*/

   /* @PostMapping("/member_modify_confirm")
    public Object memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        String nextPage = "admin/member/member_modify_form";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if(loginedAdminMemberDto != null) {

            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);
        }
        else {
            nextPage = "admin/member/member_modify_fail";

        }

        return nextPage;
    }*/

/*    @PostMapping("/member_modify_confirm")
    public void memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto, HttpServletResponse response) throws IOException {
        log.info("[AdminMemberController] memberModifyConfirm()");

//        String nextPage = "admin/member/member_modify_form";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if (loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            // contentType을 먼저하지 않으면, 한글이 깨질 수 있음
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


    //회원 탈퇴
/*    @GetMapping("/member_delete_confirm")
    @ResponseBody
    public Map<String, Object> memberDeleteConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("[AdminMemberController] memberDeleteConfirm()");

        Map<String, Object> map = adminMemberService.memberDeleteConfirm(Integer.parseInt(msgMap.get("a_m_no")));

        if ((int) map.get("result") > 0) {
            session.removeAttribute("loginedAdminMemberDto");

        }
        return map;
    }*/

    @GetMapping("/member_delete_confirm")
    public String memberDeleteConfirm(HttpSession session) {
        log.info("[AdminMemberController] memberDeleteConfirm()");

        String nextPage = "redirect:/admin";

        //로그인되어있는 사람만 삭제를 할 수 있기 때문에 확인하기 위해서 세션 정보 들고와줌
        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto)session.getAttribute("loginedAdminMemberDto");
        System.out.println("---------->" + loginedAdminMemberDto);

        //분기 태우기
        if(loginedAdminMemberDto == null) {	//로그인 되어있지 않다면

            return "redirect:/admin";

        }

        int result = adminMemberService.memberDeleteConfirm(loginedAdminMemberDto.getA_m_no());
        if(result > 0) { //admin 멤버 삭제시
            session.removeAttribute("loginedAdminMemberDto");	//세션 날려줘야 함

        } else {
            nextPage = "admin/delete_fail";

        }

        return nextPage;

    }

    //관리자 정보 리스트
    @GetMapping("/search_admin_list")
    public String searchAdminList(Model model, HttpSession session) {
        log.info("[AdminMemberController] searchAdminList()");

        List<AdminMemberDto> adminMemberDtos = adminMemberService.searchAdminList();

        model.addAttribute("adminMemberDtos", adminMemberDtos);

        String nextPage = "/admin/member/search_admin_list";

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        if (loginedAdminMemberDto == null) {
            return "redirect:/admin";

        }

        return nextPage;

    }

    //(로그인을 위한) 관리자 승인 처리
    @PostMapping("/member_approval_confirm")
    @ResponseBody
    public Object memberApprovalConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] memberApprovalConfirm()");

        Map<String, Object> map = adminMemberService.memberApprovalConfirm(adminMemberDto.getA_m_no());

        return map;

    }

}
