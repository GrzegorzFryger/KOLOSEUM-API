package workerboard.serivce.Interface;

import org.springframework.data.jpa.domain.Specification;
import workerboard.model.ApplicationUser;

@FunctionalInterface
public interface SinglePredicate {

    Specification<ApplicationUser> createPredicate(String fieldName, String value);

}
