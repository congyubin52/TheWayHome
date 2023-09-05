package com.btc.thewayhome.user.board.review;

import com.btc.thewayhome.user.board.config.UploadFileService;
import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/user/board")
public class ReviewBoardUserController {

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    ReviewBoardUserService reviewBoardUserService;

    // 후기 게시판 페이지 이동
    @GetMapping("/review_board")
    public String reviewBoardPage(Model model) {
        log.info("reviewBoardPage()");

        String nextPage = "/user/board/review/review_board";

        //서비스에서 Map으로 넘겨주기 때문에 Map 타입으로 받음
        Map<String, Object> map = reviewBoardUserService.reviewBoardList();
        List<ReviewBoardUserDto> reviewBoardDtos = (List<ReviewBoardUserDto>) map.get("reviewBoardDtos");

        if(reviewBoardDtos == null) {
            log.info("reviewBoardDtos IS NULL!!!");

        } else {
            log.info("reviewBoardDtos SELECT SUCCESS!!!");
            model.addAttribute("reviewBoardDtos", reviewBoardDtos);

        }
        return nextPage;

    }

    // 후기 게시판 글 작성 Form
    @GetMapping("/write_review_form")
    public String reviewBoardWritePage(HttpSession session) {
        log.info("reviewBoardWritePage()");

        String nextPage = "/user/board/review/write_review_form";

        if(session.getAttribute("loginedUserMemberDto") == null) {
            nextPage = "/user/member/member_login_form";
        }

        return nextPage;

    }

    // 후기 게시판 작성 글 db에 입력
    @PostMapping("/write_review_confirm")
    public String writeReviewConfirm(HttpSession session, ReviewBoardUserDto reviewBoardUserDto, @RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("writeReviewConfirm()");

        UserMemberDto loginedUserMemberDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");

        String nextPage = "redirect:/user/board/review_board";

        String saveFileName = "noImage";

        //SAVE FILE
        if(!file.isEmpty()) {
            saveFileName = uploadFileService.upload(file);
        }

        int result = reviewBoardUserService.writeReviewConfirm(loginedUserMemberDto.getU_m_id(), saveFileName, reviewBoardUserDto);

        if(result <= 0) {
            log.info("writeReviewConfirm FAIL");
            nextPage = "redirect:/user/board/write_review_form";

        }
        return nextPage;

    }

    // 후기 게시판 상세보기
    @GetMapping("/review_detail")
    public String reviewDetailPage(@RequestParam("r_b_no") int r_b_no, Model model) {
        log.info("reviewDetailPage()");

        String nextPage = "/user/board/review/review_detail";

        ReviewBoardUserDto selectReviewDto = reviewBoardUserService.reviewDetailPage(r_b_no);

        model.addAttribute("selectReviewDto", selectReviewDto);

        return nextPage;
    }

    // 후기 게시판 수정 Form
    @GetMapping("/review_modify_form")
    public String reviewModifyPage(int r_b_no, Model model) {
        log.info("reviewModifyPage()");

        String nextPage = "/user/board/review/review_modify_form";

        ReviewBoardUserDto selectReviewDto = reviewBoardUserService.reviewDetailPage(r_b_no);

        model.addAttribute("selectReviewDto", selectReviewDto);

        return nextPage;
    }

    // 후기 게시판 수정 Confirm


    // 후기 게시판 삭제
    @GetMapping("/review_delete_confirm")
    public String reviewDeleteConfirm(@RequestParam("r_b_no") int r_b_no, HttpServletResponse response) throws IOException {
        log.info("reviewDeleteConfirm()");

        String nextPage = "redirect:/user/board/review_board";

        int result = reviewBoardUserService.reviewDeleteConfirm(r_b_no);

        if (result <= 0) {
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('게시글 삭제에 실패했습니다.');");
            out.println("history.back();");
            out.println("</script>");
            out.flush();

            nextPage = "";

        }
        return nextPage;

    }

}
