package com.tecnova.demo.service;

import com.tecnova.demo.model.Task;
import com.tecnova.demo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testGetAllTasks() {
        // Datos simulados
        List<Task> tasks = Arrays.asList(new Task(), new Task());

        // Simular comportamiento del repositorio
        when(taskRepository.findAll()).thenReturn(tasks);

        // Llamar al método del servicio
        List<Task> result = taskService.getAllTask();

        // Validar respuesta
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }
    @Test
    void testGetTaskByIdFound() {
        // Datos simulados
        Long taskId = 1L;
        Task task = new Task();

        // Simular comportamiento del repositorio
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Llamar al método del servicio
        Task result = taskService.getTaskById(taskId);

        // Validar respuesta
        assertNotNull(result);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testGetTaskByIdNotFound() {
        // Datos simulados
        Long taskId = 1L;

        // Simular comportamiento del repositorio
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Llamar al método del servicio
        Task result = taskService.getTaskById(taskId);

        // Validar respuesta
        assertNull(result);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testCreateUpdateTask() {
        // Datos simulados
        Task task = new Task();

        // Simular comportamiento del repositorio
        when(taskRepository.save(task)).thenReturn(task);

        // Llamar al método del servicio
        Task result = taskService.createUpdateTask(task);

        // Validar respuesta
        assertNotNull(result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask() {
        // Datos simulados
        Long taskId = 1L;

        // Simular comportamiento del repositorio
        doNothing().when(taskRepository).deleteById(taskId);

        // Llamar al método del servicio
        taskService.deleteTask(taskId);

        // Validar respuesta
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
