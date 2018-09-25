package objectassociatedemo.service;

import objectassociatedemo.entity.ObjectAssociateDemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import objectassociatedemo.dao.ObjectAssociateDemoRepo;

import java.util.List;


@Service
public class ObjectAssociateDemoService
{
    @Autowired
    private ObjectAssociateDemoRepo objectAssociateDemoRepo;
    
    public ObjectAssociateDemoEntity saveUser(ObjectAssociateDemoEntity entity)
    {
    	return objectAssociateDemoRepo.save(entity);
    }

    public void delete(ObjectAssociateDemoEntity entity) {
        objectAssociateDemoRepo.delete(entity);
    }

    public List<ObjectAssociateDemoEntity> findAll() {
        return objectAssociateDemoRepo.findAll();
    }

    public ObjectAssociateDemoEntity findById(Integer id) {
        return objectAssociateDemoRepo.findById(id);
    }

    public void updateNameById(Integer id, String name) {
        objectAssociateDemoRepo.updateNameById(id, name);
    }
}