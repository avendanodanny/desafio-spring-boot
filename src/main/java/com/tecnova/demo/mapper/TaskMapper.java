package com.tecnova.demo.mapper;

import com.tecnova.demo.dto.TaskDTO;
import com.tecnova.demo.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(Task user);
    Task toEntity(TaskDTO userDTO);

    List<TaskDTO> toListDTO(List<Task> listUser);
}
