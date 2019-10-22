package fr.lp.ic.contact;

import fr.lp.ic.contact.validators.PhoneValidatorUtils;
import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorUtilsTest {

    @Test
    public void shouldValidateEmailValidator(){
        Assert.assertTrue(PhoneValidatorUtils.validate("test@test.com"));
        Assert.assertFalse(PhoneValidatorUtils.validate(""));
        Assert.assertFalse(PhoneValidatorUtils.validate("testtest.com"));
    }
}
