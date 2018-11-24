package workerboard.serivce;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import workerboard.exception.NotFound;
import workerboard.repository.BasicRepository;
import workerboard.serivce.mapper.MapGenerate;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BasicAbstractService<T> {

    private BasicRepository<T> basicRepository;


    public void setBasicRepository(BasicRepository<T> basicRepository) {
        this.basicRepository = basicRepository;
    }


    @FunctionalInterface
    public interface SpecificationFunctional {
        Specification createSpecification(Map<String, String> map);
    }


    public static SpecificationFunctional andCriteria() {
        return (map) -> {
            return (root, query, cb) -> {
                return cb.and(map.entrySet().stream().map((k) -> {
                    return cb.equal(root.get(k.getKey()), k.getValue());
                }).toArray(Predicate[]::new));
            };
        };
    }

    @SuppressWarnings("unchecked")
    //Accept only map with one value
    public static SpecificationFunctional likeCriteria() throws IllegalArgumentException {
        return (map) -> {
            return (root, query, cb) -> {
                return cb.and(cb.like(root.get(map.entrySet().iterator().next().getKey()),
                        "%" + map.entrySet().iterator().next().getValue() + "%"));
            };
        };
    }


    public Optional<T> findOne(Long id) throws NotFound {
        return basicRepository.findById(id);
    }

    public List<T> findALl() {
        return basicRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    public <U extends MapGenerate> List<String> findPropertyByAttribute(SpecificationFunctional criteria, U classCastToMap,
                                                                        String selectedAttribute, Class<T> domain) {
        Assert.notNull(classCastToMap, "Domain class must not be null!");
        return findPropertyByAttribute(criteria,
                classCastToMap.toMap(),
                selectedAttribute,
                domain);
    }

    @SuppressWarnings("unchecked")
    public List<String> findPropertyByAttribute(SpecificationFunctional criteria, Map<String, String> mapParameters,
                                                String attribute, Class<T> domain) {

        Assert.notEmpty(mapParameters, "Map must not be empty ");
        return basicRepository
                .findAllPropertyByAttribute(criteria.createSpecification(mapParameters),
                        domain,
                        attribute);
    }


    @SuppressWarnings("unchecked")
    public <U extends MapGenerate> List<T> findAllByCriteria(SpecificationFunctional criteria, U classCastToMap) {

        Assert.notNull(classCastToMap, "Domain class must not be null!");
        return findAllByCriteria(criteria,
                classCastToMap.toMap());
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllByCriteria(SpecificationFunctional criteria, Map<String, String> mapParameters){

        Assert.notEmpty(mapParameters, "Map must not be empty ");
        return basicRepository
                .findAll(criteria.createSpecification(mapParameters));
    }


}
