package com.btc.thewayhome.admin.pets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PetsAdminService implements IPetsAdminService{

    @Autowired
    IPetsAdminDaoMapper iPetsAdminDaoMapper;

    //보호소 리스트
    @Override
    public List<ShelterListInfoDto> searchShelterList() {
        log.info("searchShelterList()");

        return iPetsAdminDaoMapper.selectShelter();

    }

    //보호 동물 리스트(보호소 리스트 상세 페이지)
    @Override
    public List<PetsAdminDto> searchPetsList() {
        log.info("searchShelterList()");

        return iPetsAdminDaoMapper.selectPets();
    }

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    @Override
    public PetsAdminDto searchPetsListDetail(int an_no) {
        log.info("searchPetsListDetail()");
        PetsAdminDto petsAdminDto = null;
        log.info("petAdminDto -----> {}", petsAdminDto.getAn_no());

        //조회수
        int result = iPetsAdminDaoMapper.updatePetsListDetailHits(an_no);

//      PetsAdminDto petsAdminDto = null;

        if(result > 0) {
            petsAdminDto = iPetsAdminDaoMapper.selectPetsListDetail(an_no);

            if(petsAdminDto != null) {
                log.info("searchPetsListDetail SUCCESS!!");

                return petsAdminDto;

            } else {
                log.info("searchPetsListDetail FAIL!!");

                return null;

            }
        } else {
            return null;

        }

    }

}
