package com.dev21.tasking.mapper;

import com.dev21.tasking.persistence.entity.Task;
import com.dev21.tasking.persistence.entity.TaskStatus;
import com.dev21.tasking.service.dto.TaskInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDtoToTask implements Mapper<TaskInDto, Task> {

  @Override
  public Task map(TaskInDto in) {
    Task task = new Task();
    task.setTitle(in.getTitle());
    task.setDescription(in.getDescription());
    task.setEta(in.getEta());
    task.setCreatedDate(LocalDateTime.now());
    task.setFinished(false);
    task.setTaskStatus(TaskStatus.ON_TIME);
    return task;
  }
}
