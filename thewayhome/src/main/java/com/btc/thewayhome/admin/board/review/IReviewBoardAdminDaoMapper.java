package com.btc.thewayhome.admin.board.review;

import com.btc.thewayhome.user.board.review.ReviewBoardUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IReviewBoardAdminDaoMapper {


    public List<ReviewBoardUserDto> selectAllReviewBoard(int skip, int amount);

    public int getTotalCnt();

    public ReviewBoardUserDto selectReviewDetail(ReviewBoardUserDto reviewBoardUserDto);

    public int deleteReviewBoard(int r_b_no);
}
