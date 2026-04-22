package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.ClassGeneric;
import com.bus.tracking.bus_tracking_system.service.ClassGenericService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/classes")
@CrossOrigin(origins = "https://pjsofttech.com")
public class ClassGenericController {

    private final ClassGenericService service;

    public ClassGenericController(ClassGenericService service) {
        this.service = service;
    }

    // POST
    @PostMapping("/createClasses")
    public ClassGeneric create(@RequestBody ClassGeneric classGeneric) {
        return service.save(classGeneric);
    }

    // GET ALL
    @GetMapping("/getAllClasses")
    public List<ClassGeneric> getAll() {
        return service.getAll();
    }



    // DELETE
    @DeleteMapping("deleteClass/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}