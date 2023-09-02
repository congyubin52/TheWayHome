package com.btc.thewayhome.user.board.review;

public interface IReviewBoardUserService {

    /*후기 게시판 작성 글 db에 입력*/
    int writeReviewConfirm(ReviewBoardUserDto reviewBoardUserDto);

    ReviewBoardUserDto reviewDetailPage(int bNo);
}
