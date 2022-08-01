package com.dev21.tasking.controller;

import com.dev21.tasking.persistence.entity.Task;
import com.dev21.tasking.persistence.entity.TaskStatus;
import com.dev21.tasking.service.TaskService;
import com.dev21.tasking.service.dto.TaskInDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskingController {

  private final TaskService taskService;

  @PostMapping
  public Task createTask(@RequestBody TaskInDto taskInDto) {
    return taskService.createTask(taskInDto);
  }

  @GetMapping
  public List<Task> findAll() {
    return taskService.findAll();
  }

  @GetMapping("/status/{status}")
  public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus taskStatus) {
    return taskService.findAllByStatus(taskStatus);
  }

  @PatchMapping("mark_as_finished/{id}")
  public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
    taskService.updateTaskAsFinished(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    taskService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
