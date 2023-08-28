package com.btc.thewayhome.user.member;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    UserMemberService userMemberService;

    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("[UserMemberController] createAccountForm()");

        String nextPage = "/member/user/create_account_form";

        return nextPage;

    }

    @GetMapping("/create_account_confirm")
    public String createAccountConfirm(UserMemberDto userMemberDto) {
        log.info("[UserMemberController] createAccountConfirm()");

        String nextPage = "redirect:/member/user/create_account_form";

        int result = userMemberService.createAccountConfirm(userMemberDto);
        if(result > userMemberService.INSERT_FAIL_AT_DATABASE) {
            nextPage = "/member/user/member_login_form";

        }

        return nextPage;

    }

}
