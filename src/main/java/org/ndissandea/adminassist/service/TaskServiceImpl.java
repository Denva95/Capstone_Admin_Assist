package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.employeeNotFoundException;
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
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksList() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new taskNotFoundException("Missing Id"));
    }


    @Override
    public Task updateTask(Task task, long id) {
        return taskRepository.findById(id).map(tm->{
            tm.setTaskName(task.getTaskName());
            tm.setTaskDescription(task.getTaskDescription());
            tm.setTaskStatus(task.getTaskStatus());
            tm.setPriority(task.getPriority());
            return taskRepository.save(tm);
        }).orElse(null);

    }


    @Override
    public void removeTask(long id) {
        if(!taskRepository.existsById(id)){
            throw new taskNotFoundException("task does not exist");
        }
        taskRepository.deleteById(id);



    }
}
