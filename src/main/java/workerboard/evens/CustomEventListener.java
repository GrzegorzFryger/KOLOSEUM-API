package workerboard.evens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import workerboard.model.ApplicationUser;
import workerboard.model.Experience;
import workerboard.serivce.ExperienceService;

@Component
public class CustomEventListener {


    private ExperienceService service;

    @Autowired
    public CustomEventListener(ExperienceService service) {
        this.service = service;
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void handle(InsuranceEvent applicationCreateEvent) {

//        ApplicationUser user = new ApplicationUser();
//        user.setId(Long.valueOf(1));
//
//       applicationCreateEvent.getInsurance().setSeller(user);



      //  System.out.print("\n"+"Inside evenListiner : id : "+ applicationCreateEvent.getInsurance().getInstallmentAmount()+ "\n");

        service.setPoint(applicationCreateEvent.getInsurance());

        System.out.print("\n"+ " Listner Watek : "+  Thread.currentThread().getId()+ " Nazwa : "+Thread.currentThread().getName() + applicationCreateEvent.getInsurance()+ "\n");
    }
}
