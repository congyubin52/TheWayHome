package com.btc.thewayhome.user.board.free;

import com.btc.thewayhome.user.board.config.UploadFileService;
import com.btc.thewayhome.user.member.UserMemberDto;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Log4j2
@RequestMapping("/user/board")
@Controller
public class FreeBoardUserController {


    @Autowired
    FreeBoardUserService freeBoardUserService;

    @Autowired
    UploadFileService uploadFileService;



    /*
      실종/목격 게시판 Home
   */
    @GetMapping({"", "/"})
    public String freeBoardHome() {
        log.info("freeBoardHome()");

//        return "/user/board/free/free_board_list";
        return "redirect:/user/board/free_board_list";
    }

    @GetMapping("/free_board_list")
    public String freeBoardList(Model model){

        String nextPage = "/user/board/free/free_board_list";

        Map<String, Object> map = freeBoardUserService.getAllFreeBoard();
        List<FreeBoardUserDto> freeBoardUserDtos = (List<FreeBoardUserDto>) map.get("freeBoardUserDtos");
        if(freeBoardUserDtos == null){
            log.info("freeBoardUserDtos IS NULL!!!");
        } else {
            log.info("freeBoardUserDtos SELECT SUCCESS!!!");
            model.addAttribute("freeBoardUserDtos", freeBoardUserDtos);
        }
        return nextPage;
    }

    /*
      실종/목격 게시판 글 작성 Form
   */
    @GetMapping({"free_board_form"})
    public String freeBoardWriteForm(HttpSession session) {
        log.info("freeBoardWriteForm()");

        String nextPage = "/user/board/free/free_board_form";

        UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");
        if(loginedUserDto == null){
            nextPage = "user/member/member_login_form";
        }

        return nextPage;
    }

    /*
      실종/목격 게시판 글 작성 Confirm
   */
    @PostMapping({"/free_board_write_confirm"})
    public String freeBoardWriteConfirm(HttpSession session, FreeBoardUserDto freeBoardUserDto, @RequestParam("file") MultipartFile file) {
        log.info("freeBoardWriteConfirm()");

        UserMemberDto loginedUserMemberDto = (UserMemberDto) session.getAttribute("loginedUserMemberDto");

        String nextPage = "redirect:/user/board/";

        // SAVE FILE
        String saveFileName = uploadFileService.upload(file);

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

    @GetMapping("/free_board_detail")
    public String freeBoardDetail(@RequestParam("fb_no") int fb_no, FreeBoardUserDto freeBoardUserDto, Model model) {
        log.info("freeBoardDetail()");

        String nextPage = "user/board/free/free_board_detail";

        Map<String, Object> map = freeBoardUserService.freeBoardDetail(fb_no, freeBoardUserDto);

        FreeBoardUserDto freeBoardDetailDto = (FreeBoardUserDto) map.get("freeBoardDetailDto");
        model.addAttribute("freeBoardDetailDto", freeBoardDetailDto);

        return nextPage;


    }





}
