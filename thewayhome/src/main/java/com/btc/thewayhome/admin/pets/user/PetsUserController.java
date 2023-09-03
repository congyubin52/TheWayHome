package com.btc.thewayhome.admin.pets.user;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequestMapping("/user/pets")
@Controller
public class PetsUserController {

    @Autowired
    PetsUserService petsUserService;


    @RequestMapping("/pets_form")
    public String petsForm(){
        log.info("petsForm()");

        String nextPage = "/admin/pets/pets_form";

        return nextPage;
    }


    /*
     *  사용자(USER)에게 보이는 페이지
     */
    //보호소 전체 리스트
    @GetMapping("/shelter_list")
    public String shelterList(Model model) {
        log.info("shelterList()");

        List<UserShelterListInfoDto> userShelterListInfoDtos = petsUserService.searchShelterList();

        model.addAttribute("userShelterListInfoDtos", userShelterListInfoDtos);

        String nextPage = "admin/pets/user/user_shelter_list";

        return nextPage;
    }

    //보호 동물 전체 리스트(보호소 리스트 상세 페이지)
    @GetMapping("/pets_list")
    public String petsList(Model model, PetsUserDto petsUserDto, @RequestParam("s_no") String s_no) {
        log.info("petsList()");

        List<PetsUserDto> petsUserDtos = petsUserService.searchPetsList(s_no);


        log.info("s_no------->{}", s_no);

        model.addAttribute("petsUserDtos", petsUserDtos);

        String nextPage = "admin/pets/user/user_pets_list";

        return nextPage;

    }

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    @GetMapping("/pets_list_detail")
    public String petsListDetail(Model model, PetsUserDto petsUserDto, HttpSession session, @RequestParam("an_no") int an_no) {
        log.info("petsListDetail()");
        log.info("petsUserDto.getAn_no={}", petsUserDto.getAn_no());
        log.info("petsUserDto.getAn_no={}", an_no);

        String nextPage = "admin/pets/user/user_pets_list_detail";

        petsUserDto = petsUserService.searchPetsListDetail(an_no);

        log.info("selectPetsAdminDto---->", petsUserDto);
        log.info(petsUserDto.getAn_no());
        session.setAttribute("petsUserDto", petsUserDto);
        model.addAttribute("petsUserDto", petsUserDto);

        return nextPage;

    }

}
