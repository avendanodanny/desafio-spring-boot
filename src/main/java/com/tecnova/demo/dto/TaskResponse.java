package com.tecnova.demo.dto;
;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {

    private String error;
    private TaskDTO taskDTO;

    public TaskResponse(String error, TaskDTO taskDTO){
        this.error = error;
        this.taskDTO = taskDTO;
    }

}
