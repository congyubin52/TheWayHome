package com.btc.thewayhome.user.board.comment;

import com.btc.thewayhome.user.member.UserMemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/review_detail_json")
    @ResponseBody
    public List<CommentDto> reviewDetailPageJson(@RequestParam("r_b_no") int r_b_no , Model model) {
        log.info("reviewDetailPageJson()");

        List<CommentDto> commentDtos =  commentService.getCommentAll(r_b_no);
        model.addAttribute("commentDtos", commentDtos);

        return commentDtos;
    }

    @GetMapping("/review_comment_delete")
    @ResponseBody
    public List<CommentDto> reviewCommentDelete(@RequestParam("r_b_no") int r_b_no ,@RequestParam("r_c_no") int r_c_no ,Model model) {
        log.info("reviewDetailPageJson()");

        int result = commentService.reviewCommentDelete(r_c_no);

        List<CommentDto> commentDtos =  commentService.getCommentAll(r_b_no);
        model.addAttribute("commentDtos", commentDtos);

        return commentDtos;
    }


}
