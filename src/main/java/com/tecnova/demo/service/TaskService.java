package com.tecnova.demo.service;

import com.tecnova.demo.dto.TaskDTO;
import com.tecnova.demo.mapper.TaskMapper;
import com.tecnova.demo.model.Task;
import com.tecnova.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    /**
     * Method to get all the tasks
     * @return list of task
     */
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    /**
     * Method to get one task for id
     * @return task
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new task or update existing
     * @return create or update task
     */
    public Task createUpdateTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Method to delete one task for id
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Method to convert task entity in DTO
     */
    public TaskDTO convertToDTO(Task task) {
        return taskMapper.toDTO(task);
    }

    /**
     * Method to convert list task entity in list DTO
     */
    public List<TaskDTO> convertToListDTO(List<Task> tasks) {
        return taskMapper.toListDTO(tasks);
    }
}
