package com.btc.thewayhome.user.board.free;

import com.btc.thewayhome.user.board.free.image.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@Service
public class FreeBoardUserService implements IFreeBoardUserService {

    @Autowired
    IFreeBoardUserDaoMapper iFreeBoardUserDaoMapper;

    @Autowired
    ImageService imageService;

    public int freeBoardWriteConfirm(String u_m_id, String b_image, FreeBoardUserDto freeBoardUserDto) {
        log.info("freeBoardWriteConfirm()");
        freeBoardUserDto.setU_m_id(u_m_id);
        freeBoardUserDto.setB_image(b_image);

        return iFreeBoardUserDaoMapper.insertFreeBoardContent(freeBoardUserDto);
    }

}
