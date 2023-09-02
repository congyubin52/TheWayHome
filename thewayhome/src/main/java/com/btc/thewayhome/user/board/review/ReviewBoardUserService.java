package com.btc.thewayhome.user.board.review;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ReviewBoardUserService implements IReviewBoardUserService{

    @Autowired
    IReviewBoardUserDaoMapper iReviewBoardUserDaoMapper;
    @Override
    public int writeReviewConfirm(ReviewBoardUserDto reviewBoardUserDto) {
        log.info("[ReviewBoardUserService] writeReviewConfirm()");

        return iReviewBoardUserDaoMapper.insertWriteReview(reviewBoardUserDto);
    }

    @Override
    public ReviewBoardUserDto reviewDetailPage(int bNo) {

        return iReviewBoardUserDaoMapper.selectReviewForBNo(bNo);
    }


}
