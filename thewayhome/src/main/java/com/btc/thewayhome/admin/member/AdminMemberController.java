package com.btc.thewayhome.admin.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

        String nextPage = "member/admin/create_account_form";

        return nextPage;

    }

    @PostMapping("/create_account_confirm")
    public String createAccountConfirm(AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] createAccountConfirm()");

        //로그인 성공시 홈 메인화면으로 이동
        String nextPage = "redirect:/";

        int result = adminMemberService.createAccountConfirm(adminMemberDto);

        if(result <= 0) {
            nextPage = "member/admin/create_account_fail";
        }

        return nextPage;

    }

    // 로그인
    @GetMapping("/login_form")
    public String loginForm() {
        log.info("[AdminMemberController] loginForm()");

        String nextPage = "/member/admin/login_form";

        return nextPage;

    }



}
