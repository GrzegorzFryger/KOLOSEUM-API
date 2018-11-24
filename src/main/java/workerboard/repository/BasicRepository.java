package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import workerboard.repository.CustomRepository.CustomSimpleJpaRepository;

@NoRepositoryBean
public interface BasicRepository <T> extends JpaRepository<T, Long>, Repository<T,Long>,
        JpaSpecificationExecutor, CustomSimpleJpaRepository<T,Long> {

}