package workerboard.repository.CustomRepository;

import workerboard.model.ExperienceScore;
import workerboard.model.PoliciesScore;

import java.time.LocalDate;
import java.util.List;

public interface ScoreboardCustomRepository {

    public List<ExperienceScore> getExperienceScoreboard(SortType typ);
    public List<ExperienceScore> getExperienceScoreboard(SortType type,int limitResults);
    public List<PoliciesScore> getPoliciesScoreboard(LocalDate start, LocalDate end, SortType type,int limitResults);
    public List<PoliciesScore> getPoliciesScoreboard(LocalDate start, LocalDate end, SortType type);


}
