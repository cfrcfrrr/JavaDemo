package jpademo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import jpademo.entity.JpaDemoEntity;

public interface JpaDemoRepo extends JpaRepository<JpaDemoEntity, String> {

}
