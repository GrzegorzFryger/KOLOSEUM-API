package workerboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import workerboard.repository.CustomRepository.SimpleJpaCustomRepository;

@NoRepositoryBean
public interface BasicCustomRepository<T> extends JpaRepository<T, Long>, Repository<T,Long>,
        JpaSpecificationExecutor, SimpleJpaCustomRepository<T,Long> {

}