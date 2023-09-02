package com.btc.thewayhome.user.board.free;

import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RequestMapping("/user/board/")
@Controller
public class FreeBoardUserController {


    @Autowired
    FreeBoardUserService freeBoardUserService;



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

        return "/user/board/free/free_board_form";
    }

    /*
      실종/목격 게시판 글 작성 Confirm
   */
    @PostMapping({"/free_board_write_confirm"})
    @ResponseBody
    public Map<String, Object> freeBoardWriteConfirm(HttpSession session, FreeBoardUserDto freeBoardUserDto, MultipartRequest request) {
        log.info("freeBoardWriteConfirm()");

        UserMemberDto loginedUserMemberDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");

        log.info(freeBoardUserDto.getB_title());
        log.info(loginedUserMemberDto.getU_m_id());
        log.info(request);

//        try {
//
//            int result = freeBoardUserService.freeBoardWriteConfirm(loginedUserMemberDto.getU_m_id() , freeBoardUserDto, request);
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }



}
