package com.tecnova.demo.service;

import com.tecnova.demo.dto.TaskDTO;
import com.tecnova.demo.model.Task;

import java.util.List;

public interface ITaskService {

    public List<Task> getAllTask();

    public Task getTaskById(Long id);

    public Task createUpdateTask(Task task);

    public void deleteTask(Long id);

    public TaskDTO convertToDTO(Task task);

    public List<TaskDTO> convertToListDTO(List<Task> tasks);

}
