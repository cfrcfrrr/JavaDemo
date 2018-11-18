package comparetraversalwithdatabasedemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyRepo extends JpaRepository<MyEntity, Integer> {
    public List<MyEntity> findByVersionId(Long versionId);

    public List<MyEntity> findByNameAndParameterOneAndParameterTwoAndVersionId(String name, String parameterOne, String parameterTwo, Long versionId);
}
