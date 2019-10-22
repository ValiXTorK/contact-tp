package fr.lp.ic.contact;

import fr.lp.ic.contact.dao.IContactDao;
import fr.lp.ic.contact.exception.ContactException;
import fr.lp.ic.contact.exception.ContactNotFoundException;
import fr.lp.ic.contact.model.Contact;
import org.easymock.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.Optional;

public class ContactServiceMockTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    ContactService contactService = new ContactService();

    @Mock
    IContactDao contactDao;

    @Test(expected = ContactException.class)
    public void shouldFailIfNameAlreadyExists() throws ContactException {
        String name = "Anthony";
        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.of(new Contact()));

        // Replay
        EasyMock.replay(contactDao);

        // Test
        contactService.newContact(name, "0225451512", "test@yopmail.com");
    }

    @Test
    public void shouldInsert() throws ContactException {
        String name = "Anthony";
        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());
        Capture<Contact> captured = EasyMock.newCapture();
        EasyMock.expect(contactDao.save(EasyMock.capture(captured))).andReturn(true);
        // Replay
        EasyMock.replay(contactDao);

        // Test
        contactService.newContact(name, "0225451512", "test@yopmail.com");

        Contact value = captured.getValue();
        Assert.assertEquals(name.toUpperCase(), value.getName());
    }

    @Test(expected = ContactNotFoundException.class)
    public void shouldFailDeletionIfNameDoesntExist() throws ContactNotFoundException {
        String name = "Anthony";
        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());

        // Replay
        EasyMock.replay(contactDao);

        // Test
        contactService.deleteContact(name);

//        Contact value = captured.getValue();
//        Assert.assertEquals(name.toUpperCase(), value.getName());
    }

    @Test
    public void shouldDeleteIfNameExists() throws ContactNotFoundException {
        String name = "Arnaud";

        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.of(new Contact()));
        EasyMock.expect(contactDao.delete(name)).andReturn(true);

        //Replay
        EasyMock.replay(contactDao);

        //Test
        contactService.deleteContact(name);
    }

    @Test(expected = ContactNotFoundException.class)
    public void shouldFailIfNameDoesntExist() throws ContactNotFoundException, ContactException {
        String name = "Anthony";

        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.empty());

        //Replay
        EasyMock.replay(contactDao);

        //Test
        contactService.updateContact(name, name, "0214545478", "test1@yopmail.com");
    }

    @Test(expected = ContactException.class)
    public void shouldFailIfNameExistsButNewNameIsConflicting() throws ContactNotFoundException, ContactException {
        String name = "Anthony";
        String newName = "Arnaud";

        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.of(new Contact()));
        EasyMock.expect(contactDao.findByName(newName)).andReturn(Optional.of(new Contact()));

        //Replay
        EasyMock.replay(contactDao);
        ////EasyMock.replay(contactDao.update(EasyMock.eq(name), EasyMock.anyObject(Contact.class)));

        //Test
        contactService.updateContact(name, newName, "0214545478", "test1@yopmail.com");
    }

    @Test
    public void shouldUpdateContact() throws ContactNotFoundException, ContactException {
        String name = "Anthony";
        String newName = "Arnaud";

        //Enregistrement des comportements
        EasyMock.expect(contactDao.findByName(name)).andReturn(Optional.of(new Contact()));
        Capture<Contact> captured = EasyMock.newCapture();
        EasyMock.expect(contactDao.findByName(newName)).andReturn(Optional.empty());
        EasyMock.expect(contactDao.update(EasyMock.eq(name), EasyMock.capture(captured))).andReturn(false);
        //Replay
        EasyMock.replay(contactDao);
        ////EasyMock.replay(contactDao.update(EasyMock.eq(name), EasyMock.anyObject(Contact.class)));

        //Test
        contactService.updateContact(name, newName, "0214545478", "test1@yopmail.com");
    }


}
