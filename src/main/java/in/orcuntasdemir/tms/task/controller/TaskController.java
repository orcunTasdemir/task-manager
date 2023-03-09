package in.orcuntasdemir.tms.task.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.orcuntasdemir.tms.task.entity.Task;
import in.orcuntasdemir.tms.task.service.TaskService;

@Controller
public class TaskController {

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	private TaskService taskService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	// handler method to handle list of tasks to return mode and view

	@GetMapping("/tasks")
	public String listTasks(@RequestParam(required = false) Map<String, String> qparams, Model model) {
		String status = qparams.get("status");
		String dir = qparams.get("dir");
		List<Task> tasks = taskService.getAllTasks(status);		
		tasks = taskService.sortByDueDate(tasks, dir);
		model.addAttribute("tasks", tasks);

		return "tasks";
	}

	@GetMapping("/tasks/new")
	public String createTaskForm(Model model) {

		// create task object to hold task form data
		Task task = new Task();
		model.addAttribute("task", task);
		return "create_task";
	}

	@PostMapping("/tasks")
	public String saveTask(@ModelAttribute("task") Task task) {
		taskService.saveTask(task);
		return "redirect:/tasks";
	}

	@GetMapping("/tasks/complete/{id}")
	public String completeTask(@PathVariable Long id, Model model) {
		Task existingTask = taskService.getTaskById(id);
		existingTask.setIsDone(!existingTask.getIsDone());
		// Save the updated task object
		taskService.updateTask(existingTask);
		return "redirect:/tasks";
	}

	@GetMapping("/tasks/edit/{id}")
	public String editTaskForm(@PathVariable Long id, Model model) {
		model.addAttribute("task", taskService.getTaskById(id));
		return "edit_task";
	}

	@PostMapping("/tasks/{id}")
	public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task, Model model) {

		// get task from database by id
		Task existingTask = taskService.getTaskById(id);

		// update the task
		existingTask.setId(id);
		existingTask.setTitle(task.getTitle());
		existingTask.setBody(task.getBody());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setIsDone(task.getIsDone());

		// Save the updated task object
		taskService.updateTask(existingTask);
		return "redirect:/tasks";
	}

	@GetMapping("/tasks/{id}")
	public String deleteTask(@PathVariable long id) {
		taskService.deleteTaskById(id);
		return "redirect:/tasks";
	}

}
