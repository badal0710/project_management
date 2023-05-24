package com.service.project_management.Controllers;

import com.service.project_management.dto.CommentsDto;
import com.service.project_management.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {


@Autowired
CommentService commentService;


@PostMapping("/create-comment")
ResponseEntity<Object> createComment(@RequestBody CommentsDto commentsDto){

commentService.createComments(commentsDto);
return ResponseEntity.ok().body("Comments Added Successfully");
}



}