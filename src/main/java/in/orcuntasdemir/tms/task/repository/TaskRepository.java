package in.orcuntasdemir.tms.task.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.orcuntasdemir.tms.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByisDone(Boolean isDone);

}
