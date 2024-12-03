package org.ndissandea.adminassist.service;



import org.ndissandea.adminassist.model.Task;


import java.util.List;

public interface TaskService {
    public Task addTask(Task task);
    public void removeTask(long id);
    public Task updateTask(Task task, long id);
    public List<Task> getTasksList();
   public Task getTaskById(long id);

}
