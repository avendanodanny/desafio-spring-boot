package com.tecnova.demo.controller;

import com.tecnova.demo.dto.TaskDTO;
import com.tecnova.demo.dto.TaskResponse;
import com.tecnova.demo.model.Task;
import com.tecnova.demo.service.ITaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskRestControllerTest {

    @Mock
    private ITaskService taskService;

    @InjectMocks
    private TaskRestController taskRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllTask() {
        // Datos simulados
        List<Task> tasks = Arrays.asList(new Task(), new Task());

        // Simular comportamiento del servicio
        when(taskService.getAllTask()).thenReturn(tasks);

        // Llamar al método del controlador
        ResponseEntity<List<TaskDTO>> response = taskRestController.getAllTasks();

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        // Verificar que el servicio fue llamado
        verify(taskService, times(1)).getAllTask();
    }

    @Test
    void testGetTaskByIdFound() {
        // Datos simulados
        Long taskId = 1L;
        Task task = new Task();

        // Simular comportamiento del servicio
        when(taskService.getTaskById(taskId)).thenReturn(task);

        // Llamar al método del controlador
        ResponseEntity<TaskDTO> response = taskRestController.getTaskById(taskId);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testCreateUpdateTask() {
        // Datos simulados
        Task taskParam = new Task();

        Task savedTask = new Task();

        // Simular comportamiento del servicio
        when(taskService.createUpdateTask(taskParam)).thenReturn(savedTask);

        // Llamar al método del controlador
        ResponseEntity<TaskResponse> response = taskRestController.createTask(taskParam);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testUpdateTask() {
        // Datos simulados
        Task taskParam = new Task();
        Task savedTask = new Task();

        // Simular comportamiento del servicio
        when(taskService.getTaskById(any())).thenReturn(savedTask);
        when(taskService.createUpdateTask(any())).thenReturn(savedTask);

        // Llamar al método del controlador
        ResponseEntity<TaskResponse> response = taskRestController.updateTask(1L, taskParam);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTask() {
        // Datos simulados
        Long taskId = 1L;

        // Simular comportamiento del servicio
        Mockito.doNothing().when(taskService).deleteTask(any());

        // Llamar al método del controlador
        ResponseEntity<HttpStatus> response = taskRestController.deleteTask(taskId);

        // Validar respuesta
        assertNotNull(response);
    }

    @Test
    void testGetTaskByIdNotFound() {
        // Datos simulados
        Long taskId = 1L;

        // Simular comportamiento del servicio
        when(taskService.getTaskById(taskId)).thenReturn(null);

        // Llamar al método del controlador
        ResponseEntity<TaskDTO> response = taskRestController.getTaskById(taskId);

        // Validar respuesta
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

}
