package com.gifprojects;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineerDTO softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @GetMapping
    public List<SoftwareEngineerDTO> getEngineers(){
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineerDTO getEngineerById(@PathVariable Integer id){
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteSoftwareEngineerById(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineerById(id);
    }

    @PutMapping("update/{id}")
    public void updateSoftwareEngineerById(@PathVariable Integer id, @RequestBody SoftwareEngineerDTO softwareEngineer){
        softwareEngineerService.updateSoftwareEngineerById(id, softwareEngineer);
    }

}
