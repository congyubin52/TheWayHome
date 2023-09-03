package com.btc.thewayhome.user.board.review;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IReviewBoardUserDaoMapper {

    /*후기 게시판 작성 글 db에 입력*/
    public int insertWriteReview(ReviewBoardUserDto reviewBoardUserDto);

    public ReviewBoardUserDto selectReviewForBNo(int r_b_no);

    public List<ReviewBoardUserDto> selectReviewAll();

    public int updateHits(int rBNo);
}
