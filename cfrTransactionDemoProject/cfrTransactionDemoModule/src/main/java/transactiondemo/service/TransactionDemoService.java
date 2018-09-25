package transactiondemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transactiondemo.dao.TransactionDemoRepo;
import transactiondemo.entity.TransactionDemoEntity;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TransactionDemoService {
    @Autowired
    private TransactionDemoRepo transactionDemoRepo;

    public TransactionDemoEntity saveUser(TransactionDemoEntity entity)
    {
        return transactionDemoRepo.save(entity);
    }

    public void delete(TransactionDemoEntity entity) {
        transactionDemoRepo.delete(entity);
    }

    public List<TransactionDemoEntity> findAll() {
        return transactionDemoRepo.findAll();
    }

    public TransactionDemoEntity findById(Long id) {
        return transactionDemoRepo.findOneById(id);
    }

    public void updateNameById(Long id, String name) {
        transactionDemoRepo.updateNameById(id, name);
    }

    @Transactional
    public void transactionTest() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        transactionDemoRepo.save(new TransactionDemoEntity("a " + sdf.format(System.currentTimeMillis())));
        if(true) {
            throw new RuntimeException("myException");
        }
        transactionDemoRepo.save(new TransactionDemoEntity("b " + sdf.format(System.currentTimeMillis())));
    }
}
