package workerboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import workerboard.repository.CustomRepository.CustomSimpleJpaRepositoryImpl;
import workerboard.serivce.BasicExperiencePointManager;
import workerboard.serivce.ExperiencePointManager;

import java.util.concurrent.Executor;


@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(repositoryBaseClass = CustomSimpleJpaRepositoryImpl.class)
public class WorkerBoardApplication {

    public static void main(String[] args) {

        SpringApplication.run(WorkerBoardApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExperiencePointManager experiencePointManager(){
       BasicExperiencePointManager basicExperiencePointManager = new BasicExperiencePointManager();

       basicExperiencePointManager.setConverterPoint(100);
        return basicExperiencePointManager;
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        executor.initialize();
        return executor;
    }




}
