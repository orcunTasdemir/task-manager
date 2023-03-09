package in.orcuntasdemir.tms.task.service;

import java.util.Date;
import java.util.List;

import in.orcuntasdemir.tms.task.entity.Task;

public interface TaskService {
	
	List<Task> getAllTasks(String status);

	Task saveTask(Task task);

	Task getTaskById(Long id);
	
	Task updateTask(Task task);

	void deleteTaskById(Long id);
		
	List<Task> sortByDueDate(List<Task> tasks, String dir);
}
