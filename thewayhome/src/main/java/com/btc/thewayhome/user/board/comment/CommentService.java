package com.btc.thewayhome.user.board.comment;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class CommentService implements ICommentService{

    @Autowired
    ICommentDaoMapper iCommentDaoMapper;


    @Override
    public List<CommentDto> writeCommentConfirm(Map<String, Object> msgMap, CommentDto commentDto) {
        log.info("writeCommentConfirm()");

        commentDto.setU_m_id(msgMap.get("u_m_id").toString());
        commentDto.setB_type(msgMap.get("b_type").toString());
        commentDto.setB_no(Integer.parseInt(msgMap.get("b_no").toString()));
        commentDto.setB_c_content(msgMap.get("b_c_content").toString());

        int result = -1;
        result = iCommentDaoMapper.insertComment(commentDto);

        if(result > 0) {
            return iCommentDaoMapper.selectCommentAll(commentDto.getB_no());

        } else {
            return null;

        }

    }

    @Override
    public List<CommentDto> getCommentAll(int rBNo) {
        log.info("getCommentAll()");
        return iCommentDaoMapper.selectCommentAll(rBNo);
    }

    @Override
    public int reviewCommentDelete(int b_c_no) {
        log.info("reviewCommentDelete()");
        return iCommentDaoMapper.deleteCommentCNo(b_c_no);
    }

}
