package com.btc.thewayhome.user.board.review;

import java.util.Map;

public interface IReviewBoardUserService {

    /*후기 게시판 작성 글 db에 입력*/
    int writeReviewConfirm(ReviewBoardUserDto reviewBoardUserDto);

    ReviewBoardUserDto reviewDetailPage(int r_b_no);

    Map<String, Object> reviewBoardList();
}
