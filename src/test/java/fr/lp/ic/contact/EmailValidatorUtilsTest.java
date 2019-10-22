package fr.lp.ic.contact;

import fr.lp.ic.contact.validators.EmailValidatorUtils;
import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorUtilsTest {

    @Test
    public void shouldValidateEmailValidator(){
        Assert.assertTrue(EmailValidatorUtils.validate("test@test.com"));
        Assert.assertFalse(EmailValidatorUtils.validate(""));
        Assert.assertFalse(EmailValidatorUtils.validate("testtest.com"));
    }
}
