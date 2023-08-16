package com.moko.monlaboratoireapi.controllers;

import com.moko.monlaboratoireapi.models.LabUser;
import com.moko.monlaboratoireapi.services.LabUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class LabUserController {

    private final LabUserService labUserService;

    @Autowired
    public LabUserController(LabUserService labUserService) {
        this.labUserService = labUserService;
    }

    @GetMapping
    public List<LabUser> getStudents() {
        return this.labUserService.getUsers();
    }

    @PostMapping
    public void addNewUser(@RequestBody LabUser lab_user) {
        this.labUserService.addNewUser(lab_user);
    }

    @DeleteMapping(path = "{lab_user_id}")
    public void deleteLabUser(@PathVariable("lab_user_id") UUID lab_user_id) {
        this.labUserService.deleteLabUser(lab_user_id);
    }

    @PutMapping(path = "{lab_user_id}")
    public void updateLaUser(
            @PathVariable("lab_user_id") UUID lab_user_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        labUserService.updateLabUser(lab_user_id, name, email);
    }
}
