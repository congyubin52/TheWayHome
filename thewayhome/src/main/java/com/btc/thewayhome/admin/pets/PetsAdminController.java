package com.btc.thewayhome.admin.pets;

import com.btc.thewayhome.admin.member.GetPetsData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/admin/pets")
public class PetsAdminController {

    @Autowired
    PetsAdminService petsAdminService;


    @RequestMapping("/pets_form")
    public String petsForm(){
        log.info("petsForm()");

        String nextPage = "/admin/pets/pets_form";

        return nextPage;
    }

}
