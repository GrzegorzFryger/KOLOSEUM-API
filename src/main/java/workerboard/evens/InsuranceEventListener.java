package workerboard.evens;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InsuranceEventListener {


    @Async
    @EventListener()
    public void onApplicationEvent(InsuranceEvent insuranceEvent) {


        System.out.print(insuranceEvent.getInsurance().getPersons().toString()+ "\n");
    }
}
