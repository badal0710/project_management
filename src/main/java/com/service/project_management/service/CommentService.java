package com.service.project_management.service;


import com.service.project_management.Entities.*;
import com.service.project_management.Repositories.*;
import com.service.project_management.dto.ProjectComments.ProjectCommentDto;
import com.service.project_management.dto.ProjectComments.ProjectCommentDtoGet;
import com.service.project_management.dto.TaskComments.CommentsDto;
import com.service.project_management.dto.TaskComments.CommentsDtoGet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private InvestorRepo investorRepo;
    @Autowired
    private ProjectCommentsRepo projectCommentsRepo;


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

    public List<CommentsDtoGet> getCommentsOfTask(Integer taskId) {
        List<Comments> comments = commentRepo.getCommentsByTaskId(taskId);

        List<CommentsDtoGet> commentsDtos = comments.stream().map(pd -> this.commentToDto(pd)).collect(Collectors.toList());
        return commentsDtos;

    }


    public ProjectComments createProjectComments(ProjectCommentDto projectCommentDto) {

        ProjectComments projectComments=new ProjectComments();
        projectComments.setComment_info(projectComments.getComment_info());

        Project project=new Project();
        project = projectRepo.getOneProject(projectCommentDto.getProjectId());

        Investor investor=new Investor();

        investor=investorRepo.getById(projectCommentDto.getInvestorId());
        projectComments.setInvestor(investor);
        projectComments.setProject(project);
        projectCommentsRepo.save(projectComments);

        return projectComments;

    }

    public List<ProjectCommentDtoGet> getProjectComment(Integer projectId) {
        List<ProjectComments> comments = projectCommentsRepo.getCommentByProject(projectId);

        List<ProjectCommentDtoGet> projectCommentDtos = comments.stream().map(pd -> this.projectCommnetToDto(pd)).collect(Collectors.toList());
        return projectCommentDtos;

    }








    public ProjectCommentDtoGet projectCommnetToDto(ProjectComments projectComments){
        ProjectCommentDtoGet commentsDto = this.modelMapper.map(projectComments, ProjectCommentDtoGet.class);
        return commentsDto;
    }





    public Comments dtoToComment(CommentsDtoGet commentsDto) {
        Comments commentDto1 = this.modelMapper.map(commentsDto, Comments.class);
        return commentDto1;
    }

    public CommentsDtoGet commentToDto(Comments comments) {
        CommentsDtoGet commentsDto = this.modelMapper.map(comments, CommentsDtoGet.class);
        return commentsDto;
    }
}