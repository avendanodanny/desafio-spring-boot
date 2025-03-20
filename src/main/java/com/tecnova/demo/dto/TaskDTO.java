package com.tecnova.demo.dto;

import com.tecnova.demo.model.TaskState;
import com.tecnova.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private User user;
    private TaskState taskState;
    private String name;
    private Date initDate;
    private Date endDate;
}
