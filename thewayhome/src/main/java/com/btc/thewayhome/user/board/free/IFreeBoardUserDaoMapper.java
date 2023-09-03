package com.btc.thewayhome.user.board.free;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IFreeBoardUserDaoMapper {
    public int insertFreeBoardContent(FreeBoardUserDto freeBoardUserDto);
}
