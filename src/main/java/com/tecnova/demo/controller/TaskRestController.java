package com.tecnova.demo.controller;

import com.tecnova.demo.dto.TaskDTO;
import com.tecnova.demo.dto.TaskResponse;
import com.tecnova.demo.model.Task;
import com.tecnova.demo.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    private static Logger logger = LoggerFactory.getLogger(TaskRestController.class);

    @Autowired
    private ITaskService taskService;

    @Operation(summary = "Method to get all the tasks", description = "Method to get all the tasks", tags={ "task" }	)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity your body is list of tasks"),
            @ApiResponse(responseCode = "500", description = "Error at add task")
    })
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        logger.info("llega al método getAllTasks...");
        List<Task> tasks = taskService.getAllTask();
        List<TaskDTO> tasksResponse = taskService.convertToListDTO(tasks);
        return new ResponseEntity<>(tasksResponse, HttpStatus.OK);
    }

    @Operation(summary = "Method to get one task for id", description = "Method to get one task for id", tags={ "task" }	)
    @Parameter(name = "id", description = "task id", example = "1", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity your body contain task"),
            @ApiResponse(responseCode = "204", description = "Data not found"),
            @ApiResponse(responseCode = "500", description = "Error at task select")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long id) {
        logger.info("llega al método getTaskById...");
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(taskService.convertToDTO(task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Method to create new task", description = "Method to create new task", tags={ "task" }	)
    @Parameter(name = "task", description = "new task", example = "{\n" +
            "    \"name\": \"tarea de prueba tres (2)\",\n" +
            "    \"initDate\": \"2025-02-28T16:26:49.000+00:00\",\n" +
            "    \"endDate\": \"2025-02-28T16:26:49.000+00:00\",\n" +
            "    \"user\": {\n" +
            "        \"id\": 16688059\n" +
            "    },\n" +
            "    \"taskState\": {\n" +
            "        \"id\": 4\n" +
            "    }\n" +
            "}", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entity your body contain task"),
            @ApiResponse(responseCode = "500", description = "Error at task insert")
    })
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody Task task) {
        logger.info("llega al método createTask...");
        TaskResponse response = null;
        try {
            Task savedTask = taskService.createUpdateTask(task);
            response = new TaskResponse(null, taskService.convertToDTO(savedTask));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            response = new TaskResponse("Ha ocurrido un error al momento de guardar la tarea: " + e.getMessage(),
                    null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Method to update task", description = "Method to update task", tags={ "task" }	)
    @Parameter(name = "id", description = "task id", example = "1", required = true)
    @Parameter(name = "task", description = "new task", example = "{\n" +
            "    \"name\": \"tarea de prueba tres (2)\",\n" +
            "    \"initDate\": \"2025-02-28T16:26:49.000+00:00\",\n" +
            "    \"endDate\": \"2025-02-28T16:26:49.000+00:00\",\n" +
            "    \"user\": {\n" +
            "        \"id\": 16688059\n" +
            "    },\n" +
            "    \"taskState\": {\n" +
            "        \"id\": 4\n" +
            "    }\n" +
            "}", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity your body contain task"),
            @ApiResponse(responseCode = "500", description = "Error at task update")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        logger.info("llega al método updateTask...");
        Task existingTask = taskService.getTaskById(id);
        TaskResponse response = null;
        if (existingTask != null) {
            existingTask.setUser(task.getUser());
            existingTask.setTaskState(task.getTaskState());
            existingTask.setName(task.getName());
            existingTask.setInitDate(task.getInitDate());
            existingTask.setEndDate(task.getEndDate());

            try {
                Task updatedTask = taskService.createUpdateTask(existingTask);
                response = new TaskResponse(null, taskService.convertToDTO(updatedTask));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (Exception e){
                logger.error(e.getMessage());
                response = new TaskResponse("Ha ocurrido un error al momento de actualizar la tarea: " + e.getMessage(),
                        null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Method to delete one task for id", description = "Method to delete one task for id", tags={ "task" }	)
    @Parameter(name = "id", description = "task id", example = "4", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entity with http status, delete ok"),
            @ApiResponse(responseCode = "500", description = "Error at task delete")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Long id) {
        logger.info("llega al método deleteTask...");
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
