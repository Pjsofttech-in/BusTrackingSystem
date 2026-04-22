package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.DivisionEntity;
import com.bus.tracking.bus_tracking_system.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @PostMapping("/addDivision")
    public DivisionEntity addDivision(@RequestBody DivisionEntity division){
        return divisionService.saveDivision(division);
    }

    @GetMapping("/getAllDivision")
    public List<DivisionEntity> getAllDivision(){
        return divisionService.getAllDivisions();
    }

    @GetMapping("/GetDivision/{id}")
    public DivisionEntity getDivision(@PathVariable Long id){
        return divisionService.getDivisionById(id);
    }

    @DeleteMapping("/deleteDivision/{id}")
    public String deleteDivision(@PathVariable Long id){
        divisionService.deleteDivision(id);
        return "Division Deleted Successfully";
    }
}