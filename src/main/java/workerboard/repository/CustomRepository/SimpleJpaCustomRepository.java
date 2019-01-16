package workerboard.repository.CustomRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface SimpleJpaCustomRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

    @Transactional(readOnly = true)
    List<String> findAllProjectionByAttributeAndCriteria(Specification<T> spec, Class<T> domainClass, String attribute, boolean distinct, SortType sortBy)
            throws IllegalArgumentException;

    @Transactional(readOnly = true)
    List<String> findAllProjectionByAttributeAndCriteria(Specification<T> spec, Class<T> domainClass, String attribute)
            throws IllegalArgumentException;


    @Transactional(readOnly = true)
    List<String> findAllProjection(Class<T> domainClass, String searchAttribute, SortType sortBy)
            throws IllegalArgumentException;

    @Transactional(readOnly = true)
    List<String> findAllProjection(Class<T> domainClass, String searchAttribute, boolean distinct, SortType sortBy)
            throws IllegalArgumentException;

    @Transactional(readOnly = true)
    List<String> findAllProjection(Class<T> domainClass, String searchAttribute, boolean distinct)
            throws IllegalArgumentException;

    @Transactional(readOnly = true)
    List<String> findAllProjection(Class<T> domainClass, String attribute);


}
