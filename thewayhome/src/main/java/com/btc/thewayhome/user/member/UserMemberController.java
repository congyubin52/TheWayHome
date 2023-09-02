package com.btc.thewayhome.user.member;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/user/member")

public class UserMemberController {

    @Autowired
    UserMemberService userMemberService;

    /*
        사용자 회원가입 form
     */
    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("[UserMemberController] createAccountForm()");

        String nextPage = "/user/member/create_account_form";

        return nextPage;

    }

    /*
        사용자 회원가입 confirm
     */
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

        String nextPage = "user/member/member_login_form";

        return nextPage;

    }

    /*
        사용자 계정 수정 Form (비밀번호 제외)
     */
    @GetMapping ("/member_modify_form")
    public String userMemeberModfiyForm() {
        log.info("[UserMemberController] userMemeberModfiyForm()");

        String nextPage = "/user/member/member_modify_form";

        return nextPage;

    }


    /*
        사용자 계정 수정 Confirm (비밀번호 제외)
     */
    @PostMapping ("/member_modify_confirm")
    public String userMemeberModfiyConfirm(HttpSession session, UserMemberDto userMemberDto) {
        log.info("[UserMemberController] userMemeberModfiyConfirm()");

        String nextPage = "redirect:/user/member/member_modify_form";
//        String nextPage = "/user/member/member_modify_success";

        UserMemberDto updateUserDto = userMemberService.userMemeberModifyConfirm(userMemberDto);

        if(updateUserDto != null){
            session.setAttribute("loginedUserMemberDto", updateUserDto);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            nextPage = "redirect:/user/member/member_modify_form";
        }

        return nextPage;

    }

    /*
        사용자 비밀번호 수정 Form
     */
    @GetMapping ("/member_password_modify_form")
    public String userMemeberPasswordModfiyForm() {
        log.info("[UserMemberController] userMemeberPasswordModfiyForm()");

        String nextPage = "/user/member/member_password_modify_form";

        return nextPage;

    }

    /*
        사용자 비밀번호 수정 Confirm
     */
    @PostMapping ("/member_password_modify_confirm")
    public String userMemeberPasswordModfiyConfirm(HttpSession session,
                                                   HttpServletResponse response,
                                                   UserMemberDto userMemberDto,
                                                   @RequestParam("u_m_pw") String currentPw,
                                                   @RequestParam("u_m_Re_pw") String changePw) throws IOException {
        log.info("[UserMemberController] userMemeberPasswordModfiyConfirm()");

        String nextPage = "redirect:/";
//        String nextPage = "/user/member/member_password_modify_success";


        UserMemberDto updateUserDto = userMemberService.userMemeberPasswordModifyConfirm(userMemberDto, currentPw, changePw);

        if(updateUserDto != null){
            session.setAttribute("loginedUserMemberDto", updateUserDto);
            session.setMaxInactiveInterval(60 * 30);
        } else {
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 수정 실패 했습니다.');</script>");
            out.flush();

//            nextPage = "redirect:/user/member/member_modify_form";
        }
        return nextPage;
    }

    /*
        사용자 계정 삭제 Confirm
     */
    @GetMapping ("/member_delete_confirm")
    public String userMemberDeleteConfirm(HttpSession session, HttpServletResponse response) throws IOException {
        log.info("[UserMemberController] userMemberDeleteConfirm()");

        String nextPage = "redirect:/user/member/member_logout_confirm";

        UserMemberDto loginedUserMemberDto =
                (UserMemberDto) session.getAttribute("loginedUserMemberDto");
        int result = userMemberService.userMemeberDeleteConfirm(loginedUserMemberDto.getU_m_no());

        if (result <= 0){
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('계정 삭제 실패 했습니다.');</script>");
            out.flush();
        }


        return nextPage;

    }



}
