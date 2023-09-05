package com.btc.thewayhome.user.board.review;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ReviewBoardUserService implements IReviewBoardUserService{

    @Autowired
    IReviewBoardUserDaoMapper iReviewBoardUserDaoMapper;

    @Override
    public int writeReviewConfirm(String u_m_id, String r_b_image, ReviewBoardUserDto reviewBoardUserDto) {
        log.info("writeReviewConfirm()");

        reviewBoardUserDto.setU_m_id(u_m_id);
        reviewBoardUserDto.setR_b_image(r_b_image);

        return iReviewBoardUserDaoMapper.insertWriteReview(reviewBoardUserDto);

    }

    @Override
    public ReviewBoardUserDto reviewDetailPage(int r_b_no) {
        log.info("reviewDetailPage()");

        int result = iReviewBoardUserDaoMapper.updateHits(r_b_no);

        if(result > 0) {
            log.info("hits update success");
            return iReviewBoardUserDaoMapper.selectReviewForBNo(r_b_no);

        }else {
            log.info("hits update fail");
            return null;

        }

    }

    @Override
    public Map<String, Object> reviewBoardList() {
        log.info("reviewBoardList()");

        Map<String, Object> map = new HashMap<>();
        List<ReviewBoardUserDto> reviewBoardDtos = iReviewBoardUserDaoMapper.selectReviewAll();

        if(reviewBoardDtos == null) {
            log.info("NULL");
            return null;

        } else {
            log.info("NOT NULL");
            map.put("reviewBoardDtos", reviewBoardDtos);
            return map;

        }

    }

    @Override
    public int reviewDeleteConfirm(int rBNo) {
        log.info("reviewDeleteConfirm()");
        return iReviewBoardUserDaoMapper.reviewUseNForBNo(rBNo);

    }


}
