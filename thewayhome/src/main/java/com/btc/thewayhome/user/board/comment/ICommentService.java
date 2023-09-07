package com.btc.thewayhome.user.board.comment;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

public interface ICommentService {

    List<CommentDto> writeCommentConfirm(Map<String, Object> msgMap, CommentDto commentDto);

    List<CommentDto> getCommentAll(int rBNo);

    int reviewCommentDelete(int r_c_no);
}
