package com.example.demo.controller;


import com.example.demo.model.Model;
import com.example.demo.repo.MyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

    @Autowired
    MyRepo myRepo;

    @PostMapping("/insert")
    public Model insertModel(@RequestBody Model  model)
    {
        myRepo.save(model);
        return model;
    }

    @GetMapping("/getAll")
    public List<Model> getAllModel(){
        return myRepo.findAll();
    }

    @PutMapping("/update/{id}")
    public Model updateModel(@PathVariable String id, @RequestBody Model model){
        Optional<Model> byId = myRepo.findById(id);
        if (byId.isPresent()){
            return myRepo.save(model);
        }else {
            throw new RuntimeException("id not found");
        }

    }

    @DeleteMapping("/delete")
    public String deleteAllModel(){
        myRepo.deleteAll();
        return "deleted successfully";
    }

}
