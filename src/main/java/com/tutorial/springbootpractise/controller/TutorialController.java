package com.tutorial.springbootpractise.controller;

import com.tutorial.springbootpractise.entity.Tutorial;
import com.tutorial.springbootpractise.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    //get all tutorial by title
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam String title){
       List<Tutorial> tutorials = new ArrayList<>();
        if (title==null){
            tutorialRepository.findAll().forEach(tutorials::add);
        }else {
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }if (tutorials.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return  new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }

    //add tutorials to the database
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial){
        tutorialRepository.save(tutorial);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //delete tutorial by id
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        tutorialRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //delete all tutorial
    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get published tutorial
    @GetMapping("/tutorial/published")
    public ResponseEntity<List<Tutorial>> getByPublished(@RequestParam boolean published){
        List<Tutorial> tutorials = tutorialRepository.findByPublished(published);
        if (tutorials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {return  new ResponseEntity<>(tutorials,HttpStatus.OK);

        }
    }

}
