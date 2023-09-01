package com.btc.thewayhome.user.board.free;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/user/free")
@Controller
public class FreeBoardUserController {

@GetMapping({"","/"})
    public String freeBoardHome() {
    log.info("[FreeBoardUserController] freeBoardHome()");

//    String nextPage = "/user/board/free/free_board_list";

    return "/user/board/free/free_board_list";




}

}
