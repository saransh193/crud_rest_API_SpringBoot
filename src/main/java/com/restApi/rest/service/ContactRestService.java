package com.restApi.rest.service;

import com.restApi.rest.beans.ContactBean;
import com.restApi.rest.dao.ContactRestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactRestService {

    @Autowired
    private ContactRestDao contactRestDao;

    public List<ContactBean> getContactDetails() throws Exception {
        return contactRestDao.getContactDetailsFromDB();
    }

    public boolean saveContactDetails(ContactBean contact) throws Exception {
         return contactRestDao.saveContactDetailsInDB(contact);
    }

    public boolean updateContactDetails(ContactBean contact, int id) throws Exception {
         return contactRestDao.updateContactDetailsInDB(contact, id);
    }
}
