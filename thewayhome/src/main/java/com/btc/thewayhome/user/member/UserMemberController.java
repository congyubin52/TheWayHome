package com.btc.thewayhome.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    UserMemberService userMemberService;

    @GetMapping("/create_account_form")
    public String createAccountForm() {
        System.out.println("[UserMemberController] createAccountForm()");

        String nextPage = "/member/user/create_account_form";

        return nextPage;

    }

}
