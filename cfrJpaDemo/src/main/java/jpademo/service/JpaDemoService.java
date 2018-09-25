package jpademo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpademo.dao.JpaDemoRepo;
import jpademo.entity.JpaDemoEntity;


@Service
//@Transactional
public class JpaDemoService
{
    @Autowired
    private JpaDemoRepo userJpaRepository;
//    @Autowired
//    private UserRepository userRepository;
    
    public JpaDemoEntity saveUser(JpaDemoEntity entity)
    {
    	return userJpaRepository.save(entity);
    }

    //
//    public List<User> findAll()
//    {
//        return userJpaRepository.findAll();
//    }
//
//    public List<User> findByName(String name)
//    {
//        List<User> userList1 = userRepository.findByName1(name);
//        List<User> userList2 = userRepository.findByName2(name);
//        List<User> userList3 = userRepository.findByNameAndAddress(name, "3");
//        System.out.println("userList1:" + userList1);
//        System.out.println("userList2:" + userList2);
//        System.out.println("userList3:" + userList3);
//        return userRepository.findByName(name);
//    }


//    @Cacheable("users")
//    public User findOne(long id)
//    {
//        System.out.println("Cached Pages");
//        return userJpaRepository.findOne(id);
//    }
//
//    public void delete(long id)
//    {
//        userJpaRepository.delete(id);
//    }
}