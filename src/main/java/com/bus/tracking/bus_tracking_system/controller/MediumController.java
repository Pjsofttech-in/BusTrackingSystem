package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.MediumEntity;
import com.bus.tracking.bus_tracking_system.service.MediumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
public class MediumController {

    @Autowired
    private MediumService mediumService;

    @PostMapping("/addMedium")
    public MediumEntity addMedium(@RequestBody MediumEntity medium){
        return mediumService.saveMedium(medium);
    }

    @GetMapping("/getAllMedium")
    public List<MediumEntity> getAllMedium(){
        return mediumService.getAllMedium();
    }

    @GetMapping("/getMedium/{id}")
    public MediumEntity getMedium(@PathVariable Long id){
        return mediumService.getMediumById(id);
    }

    @PutMapping("/updateMedium/{id}")
    public MediumEntity updateMedium(@PathVariable Long id,
                                     @RequestBody MediumEntity medium) {
        return mediumService.updateMedium(id, medium);
    }

    @DeleteMapping("/deleteMedium/{id}")
    public String deleteMedium(@PathVariable Long id){
        mediumService.deleteMedium(id);
        return "Medium Deleted Successfully";
    }
}