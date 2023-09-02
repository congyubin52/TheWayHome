package com.btc.thewayhome.user.board.review;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/review_board")
    public String reviewBoardPage() {
        log.info("[ReviewBoardUserController] reviewBoardPage()");

        String nextPage = "/user/board/review_board";

        return nextPage;

    }

    /*
        후기 게시판 글 작성 페이지
     */
    @RequestMapping("/write_review_form")
    public String reviewBoardWritePage() {
        log.info("[ReviewBoardUserController] reviewBoardWritePage()");

        String nextPage = "/user/board/write_review_form";

        return nextPage;

    }

    @RequestMapping("/write_review_confirm")
    public String writeReviewConfirm(ReviewBoardUserDto reviewBoardUserDto) {
        log.info("[ReviewBoardUserController] writeReviewConfirm()");

        String nextPage = "/user/board/write_review_confirm";

        return nextPage;

    }

}
