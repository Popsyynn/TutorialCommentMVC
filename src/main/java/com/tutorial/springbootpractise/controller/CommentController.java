package com.tutorial.springbootpractise.controller;

import com.tutorial.springbootpractise.entity.Comment;
import com.tutorial.springbootpractise.repository.CommentRepository;
import com.tutorial.springbootpractise.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;


    //save comment by tutorial id
    @PostMapping("/tutorials/{id}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable Long id, @RequestBody Comment comment) throws Exception {
        Comment comment1 = tutorialRepository.findById(id).map(tutorial -> {
            comment.setTutorial(tutorial);
        return commentRepository.save(comment);
        }).orElseThrow(()-> new Exception("tutorial id not found"));

        return new ResponseEntity<>(comment1 , HttpStatus.OK);
    }
    //get comment by tutorial id
    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getComment(@PathVariable Long tutorialId) throws Exception {
        if (!tutorialRepository.existsById(tutorialId)){
            throw new Exception("id not found");
        }
           List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return new ResponseEntity<>(comments ,HttpStatus.OK);
    }

    //delete comment based on tutorial id
    @DeleteMapping("/tutorials/{id}/comments")
    public ResponseEntity<Comment> deleteAllCommentForTutorial(@PathVariable Long id) throws Exception {
        if (!tutorialRepository.existsById(id)){
            throw new Exception("id not found");
        }
        commentRepository.deleteByTutorialId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //get comment by comment id
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new Exception("no cmment with id"));

        return new ResponseEntity<>(comment , HttpStatus.OK);
    }

    //delete comment by comment Id
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteCommentById(@PathVariable Long id){
        commentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
