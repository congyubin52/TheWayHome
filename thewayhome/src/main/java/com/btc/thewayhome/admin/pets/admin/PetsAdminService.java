package com.btc.thewayhome.admin.pets.admin;

import com.btc.thewayhome.admin.pets.user.PetsUserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PetsAdminService implements IPetsAdminService{

    @Autowired
    IPetsAdminDaoMapper iPetsAdminDaoMapper;

    //보호소 전체 리스트
    @Override
    public List<AdminShelterListInfoDto> searchShelterList() {
        log.info("searchShelterList()");

        return iPetsAdminDaoMapper.selectShelter();

    }

    //보호 동물 전체 리스트(보호소 리스트 상세 페이지)
    @Override
    public List<PetsAdminDto> searchPetsList(String s_no) {
        log.info("searchShelterList()");

        List<PetsAdminDto> petsAdminDtos = iPetsAdminDaoMapper.selectPets(s_no);

        return petsAdminDtos;

    }

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    @Override
    public PetsAdminDto searchPetsListDetail(int an_no) {
        log.info("searchPetsListDetail()");

        //조회수
        int result = iPetsAdminDaoMapper.updatePetsListDetailHits(an_no);

        if(result > 0) {
            PetsAdminDto petsAdminDto = iPetsAdminDaoMapper.selectPetsListDetail(an_no);

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
