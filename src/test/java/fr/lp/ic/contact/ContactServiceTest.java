package fr.lp.ic.contact;

import org.junit.Test;

import fr.lp.ic.contact.exception.ContactException;


public class ContactServiceTest {

    private static final String VALID_PHONE_NUMBER = "0203040506";
    private static final String VALID_EMAIL = "test@gmail.com";
    private static final String VALID_NAME = "Bobby";


    private ContactService service = new ContactService();

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNameLessThanThree() throws ContactException {
        //Empty test
        service.newContact("ab", VALID_PHONE_NUMBER, VALID_EMAIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNameGreaterThanFourty() throws ContactException {
        //Empty test
        service.newContact("abcdefghijklmnopqrstuvwxyzabcdefghijklmno",
                VALID_PHONE_NUMBER, VALID_EMAIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfNameisNull() throws ContactException {
        //Empty test
        service.newContact(null, VALID_PHONE_NUMBER, VALID_EMAIL);
    }

    @Test(expected = ContactException.class)
    public void shouldFailIfNameExists() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, VALID_PHONE_NUMBER, VALID_EMAIL);
        service.newContact(VALID_NAME, VALID_PHONE_NUMBER, VALID_EMAIL);
    }

    @Test
    public void shouldInsertValidContact() throws ContactException {
        //Empty test
        service.newContact("Anthony", VALID_PHONE_NUMBER, VALID_EMAIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfPhoneNumberDoesntStartBy02() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, "010203040506", VALID_EMAIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfPhoneNumberLengthIsNotTen() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, "02030405060", VALID_EMAIL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfEmailLessThanFive() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, VALID_PHONE_NUMBER, "t@d.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfEmailWithoutArobase() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, VALID_PHONE_NUMBER, "testgmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfEmailWithoutDot() throws ContactException {
        //Empty test
        service.newContact(VALID_NAME, VALID_PHONE_NUMBER, "test@gmailcom");
    }

}
