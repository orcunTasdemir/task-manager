package in.orcuntasdemir.tms.task.service.Impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;

import in.orcuntasdemir.tms.task.entity.Task;
import in.orcuntasdemir.tms.task.repository.TaskRepository;
import in.orcuntasdemir.tms.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getAllTasks(String status) {
		List<Task> allTasks = null;
		System.out.println(status);
		if (status == null || status.isEmpty()) {
			allTasks = taskRepository.findAll();
		} else if (status.equals("completed")) {
			System.out.println("iscompleted");

			allTasks = taskRepository.findByisDone(true);
		} else if (status.equals("ongoing")) {
			allTasks = taskRepository.findByisDone(false);
		}
		return allTasks;
	}

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.findById(id).get();
	}

	@Override
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public List<Task> sortByDueDate(List<Task> tasks, String dir) {
		if (dir == null || dir.equals("descending")) {
			tasks.sort(Comparator.comparing(Task::getDueDate));
		} else {
			tasks.sort(Comparator.comparing(Task::getDueDate).reversed());
		}
		return tasks;

	}

}
