package com.btc.thewayhome.user.board.free;

import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IFreeBoardUserService {
    public int freeBoardWriteConfirm(String u_m_id, String fb_image, FreeBoardUserDto freeBoardUserDto);

    public Map<String, Object> getAllFreeBoard();
}
