package transactiondemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import transactiondemo.entity.TransactionDemoEntity;

public interface TransactionDemoRepo extends JpaRepository<TransactionDemoEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE TransactionDemoEntity SET name = ?2 WHERE id = ?1")
    public void updateNameById(Long id, String name);

    @Query(value = "SELECT entity FROM TransactionDemoEntity entity WHERE entity.id = ?1")
    public TransactionDemoEntity findOneById(Long id);
}
