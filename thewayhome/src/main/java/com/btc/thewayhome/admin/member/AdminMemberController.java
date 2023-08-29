package com.btc.thewayhome.admin.member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        String nextPage = "redirect:/";

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

    @PostMapping("/member_login_confirm")
    public String memberLoginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
        log.info("[AdminMemberController] memberLoginConfirm()");

        String nextPage = "redirect:/";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.loginConfirm(adminMemberDto);

        if(loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);

            log.info("loginedAdminMemberDto" + loginedAdminMemberDto.getA_m_id());

        } else {
            nextPage = "admin/member/member_login_fail";
        }

        return nextPage;

    }

    //로그아웃
    @GetMapping("/member_logout_comfirm")
    public String memberLogoutConfirm(HttpSession session) {
        log.info("[AdminMemberController] memberLogoutConfirm()");

        String nextPage = "redirect:/";

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
    public String memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        String nextPage = "/admin/member/member_modify_success";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if(loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);

        } else {
            nextPage = "/admin/member/member_modify_fail";

        }

        return nextPage;

    }

}
