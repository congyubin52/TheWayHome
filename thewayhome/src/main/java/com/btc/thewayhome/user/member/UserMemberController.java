package com.btc.thewayhome.user.member;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/user/member")
public class UserMemberController {

    @Autowired
    UserMemberService userMemberService;

    /*
        사용자 회원가입
     */
    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("[UserMemberController] createAccountForm()");

        String nextPage = "/user/member/create_account_form";

        return nextPage;

    }

    @PostMapping("/create_account_confirm")
    public String createAccountConfirm(UserMemberDto userMemberDto, Model model) {
        log.info("[UserMemberController] createAccountConfirm()");

        String nextPage = "redirect:/user/member/create_account_form";

        int result = userMemberService.createAccountConfirm(userMemberDto);
        if(result > userMemberService.INSERT_FAIL_AT_DATABASE) {

            model.addAttribute("u_m_id", userMemberDto.getU_m_id());
            nextPage = "/user/member/create_account_success";

        }

        return nextPage;

    }

    /*
        사용자 로그인
     */
    @GetMapping("/member_login_form")
    public String memberLoginForm() {
        log.info("[UserMemberController] memberLoginForm()");

        String nextPage = "/user/member/member_login_form";

        return nextPage;

    }

    @PostMapping("/member_login_confirm")
    @ResponseBody
    public Object  memberLoginConfirm(@RequestBody Map<String, String> msgMap, HttpSession session, Model model) {
        log.info("[UserMemberController] memberLoginConfirm()");

        Map<String, Object> map = userMemberService.memberLoginConfirm(msgMap);

        UserMemberDto loginedUserMemberDto = null;
        if (map != null) {
            loginedUserMemberDto = (UserMemberDto) map.get("userMemberDto");
            session.setAttribute("loginedUserMemberDto", loginedUserMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            return map;

        }

        model.addAttribute("loginedUserMemberDto", loginedUserMemberDto);

        return null;

    }

    @PostMapping("/user_delete_confirm")
    public String userMemeberDeleteConfirm(HttpSession session) {
        log.info("[UserMemberController] userMemeberDelete()");


        String nextPage = "redirect:/user/member/member_logout_confirm";

        UserMemberDto loginedUserMemberDto =
                (UserMemberDto) session.getAttribute("loginedUserMemberDto");
        int result = userMemberService.userMemeberDeleteConfirm(loginedUserMemberDto.getU_m_no());

        if (result <= 0)
            nextPage = "/member/user/member_modify_fail";


        return nextPage;

    }



}
