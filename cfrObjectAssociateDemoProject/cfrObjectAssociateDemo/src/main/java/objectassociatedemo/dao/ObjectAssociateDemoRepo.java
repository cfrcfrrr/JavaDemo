package objectassociatedemo.dao;

import objectassociatedemo.entity.ObjectAssociateDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ObjectAssociateDemoRepo extends JpaRepository<ObjectAssociateDemoEntity, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE ObjectAssociateDemoEntity SET name = ?2 WHERE id = ?1")
    public void updateNameById(Integer id, String name);

    @Query(value = "SELECT entity FROM ObjectAssociateDemoEntity entity WHERE entity.id = ?1")
    public ObjectAssociateDemoEntity findById(Integer id);
}
