package workerboard.repository.CustomRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import workerboard.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ScoreboardCustomRepositoryImpl implements ScoreboardCustomRepository {

    private int limitResults = 20;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExperienceScore> getExperienceScoreboard(SortType type,int limitResults) {
        this.limitResults = limitResults;
        return getExperienceScoreboard(type);
    }

    @Override
    public List<ExperienceScore> getExperienceScoreboard(SortType type) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ExperienceScore> criteria = builder
                .createQuery(ExperienceScore.class);


        Root<Experience> rootExperience = criteria.from(Experience.class);

        Join<Experience, ApplicationUser> applicationUserJoin = rootExperience
                .join("applicationUser", JoinType.LEFT);


        criteria.multiselect(rootExperience.get("id"),
                rootExperience.get("expTotalEarned"),
                rootExperience.get("level"),
                rootExperience.get("expToNextLevel"),
                applicationUserJoin.get("firstName"),
                applicationUserJoin.get("lastName"),
                rootExperience.get("attack"),
                rootExperience.get("defence"),
                rootExperience.get("knowledge"))
                .orderBy(setTypeOrder(builder,
                        rootExperience,
                        "expTotalEarned",
                        type));

        return entityManager.createQuery(criteria).setMaxResults(limitResults).getResultList();
    }

    @Override
    public List<PoliciesScore> getPoliciesScoreboard(LocalDate start, LocalDate end, SortType type,int limitResults) {
        this.limitResults = limitResults;

        return getPoliciesScoreboard(start,end ,type);
    }

    @Override
    public List<PoliciesScore> getPoliciesScoreboard(LocalDate start, LocalDate end, SortType type) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<PoliciesScore> criteria = builder
                .createQuery(PoliciesScore.class);


        Root<InsuranceApplication> rootInsurance = criteria.from(InsuranceApplication.class);

        Join<Experience, ApplicationUser> applicationUserJoin = rootInsurance
                .join("seller");

        criteria.groupBy(applicationUserJoin.get("id"));

        criteria.where(builder.between(rootInsurance.get("registerDate"), start, end));
        criteria.multiselect(applicationUserJoin.get("id"),
                applicationUserJoin.get("firstName"),
                applicationUserJoin.get("lastName"),
                builder.sum(rootInsurance.get("totalPolicyValue"))
        ).orderBy(setTypeOrder(builder,rootInsurance,"totalPolicyValue",type));

        return entityManager.createQuery(criteria).setMaxResults(limitResults).getResultList();
    }

    private <T> Order setTypeOrder( CriteriaBuilder builder, Root<T> root,
                                              String attribute, SortType type) {
        switch (type) {
            case ASC:
                return builder.asc(root.get(attribute));
            default:
                return builder.desc(root.get(attribute));
        }

    }

}
