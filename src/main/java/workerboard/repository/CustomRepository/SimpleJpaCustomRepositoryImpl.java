package workerboard.repository.CustomRepository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public class SimpleJpaCustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SimpleJpaCustomRepository<T, ID> {

    private EntityManager entityManager;
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

    public SimpleJpaCustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public SimpleJpaCustomRepositoryImpl(Class<T> domainClass, EntityManager em, EntityManager entityManager) {
        super(domainClass, em);
        this.entityManager = entityManager;
    }




    @Override
    @Transactional(readOnly = true)
    public List<String> findAllProjectionByAttributeAndCriteria(Specification<T> spec, Class<T> domainClass,
                                                                String attribute, boolean distinct, SortType sortBy)
            throws IllegalArgumentException {
        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(spec, "Specification must not be null!");
        Assert.notNull(attribute, "Specification must not be null!");


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(attribute));
        criteria.where(spec.toPredicate(root, criteria, builder));

        return findAllProjection(
                setTypeOrder(this.setDistinct(criteria), builder, root, attribute, sortBy)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllProjectionByAttributeAndCriteria(Specification<T> spec, Class<T> domainClass, String attribute)
            throws IllegalArgumentException {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(spec, "Specification must not be null!");
        Assert.notNull(attribute, "Specification must not be null!");


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(attribute));

        criteria.where(spec.toPredicate(root, criteria, builder));
        return entityManager.createQuery(criteria).getResultList();

    }


    @Override
    @Transactional(readOnly = true)
    public List<String> findAllProjection(Class<T> domainClass, String searchAttribute, SortType sortBy)
            throws IllegalArgumentException {
        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(searchAttribute, "Specification must not be null!");

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(searchAttribute));

        return findAllProjection(
                setTypeOrder(criteria, builder, root, searchAttribute, sortBy)
        );

    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllProjection(Class<T> domainClass, String searchAttribute, boolean distinct, SortType sortBy)
            throws IllegalArgumentException {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(searchAttribute, "Specification must not be null!");

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(searchAttribute));

        return findAllProjection(
                setTypeOrder(this.setDistinct(criteria), builder, root, searchAttribute, sortBy)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllProjection(Class<T> domainClass, String searchAttribute, boolean distinct)
            throws IllegalArgumentException {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(searchAttribute, "Specification must not be null!");

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(searchAttribute));

        return findAllProjection(
                this.setDistinct(criteria)
        );
    }


    @Override
    @Transactional(readOnly = true)
    @Deprecated
    public List<String> findAllProjection(Class<T> domainClass, String searchAttribute)
            throws IllegalArgumentException {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(searchAttribute, "Specification must not be null!");


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<String> criteria = builder
                .createQuery(String.class);

        Root<T> root = criteria.from(domainClass);
        criteria.select(root.get(searchAttribute));

        return findAllProjection(criteria);
    }




    private <U> CriteriaQuery<U> setDistinct(CriteriaQuery<U> criteriaQuery) {
        return criteriaQuery.distinct(true);
    }


    private <U> CriteriaQuery<U> setTypeOrder(CriteriaQuery<U> criteriaQuery, CriteriaBuilder builder, Root<T> root,
                                              String attribute, SortType type) {

        switch (type) {
            case ASC:
                return criteriaQuery.orderBy(builder.asc(root.get(attribute)));
            case DESC:
                return criteriaQuery.orderBy(builder.desc(root.get(attribute)));
        }

        return criteriaQuery;

    }

    private <U> List<U> findAllProjection(CriteriaQuery<U> criteria) {
        return entityManager.createQuery(criteria).getResultList();
    }


}
