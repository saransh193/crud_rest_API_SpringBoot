package com.restApi.rest.dao;

import com.restApi.rest.beans.ContactBean;
import com.restApi.rest.exceptionConfig.ContactNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class ContactRestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ContactBean> getContactDetailsFromDB() throws Exception {
        String contactDetailsQuery = "SELECT firstname,lastname,phone,email,address From contact_information";
        List<ContactBean> contactList;
        try {
            contactList = jdbcTemplate.query(contactDetailsQuery, new BeanPropertyRowMapper<>(ContactBean.class));
        } catch (Exception ex) {
            log.error("exception in getting data {}", ex.getMessage());
            throw new Exception("exception in getting data ", ex);
        }
        return contactList;
    }

    public boolean saveContactDetailsInDB(ContactBean contactBean) throws Exception {
        String contactDetailsQuery = "INSERT INTO contact_information(firstname,lastname,phone,email,address) values(?,?,?,?,?) ";
        try {
            jdbcTemplate.update(contactDetailsQuery, contactBean.getFirstname(),
                    contactBean.getLastname(), contactBean.getPhone(), contactBean.getEmail(), contactBean.getAddress());
        } catch (Exception ex) {
            log.error("exception in saving data {}", ex.getMessage());
            throw new Exception("exception in saving data ", ex);
        }
        return true;
    }

    public boolean updateContactDetailsInDB(ContactBean contactBean, int id) throws Exception {
        String contactDetailsQuery = "UPDATE contact_information set firstname=?,lastname=?,phone=?,email=?,address=? WHERE id=?";
        int recordUpdated;
        try {
            recordUpdated = jdbcTemplate.update(contactDetailsQuery, contactBean.getFirstname(),
                    contactBean.getLastname(), contactBean.getPhone(), contactBean.getEmail(), contactBean.getAddress(), id);
        }
        catch (Exception ex) {
            log.error("exception in saving data {}" , ex.getMessage());
            throw new Exception("exception in updating contact ", ex);
        }
        if (recordUpdated == 0){
            throw new ContactNotFoundException("contact id : " + id + " is not present");
        }
        return true;
    }
}
