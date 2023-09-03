package com.btc.thewayhome.user.board.free;

import com.btc.thewayhome.user.board.free.util.UploadFileService;
import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RequestMapping("/user/board/")
@Controller
public class FreeBoardUserController {


    @Autowired
    FreeBoardUserService freeBoardUserService;

    @Autowired
    UploadFileService uploadFileService;



    /*
      실종/목격 게시판 Home
   */
    @GetMapping({"", "/", "free_board"})
    public String freeBoardHome() {
        log.info("freeBoardHome()");

        return "/user/board/free/free_board_list";
    }

    /*
      실종/목격 게시판 글 작성 Form
   */
    @GetMapping({"free_board_form"})
    public String freeBoardWriteForm() {
        log.info("freeBoardWriteForm()");

        String nextPage = "/user/board/free/free_board_form";

        return nextPage;
    }

    /*
      실종/목격 게시판 글 작성 Confirm
   */
    @PostMapping({"/free_board_write_confirm"})
    public String freeBoardWriteConfirm(HttpSession session, FreeBoardUserDto freeBoardUserDto, @RequestParam("fb_image") MultipartFile file) {
        log.info("freeBoardWriteConfirm()");

        UserMemberDto loginedUserMemberDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");

        String nextPage = "/user/board/free/free_board_list";

        // SAVE FILE
        String saveFileName = uploadFileService.upload(file);
        System.out.println("saveF " + saveFileName);

        int result = freeBoardUserService.freeBoardWriteConfirm(loginedUserMemberDto.getU_m_id(), saveFileName, freeBoardUserDto);
        if(result > 0){
            log.info("DB UPLOAD SUCCESS");
            return nextPage;
        } else {
            log.info("DB UPLOAD FAIL");
            nextPage = "/";
            return nextPage;
        }

    }



}
