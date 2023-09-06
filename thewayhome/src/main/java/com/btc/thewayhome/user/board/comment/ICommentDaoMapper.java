package com.btc.thewayhome.user.board.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICommentDaoMapper {
    public int insertComment(CommentDto commentDto);

    public List<CommentDto> selectCommentAll();
}
