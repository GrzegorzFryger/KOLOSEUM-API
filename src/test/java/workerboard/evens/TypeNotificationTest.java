package workerboard.evens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TypeNotificationTest {



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getMessage() {

        Assert.assertEquals( TypeNotification.POINT_FOR_TASK.getMessage(), "received experience points for completing the task");
    }
}