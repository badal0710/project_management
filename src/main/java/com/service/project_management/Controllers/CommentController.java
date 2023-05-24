package com.service.project_management.Controllers;

import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.Project;
import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.CommentRepo;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.CommentsDto;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    CommentService commentService;
    @Autowired
    private TaskDetailRepo taskDetailRepo;
    @Autowired
    private CommentRepo commentRepo;


    @PostMapping("/create-comment")
    ResponseEntity<Object> createComment(@RequestBody CommentsDto commentsDto) {

        commentService.createComments(commentsDto);
        return ResponseEntity.ok().body("Comments Added Successfully");
    }

    @DeleteMapping("/delete-comment/{commentId}")
    ResponseEntity<Object> deleteComment(@PathVariable("commentId")Integer commentId){

        Comments comments = commentRepo.getById(commentId);

        if (comments==null) {
            throw new resourceNotFoundException("comment", "commentId", commentId);
        } else {
            commentRepo.deleteById(commentId);
            return ResponseEntity.ok().body("Comment Deleted Successfully!");
        }
    }

    @GetMapping("/get-task-comments/{taskId}")
    ResponseEntity<Object> getTaskComments(@PathVariable("taskId")Integer taskId){

         Comments comments=this.commentRepo.getById(taskId);
        if (comments==null) {
            throw new resourceNotFoundException("taskId", "taskId", taskId);
        } else {
            List<CommentsDto> commentsForTask=commentService.getCommentsOfTask(taskId);
            return ResponseEntity.ok().body(commentsForTask);
        }


    }



}