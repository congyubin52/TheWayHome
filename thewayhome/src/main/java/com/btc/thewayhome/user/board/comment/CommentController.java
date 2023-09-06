package com.btc.thewayhome.user.board.comment;

import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequestMapping("/user/comment")
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    // 댓글 작성 Form
    @PostMapping ("/write_comment_confirm")
    @ResponseBody
    public Object writeCommentConfirm(@RequestBody Map<String, Object> msgMap, CommentDto commentDto) {
        log.info("writeCommentConfirm()");

        List<CommentDto> commentDtos = commentService.writeCommentConfirm(msgMap, commentDto);
        Map<String, Object> map = new HashMap<>();

        map.put("commentDtos", commentDtos);

        return map;

    }
//    // 댓글 작성 Form
//    @PostMapping ("/write_comment_confirm")
//    @ResponseBody
//    public Object writeCommentConfirm(@RequestBody Map<String, Object> msgMap, CommentDto commentDto) {
//        log.info("writeCommentConfirm()");
//
//        List<CommentDto> commentDtos = commentService.writeCommentConfirm(msgMap, commentDto);
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("commentDtos", commentDtos);
//
//        return map;
//
//    }


}
