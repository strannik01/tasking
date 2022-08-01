package com.dev21.tasking.persistence.repository;

import com.dev21.tasking.persistence.entity.Task;
import com.dev21.tasking.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID =:id", nativeQuery = true)
    void markTasAsFinished(@Param("id") Long id);
}
