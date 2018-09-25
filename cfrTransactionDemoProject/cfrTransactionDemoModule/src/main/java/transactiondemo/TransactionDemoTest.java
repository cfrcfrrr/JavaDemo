package transactiondemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import transactiondemo.service.TransactionDemoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionDemoTest {
    @Autowired
    TransactionDemoService transactionDemoService;

    @Test
    public void transactionTest() throws Exception {
        transactionDemoService.transactionTest();
    }
}
