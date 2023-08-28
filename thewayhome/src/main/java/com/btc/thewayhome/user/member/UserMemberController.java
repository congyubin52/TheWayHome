package com.btc.thewayhome.user.member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
        System.out.println("[UserMemberController] createAccountForm()");

        String nextPage = "/member/user/create_account_form";

        return nextPage;

    }
    @PostMapping("/user_delete_confirm")
    public String userMemeberDeleteConfirm(HttpSession session) {
        log.info("[UserMemberController] userMemeberDelete()");


        String nextPage = "redirect:/member/user/member_logout_confirm";

        UserMemberDto loginedUserMemberDto =
                (UserMemberDto) session.getAttribute("loginedUserMemberDto");
        int result = userMemberService.userMemeberDeleteConfirm(loginedUserMemberDto.getU_m_no());

        if (result <= 0)
            nextPage = "/member/user/member_modify_fail";


        return nextPage;

    }



}
