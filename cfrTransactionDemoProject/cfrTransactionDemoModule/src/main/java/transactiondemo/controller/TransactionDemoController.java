package transactiondemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import transactiondemo.entity.TransactionDemoEntity;
import transactiondemo.service.TransactionDemoService;

import java.util.List;

@RestController
public class TransactionDemoController {
    @Autowired
    private TransactionDemoService transactionDemoService;

    @RequestMapping(value = "/entity", method= RequestMethod.POST)
    public String addEntity(@RequestBody TransactionDemoEntity entity) {
        TransactionDemoEntity ret = transactionDemoService.saveUser(entity);
        return ret.toString();
    }

    @RequestMapping(value = "/entity", method=RequestMethod.DELETE)
    public String deleteEntityById(@RequestBody TransactionDemoEntity entity) {
        transactionDemoService.delete(entity);
        return "success";
    }

    @RequestMapping(value = "/entity/all", method = RequestMethod.GET)
    public String findAll() {
        List<TransactionDemoEntity> result = transactionDemoService.findAll();
        return result.toString();
    }

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public String findById(@RequestParam long id) {
        return transactionDemoService.findById(id).toString();
    }

    @RequestMapping(value = "/entity", method=RequestMethod.PUT)
    public String updateNameById(@RequestParam long id, @RequestParam String name) {
        transactionDemoService.updateNameById(id, name);
        return "success";
    }
}
