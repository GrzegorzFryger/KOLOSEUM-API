package workerboard.repository.CustomRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CustomSimpleJpaRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {


    List<String> findAllPropertyByAttribute(Specification<T> spec, Class<T> domainClass, String attribute)
            throws IllegalArgumentException;

}
