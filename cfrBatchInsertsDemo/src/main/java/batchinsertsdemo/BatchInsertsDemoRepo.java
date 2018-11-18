package batchinsertsdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchInsertsDemoRepo extends JpaRepository<BatchInsertsDemoEntity, String> {
}
