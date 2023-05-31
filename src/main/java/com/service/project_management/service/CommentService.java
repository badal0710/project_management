package com.service.project_management.service;


import com.service.project_management.Entities.Comments;
import com.service.project_management.Entities.TaskDetails;
import com.service.project_management.Repositories.CommentRepo;
import com.service.project_management.Repositories.TaskDetailRepo;
import com.service.project_management.dto.CommentsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

@Autowired
CommentRepo commentRepo;
@Autowired
ModelMapper modelMapper;
@Autowired
TaskDetailRepo taskDetailRepo;


public Comments createComments(CommentsDto commentsDto){

    Comments comments = new Comments();
    comments.setComment_date(commentsDto.getComment_date());
    comments.setComment_info(commentsDto.getComment_info());
    TaskDetails taskDetails = new TaskDetails();
    taskDetails = taskDetailRepo.getOneTaskDetail(commentsDto.getTaskId());
    comments.setTaskDetails(taskDetails);
    commentRepo.save(comments);

    return comments;

}

public Comments dtoToComment(CommentsDto commentsDto)
{
    Comments commentDto1=this.modelMapper.map(commentsDto,Comments.class);
    return commentDto1;
}
public CommentsDto commentToDto(Comments comments) {
    CommentsDto commentsDto = this.modelMapper.map(comments, CommentsDto.class);
    return commentsDto;
}
}