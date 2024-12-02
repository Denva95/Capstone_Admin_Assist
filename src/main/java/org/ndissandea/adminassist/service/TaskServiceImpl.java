package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.taskNotFoundException;
import org.ndissandea.adminassist.model.Task;
import org.ndissandea.adminassist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(Task task) {
        logger.info("Adding a new task: {}", task);
        taskRepository.save(task);

        return task;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }


    @Override
    public Task update(Task task, long id) {
        return taskRepository.findById(id).map(tm->{
            tm.setTaskName(task.getTaskName());
            tm.setTaskDescription(task.getTaskDescription());
            tm.setTaskStatus(task.getTaskStatus());
            tm.setPriority(task.getPriority());
            return taskRepository.save(tm);
        }).orElse(null);

    }


    @Override
    public void remove(long id) {
        if(!taskRepository.existsById(id)){
            throw new taskNotFoundException("task does not exist");
        }
        taskRepository.deleteById(id);



    }
}
