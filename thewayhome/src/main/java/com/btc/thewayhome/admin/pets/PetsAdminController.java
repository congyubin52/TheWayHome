package com.btc.thewayhome.admin.pets;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@RequestMapping("/user/pets")
@Controller
public class PetsAdminController {

    @Autowired
    PetsAdminService petsAdminService;

    /*
     *  사용자(USER)에게 보이는 페이지
     */
    //보호소 전체 리스트
    @GetMapping("/shelter_list")
    public String shelterList(Model model) {
        log.info("shelterList()");

        List<ShelterListInfoDto> shelterListInfoDtos = petsAdminService.searchShelterList();

        model.addAttribute("shelterListInfoDtos", shelterListInfoDtos);

        String nextPage = "admin/pets/shelter_list";

        return nextPage;
    }
    
    //보호 동물 전체 리스트(보호소 리스트 상세 페이지)
    @GetMapping("/pets_list")
    public String petsList(Model model) {
        log.info("petsList()");

        List<PetsAdminDto> petsAdminDtos = petsAdminService.searchPetsList();

        model.addAttribute("petsAdminDtos", petsAdminDtos);

        String nextPage = "admin/pets/pets_list";

        return nextPage;

    }

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    @GetMapping("/pets_list_detail")
    public String petsListDetail(Model model, PetsAdminDto petsAdminDto, HttpSession session) {
        log.info("petsListDetail()");

        String nextPage = "admin/pets/pets_list_detail";

        petsAdminDto = petsAdminService.searchPetsListDetail(petsAdminDto.getAn_no());

        session.setAttribute("petsAdminDto", petsAdminDto);

        model.addAttribute("petsAdminDto", petsAdminDto);

        return nextPage;

    }



}
