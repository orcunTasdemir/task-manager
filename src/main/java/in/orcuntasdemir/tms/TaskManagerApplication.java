package in.orcuntasdemir.tms;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.orcuntasdemir.tms.task.entity.Task;
import in.orcuntasdemir.tms.task.repository.TaskRepository;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void run(String... args) throws Exception {

//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//
//		taskRepository.deleteAll();
//
//		Task task1 = new Task("first task", "body", formatter.parse("11/11/1111"), false);
//		taskRepository.save(task1);
//		
//		Task task2 = new Task("second task", "body2", formatter.parse("11/11/1111"), true);
//		taskRepository.save(task2);
//
//		Task task3 = new Task("third task", "body2", formatter.parse("11/11/1111"), true);
//		taskRepository.save(task3);

	}
}
