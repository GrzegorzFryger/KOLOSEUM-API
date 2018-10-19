package workerboard.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workerboard.serivce.RegistrationUserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ApplicationUserRegistration.class, secure = false)
public class ApplicationUserRegistrationTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationUserService userService;

    String exampleJson = "{\"registrationName\":\"testname\",\"registrationSurname\":" + "\"testsurname\"," +
            "\"registrationEmail\": \"testemail\", \"registrationPassword\": \"passworde\"}";

//    String wrongExampleJson = "{\"registrationName\":\"\",\"registrationSurname\":" + "\"\"," +
//            "\"registrationEmail\": \"\", \"registrationPassword\": \"pas\"}";
//

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void givenCode202_whenApplicationUserRegistrationReceiveCorrectData() throws Exception {

        //when
        Mockito.when(userService.registrationNewUser(any())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/registration")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        //then
        assertEquals(202, response.getStatus());

    }

    @Test
    public void givenCode400_whenApplicationUserRegistrationReceiveWrongData() throws Exception {

        //when
        Mockito.when(userService.registrationNewUser(any())).thenReturn(false);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/registration")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        //then
        assertEquals(406, response.getStatus());

    }


}