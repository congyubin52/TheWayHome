package com.btc.thewayhome.admin.member;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static java.lang.System.out;

@Log4j2
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;



    //회원가입
    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("[AdminMemberController] createAccountForm()");

        String nextPage = "admin/member/create_account_form";

        return nextPage;

    }

    @PostMapping("/create_account_confirm")
    public String createAccountConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] createAccountConfirm()");

        //로그인 성공시 홈 메인화면으로 이동
        String nextPage = "redirect:/admin";

        int result = adminMemberService.createAccountConfirm(adminMemberDto);

        if(result <= 0) {
            nextPage = "admin/member/create_account_fail";
        }

        return nextPage;

    }

    // 로그인
    @GetMapping("/member_login_form")
    public String memberLoginForm() {
        log.info("[AdminMemberController] memberLoginForm()");

        String nextPage = "admin/member/member_login_form";

        return nextPage;

    }

//    @PostMapping("/member_login_confirm")
//
//    public int memberLoginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
//        log.info("[AdminMemberController] memberLoginConfirm()");
//
//        // String nextPage = "redirect:/"; 쏴리
//
//        AdminMemberDto loginedAdminMemberDto = adminMemberService.loginConfirm(adminMemberDto);
//
//        if(loginedAdminMemberDto != null) {
//            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
//            session.setMaxInactiveInterval(60 * 30);
//            return 1;
//        }
//            log.info("loginedAdminMemberDto" + loginedAdminMemberDto.getA_m_id());
//
//        return 0;
//
//    }

    @PostMapping("/member_login_confirm")
    @ResponseBody
    public Object memberLoginConfirm(@RequestBody Map<String, String> msgMap, HttpSession session, Model model) {
        log.info("[AdminMemberController] memberLoginConfirm()");

        // String nextPage = "redirect:/"; 쏴리

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

    }
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
    public String memberDeleteConfirm(HttpSession session, HttpServletResponse response) throws IOException {
        log.info("[AdminMemberController] memberDeleteConfirm()");
        response.setContentType("text/html; charset=euc-kr");
        PrintWriter out = response.getWriter();

        String nextPage = "redirect:/admin";

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        int result = adminMemberService.memberDeleteConfirm(loginedAdminMemberDto.getA_m_no());

        if(result<=0) {
            out.println("<script>alert('삭제 실패'); location.href = \"/admin/member/member_modify_form\";</script>");
            out.flush();

        }

        return nextPage;

    }*/

    @PostMapping("/member_delete_confirm")
    @ResponseBody
    public Map<String, Object> memberDeleteConfirm(@RequestBody Map<String, String> msgMap, HttpSession session){
        log.info("[AdminMemberController] memberDeleteConfirm()");

        Map<String, Object> map = adminMemberService.memberDeleteConfirm(Integer.parseInt(msgMap.get("a_m_no")));

        if((int) map.get("result") > 0) {
            session.removeAttribute("loginedAdminMemberDto");

        }
        return map;
    }

}

