package com.dev21.tasking.service;

import com.dev21.tasking.exceptions.TaskException;
import com.dev21.tasking.mapper.Mapper;
import com.dev21.tasking.mapper.TaskInDtoToTask;
import com.dev21.tasking.persistence.entity.Task;
import com.dev21.tasking.persistence.entity.TaskStatus;
import com.dev21.tasking.persistence.repository.TaskRepository;
import com.dev21.tasking.service.dto.TaskInDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

  private final TaskRepository repository;
  private final Mapper<TaskInDto, Task> mapper;

  public Task createTask(TaskInDto taskInDto) {
    Task task = mapper.map(taskInDto);
    return this.repository.save(task);
  }

  public List<Task> findAll() {
    return repository.findAll();
  }

  public List<Task> findAllByStatus(TaskStatus taskStatus) {
    return repository.findAllByTaskStatus(taskStatus);
  }

  @Transactional
  public void updateTaskAsFinished(Long id) {
    Optional<Task> optTask = repository.findById(id);
    if (optTask.isPresent()) {
      repository.markTasAsFinished(id);
    }
    throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
  }

  public void deleteById(Long id) {
    Optional<Task> optTask = repository.findById(id);
    if (optTask.isEmpty()) {
      throw new TaskException("Task not found", HttpStatus.NOT_FOUND);
    }
    repository.deleteById(id);
  }

}
