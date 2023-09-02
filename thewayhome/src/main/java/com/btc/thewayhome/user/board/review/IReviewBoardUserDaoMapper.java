package com.btc.thewayhome.user.board.review;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IReviewBoardUserDaoMapper {

    /*후기 게시판 작성 글 db에 입력*/
    public int insertWriteReview(ReviewBoardUserDto reviewBoardUserDto);

    ReviewBoardUserDto selectReviewForBNo(int bNo);
}
