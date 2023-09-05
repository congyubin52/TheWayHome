package com.btc.thewayhome.user.board.comment;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentService implements ICommentService{

    @Autowired
    ICommentDaoMapper iCommentDaoMapper;


    @Override
    public List<CommentDto> writeCommentConfirm(Map<String, Object> msgMap, CommentDto commentDto) {

        commentDto.setU_m_id(msgMap.get("u_m_id").toString());
        commentDto.setB_type(msgMap.get("b_type").toString());
        commentDto.setB_no(Integer.parseInt(msgMap.get("b_no").toString()));
        commentDto.setB_c_content(msgMap.get("b_c_content").toString());

        int result = -1;
        result = iCommentDaoMapper.insertComment(commentDto);

        if(result > 0) {
            return iCommentDaoMapper.selectCommentAll();

        } else {
            return null;

        }

    }
}
