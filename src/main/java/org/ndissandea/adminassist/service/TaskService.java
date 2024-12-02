package org.ndissandea.adminassist.service;



import org.ndissandea.adminassist.model.Task;


import java.util.List;

public interface TaskService {
    public Task add(Task task);
    public void remove(long id);
    public Task update(Task task, long id);
    public List<Task> getTasks();

}
