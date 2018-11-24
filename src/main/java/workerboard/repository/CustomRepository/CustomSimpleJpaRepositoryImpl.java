package workerboard.repository.CustomRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import workerboard.repository.CustomRepository.CustomSimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public class CustomSimpleJpaRepositoryImpl<T,ID extends Serializable>  extends SimpleJpaRepository<T, ID> implements CustomSimpleJpaRepository<T, ID> {

    private EntityManager entityManager;
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

    public CustomSimpleJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public CustomSimpleJpaRepositoryImpl(Class<T> domainClass, EntityManager em, EntityManager entityManager) {
        super(domainClass, em);
        this.entityManager = entityManager;
    }


    @Override
    @Transactional(readOnly = true)
    public List<String> findAllPropertyByAttribute(Specification<T> spec, Class<T> domainClass, String attribute)
            throws IllegalArgumentException{

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(spec, "Specification must not be null!");
        Assert.notNull(attribute, "Specification must not be null!");


        CriteriaBuilder builder =  entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(attribute));

        criteria.where(spec.toPredicate(root,criteria,builder));
        return entityManager.createQuery(criteria).getResultList();

    }




}
