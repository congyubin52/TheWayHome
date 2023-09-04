package com.btc.thewayhome.user.board.free;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IFreeBoardUserDaoMapper {
    public int insertFreeBoardContent(FreeBoardUserDto freeBoardUserDto);

    public List<FreeBoardUserDto> selectAllFreeBoard();

    public int updateHits(int fb_no);

    public FreeBoardUserDto selectContent(FreeBoardUserDto freeBoardUserDto);
}
