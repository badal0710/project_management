package com.service.project_management.service;


import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.Contractor;
import com.service.project_management.Entities.Project;
import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.CommentRepo;
import com.service.project_management.Repositories.ContractorRepo;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.CommentsDto;
import com.service.project_management.dto.ProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TaskDetailRepo taskDetailRepo;
    @Autowired
    private ContractorRepo contractorRepo;


    public Comments createComments(CommentsDto commentsDto) {

        Comments comments = new Comments();
        comments.setComment_info(commentsDto.getComment_info());
        TaskDetails taskDetails = new TaskDetails();
        taskDetails = taskDetailRepo.getOneTaskDetail(commentsDto.getTaskId());

        Contractor contractor=new Contractor();
        contractor=contractorRepo.getOneContractor(commentsDto.getContractorId());
        comments.setTaskDetails(taskDetails);
        comments.setContractor(contractor);
        commentRepo.save(comments);

        return comments;

    }

    public List<CommentsDto> getCommentsOfTask(Integer taskId) {
        List<Comments> comments = commentRepo.getCommentsByTaskId(taskId);

        List<CommentsDto> commentsDtos = comments.stream().map(pd -> this.commentToDto(pd)).collect(Collectors.toList());
        return commentsDtos;

    }

    public Comments dtoToComment(CommentsDto commentsDto) {
        Comments commentDto1 = this.modelMapper.map(commentsDto, Comments.class);
        return commentDto1;
    }

    public CommentsDto commentToDto(Comments comments) {
        CommentsDto commentsDto = this.modelMapper.map(comments, CommentsDto.class);
        return commentsDto;
    }
}