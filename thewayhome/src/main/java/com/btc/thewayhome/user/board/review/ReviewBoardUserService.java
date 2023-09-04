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
    public int writeReviewConfirm(ReviewBoardUserDto reviewBoardUserDto) {
        log.info("[ReviewBoardUserService] writeReviewConfirm()");

        return iReviewBoardUserDaoMapper.insertWriteReview(reviewBoardUserDto);
    }

    @Override
    public ReviewBoardUserDto reviewDetailPage(int r_b_no) {
        log.info("[ReviewBoardUserService] reviewDetailPage()");

        int result = iReviewBoardUserDaoMapper.updateHits(r_b_no);

        if(result > 0) {
            log.info("[ReviewBoardUserService] 조회수 업데이트 성공");
            return iReviewBoardUserDaoMapper.selectReviewForBNo(r_b_no);

        }else {
            log.info("[ReviewBoardUserService] 조회수 업데이트 실패");
            return null;

        }

    }

    @Override
    public Map<String, Object> reviewBoardList() {
        log.info("[ReviewBoardUserService] reviewBoardList()");

        Map<String, Object> map = new HashMap<>();

        List<ReviewBoardUserDto> reviewBoardDtos = iReviewBoardUserDaoMapper.selectReviewAll();

        if(reviewBoardDtos == null) {
            log.info("reviewBoardDtos == null");
        } else {
            log.info("널 아니따아ㅣㅏ어ㅏㅣㄹ머ㅏㄷ");
        }

        map.put("reviewBoardDtos", reviewBoardDtos);

        return map;
    }

    @Override
    public int reviewDeleteConfirm(int rBNo) {
        log.info("[ReviewBoardUserService] reviewDeleteConfirm()");

        return iReviewBoardUserDaoMapper.reviewUseNForBNo(rBNo);
    }


}
