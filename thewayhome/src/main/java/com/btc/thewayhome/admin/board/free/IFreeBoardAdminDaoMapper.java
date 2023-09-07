package com.btc.thewayhome.admin.board.free;

import com.btc.thewayhome.user.board.free.FreeBoardUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IFreeBoardAdminDaoMapper {

    // 실종 목격 게시판 보기
    public List<FreeBoardUserDto> selectAllFreeBoard(int skip, int amount);

    // 실종 목격 게시판 - 페이지 네이션
    public int getTotalCnt();

    public FreeBoardUserDto selectContent(FreeBoardUserDto freeBoardUserDto);

    public int deleteFreeBoard(int fb_no);
}
