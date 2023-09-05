package com.btc.thewayhome.user.board.review;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IReviewBoardUserDaoMapper {

    // 후기 게시판 - 작성
    public int insertWriteReview(ReviewBoardUserDto reviewBoardUserDto);

    // 후기 게시판 - 상세보기
    public ReviewBoardUserDto selectReviewForBNo(int r_b_no);

    // 후기 게시판 - 게시글 전체 리스트
    public List<ReviewBoardUserDto> selectReviewAll();

    // 후기 게시판 - 조회수
    public int updateHits(int rBNo);

    // 후기 게시판 - 삭제
    public int reviewUseNForBNo(int rBNo);
}
