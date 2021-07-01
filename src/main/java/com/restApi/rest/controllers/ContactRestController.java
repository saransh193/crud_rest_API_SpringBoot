package com.restApi.rest.controllers;

import com.google.gson.Gson;
import com.restApi.rest.beans.ContactBean;
import com.restApi.rest.beans.CustomResponse;
import com.restApi.rest.exceptionConfig.ContactNotFoundException;
import com.restApi.rest.service.ContactRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class ContactRestController {

    @Autowired
    private ContactRestService contactRestService;

    @GetMapping(value = "/contacts")
    public ResponseEntity<List<ContactBean>> getAllContacts() throws Exception {
            List<ContactBean> contacts = contactRestService.getContactDetails();
            if (contacts.isEmpty()) {
                throw new ContactNotFoundException("No contact.");
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<String> saveContactIntoDB(@Valid @RequestBody ContactBean contact) throws Exception {
        contactRestService.saveContactDetails(contact);
        CustomResponse customResponse = new CustomResponse( "success", HttpStatus.OK.value(), "requested data is saved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(customResponse));
    }

    @PutMapping(value = "/contacts/{id}")
    public ResponseEntity<String> updateContactIntoDB(@Valid @RequestBody ContactBean contact, @PathVariable int id) throws Exception {
        contactRestService.updateContactDetails(contact, id);
        CustomResponse customResponse = new CustomResponse( "success", HttpStatus.OK.value(), "requested data is updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(customResponse));
    }
}
