package fr.lp.ic.contact;

import fr.lp.ic.contact.validators.PhoneValidatorUtils;
import org.junit.Assert;
import org.junit.Test;

public class PhoneValidatorUtilsTest {

    @Test
    public void shouldValidatePhoneValidator(){
        Assert.assertTrue(PhoneValidatorUtils.validate("0214151214"));
        Assert.assertFalse(PhoneValidatorUtils.validate(""));
        Assert.assertFalse(PhoneValidatorUtils.validate("0645121212"));
    }
}
