package com.btc.thewayhome.admin.board.review;

import com.btc.thewayhome.page.Criteria;
import com.btc.thewayhome.page.PageMakerDto;
import com.btc.thewayhome.user.board.free.FreeBoardUserDto;
import com.btc.thewayhome.user.board.review.ReviewBoardUserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ReviewBoardAdminService implements IReviewBoardAdminService{

    @Autowired
    IReviewBoardAdminDaoMapper iReviewBoardAdminDaoMapper;

    @Override
    public Map<String, Object> superReviewBoardList(int pageNum, int amount) {
        log.info("superReviewBoardList");
        Map<String, Object> map = new HashMap<>();

        //페이지 네이션
        Criteria criteria = new Criteria(pageNum, amount);
        List<ReviewBoardUserDto> reviewBoardDtos = iReviewBoardAdminDaoMapper.selectAllReviewBoard(criteria.getSkip(), criteria.getAmount());
        int totalCnt = iReviewBoardAdminDaoMapper.getTotalCnt();
        PageMakerDto pageMakerDto = new PageMakerDto(criteria, totalCnt);

        if(reviewBoardDtos != null){
            log.info("NOT NULL");
            map.put("reviewBoardDtos", reviewBoardDtos);
            map.put("pageMakerDto", pageMakerDto);
            return map;

        } else {
            log.info("NULL");
            return null;

        }
    }

    @Override
    public Map<String, Object> superReviewBoardDetail(int r_b_no, ReviewBoardUserDto reviewBoardUserDto) {
        log.info("superReviewBoardDetail()");

        Map<String, Object> map = new HashMap<>();

        reviewBoardUserDto.setR_b_no(r_b_no);

        ReviewBoardUserDto selectReviewDto = iReviewBoardAdminDaoMapper.selectReviewDetail(reviewBoardUserDto);
        map.put("selectReviewDto", selectReviewDto);

        return map;
    }

    public int superReviewBoardDelete(int r_b_no) {
        log.info("superReviewBoardDelete()");

        return iReviewBoardAdminDaoMapper.deleteReviewBoard(r_b_no);

    }
}
