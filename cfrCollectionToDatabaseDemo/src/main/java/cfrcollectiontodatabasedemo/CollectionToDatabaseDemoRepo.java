package cfrcollectiontodatabasedemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionToDatabaseDemoRepo extends JpaRepository<CollectionToDatabaseDemoEntity, Integer> {
}
