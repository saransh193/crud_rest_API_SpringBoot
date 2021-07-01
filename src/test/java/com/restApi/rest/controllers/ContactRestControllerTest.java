package com.restApi.rest.controllers;

import com.google.gson.Gson;
import com.restApi.rest.beans.ContactBean;
import com.restApi.rest.beans.CustomResponse;
import com.restApi.rest.service.ContactRestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactRestController.class)
public class ContactRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactRestService contactRestService;

    @Test
    public void getAllContacts() throws Exception {
        List<ContactBean> contacts = Arrays.asList(new ContactBean("saransh", "gupta", "abc@gmail.com", "3333", "noida"));
        Mockito.when(contactRestService.getContactDetails()).thenReturn(contacts);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/rest/contacts").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = new Gson().toJson(contacts);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void saveContact() throws Exception {

        ContactBean contact = new ContactBean();
        contact.setFirstname("abc");
        contact.setLastname("xyz");
        contact.setPhone("1234567890");
        contact.setEmail("xyz@gmail.com");
        contact.setAddress("delhi");

        String inputInJson = new Gson().toJson(contact);

        String URI = "/rest/contacts";
        Mockito.when(contactRestService.saveContactDetails(Mockito.any(ContactBean.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        CustomResponse customResponse = new CustomResponse("success", HttpStatus.OK.value(), "requested data is saved successfully");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(new Gson().toJson(customResponse)).isEqualTo(outputInJson);
        
    }
    @Test
    public void updateContact() throws Exception {

        ContactBean contact = new ContactBean();
        contact.setFirstname("abcd");
        contact.setLastname("xyz");
        contact.setPhone("123456");
        contact.setEmail("xyz@gmail.com");
        contact.setAddress("delhi");

        String inputInJson = new Gson().toJson(contact);

        String URI = "/rest/contacts/1";

        Mockito.when(contactRestService.updateContactDetails(Mockito.any(ContactBean.class),Mockito.anyInt())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();
        CustomResponse customResponse = new CustomResponse("success", HttpStatus.OK.value(), "requested data is updated successfully");

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(new Gson().toJson(customResponse)).isEqualTo(outputInJson);

    }
}
