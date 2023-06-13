package com.service.project_management.Controllers;

import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.ProjectComments;
import com.service.project_management.Repositories.CommentRepo;
import com.service.project_management.Repositories.ProjectCommentsRepo;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.ProjectComments.ProjectCommentDto;
import com.service.project_management.dto.ProjectComments.ProjectCommentDtoGet;
import com.service.project_management.dto.TaskComments.CommentsDto;
import com.service.project_management.dto.TaskComments.CommentsDtoGet;
import com.service.project_management.exceptions.resourceNotFoundException;
import com.service.project_management.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apii/comments")
public class CommentController {


    @Autowired
    CommentService commentService;
    @Autowired
    private TaskDetailRepo taskDetailRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ProjectCommentsRepo projectCommentsRepo;


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
            List<CommentsDtoGet> commentsForTask=commentService.getCommentsOfTask(taskId);
            return ResponseEntity.ok().body(commentsForTask);
        }


    }




    @PostMapping("/create-project-comments")
    ResponseEntity<Object> createProjectComments(@RequestBody ProjectCommentDto projectCommentDto) {

        commentService.createProjectComments(projectCommentDto);
        return ResponseEntity.ok().body("Comments Added Successfully");
    }

    @DeleteMapping("/delete-project-comments/{commentId}")
    ResponseEntity<Object> deleteProjectCommment(@PathVariable("commentId")Integer commentId){

        ProjectComments projectComment = projectCommentsRepo.getById(commentId);

        if (projectComment==null) {
            throw new resourceNotFoundException("comment", "commentId", commentId);
        } else {
            projectCommentsRepo.deleteById(commentId);
            return ResponseEntity.ok().body("Comment Deleted Successfully!");
        }
    }

    @GetMapping("/get-project-comment/{projectId}")
    ResponseEntity<Object> getProjectComments(@PathVariable("projectId")Integer projectId){

        ProjectComments projectComments=this.projectCommentsRepo.getById(projectId);
        if (projectComments==null) {
            throw new resourceNotFoundException("projectId", "projectId", projectId);
        } else {
            List<ProjectCommentDtoGet> commentsForTask=commentService.getProjectComment(projectId);
            return ResponseEntity.ok().body(commentsForTask);
        }


    }




}