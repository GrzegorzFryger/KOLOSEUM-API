package workerboard.serivce;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUser;
import workerboard.repository.ApplicationUserRepository;
import workerboard.serivce.mapper.UserRegistrationMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class RegistrationUserServiceTest {

    @Mock
    ApplicationUserRepository applicationUserRepository;
//    @Mock
//    BCryptPasswordEncoder passwordEncoder;

    UserRegistrationMapper userRegistrationMapper;
    RegistrationUserService registrationUserService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userRegistrationMapper = UserRegistrationMapper.INSTANCE;


//        this.registrationUserService = new RegistrationUserService(userRegistrationMapper,
//                applicationUserRepository,passwordEncoder);
//

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registrationNewUser() {

        RegistrationUser regUser= new RegistrationUser();
        regUser.setRegistrationName("Greg");
        regUser.setRegistrationSurname("testSurname");
        regUser.setRegistrationEmail("testEmail");

        ApplicationUser user = new ApplicationUser();

        user.setFirstName(regUser.getRegistrationName());
        user.setLastName(regUser.getRegistrationSurname());
        //user.setPassword(regUser.getRegistrationPassword());
        user.setEmail(regUser.getRegistrationEmail());




        Mockito.when(applicationUserRepository.save(any(ApplicationUser.class))).thenReturn(user);

        //when
        boolean returnValue = registrationUserService.registrationNewUser(regUser);

        //then

        assertEquals(true,returnValue);


    }

    @Test
    public void givenFalse_whenRegistrationNewUserReceiveNull() {

        //when
        boolean returnValue = registrationUserService.registrationNewUser(null);

        //then
        assertEquals(false,returnValue);

    }




}