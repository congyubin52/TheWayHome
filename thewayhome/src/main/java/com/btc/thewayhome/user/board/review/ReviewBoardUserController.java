package com.btc.thewayhome.user.board.review;

import com.btc.thewayhome.user.member.UserMemberDto;
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
@RequestMapping("/user/board")
public class ReviewBoardUserController {

    @Autowired
    ReviewBoardUserService reviewBoardUserService;

    /*
        후기 게시판 페이지
     */
    @GetMapping("/review_board")
    public String reviewBoardPage() {
        log.info("[ReviewBoardUserController] reviewBoardPage()");

        String nextPage = "/user/board/review_board";

        return nextPage;
    }

    /*
        후기 게시판 글 작성 페이지
     */
    @GetMapping("/write_review_form")
    public String reviewBoardWritePage(HttpSession session) {
        log.info("[ReviewBoardUserController] reviewBoardWritePage()");

        String nextPage = "/user/board/write_review_form";

        if(session.getAttribute("loginedUserMemberDto") == null) {
            nextPage = "/user/member/member_login_form";
        }

        return nextPage;

    }

    /*
        후기 게시판 작성 글 db에 입력
     */
    @PostMapping("/write_review_confirm")
    public String writeReviewConfirm(HttpSession session, ReviewBoardUserDto reviewBoardUserDto) {
        log.info("[ReviewBoardUserController] writeReviewConfirm()");

        String nextPage = "/user/board/review_board";

        UserMemberDto loginedUserMemberDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");

        reviewBoardUserDto.setU_m_id(loginedUserMemberDto.getU_m_id());

        int result = reviewBoardUserService.writeReviewConfirm(reviewBoardUserDto);

        if(result <= 0) {
            nextPage = "redirect:/user/board/write_review_form";

        }

        return nextPage;

    }

    @GetMapping("/review_detail")
    public String reviewDetailPage(int b_no, Model model) {
        log.info("[ReviewBoardUserController] reviewDetailPage()");

        String nextPage = "/user/board/review_detail";

        ReviewBoardUserDto selectReviewDto = reviewBoardUserService.reviewDetailPage(b_no);

        model.addAttribute("selectReviewDto", selectReviewDto);

        return nextPage;
    }

    @GetMapping("/review_modify_form")
    public String reviewModifyPage(int b_no, Model model) {
        log.info("[ReviewBoardUserController] reviewModifyPage()");

        String nextPage = "/user/board/review_modify_form";

        ReviewBoardUserDto selectReviewDto = reviewBoardUserService.reviewDetailPage(b_no);

        model.addAttribute("selectReviewDto", selectReviewDto);

        return nextPage;
    }

}
