package fr.lp.ic.contact.validators;

import java.util.regex.Pattern;

public final class PhoneValidatorUtils {

    private static final String PHONE_PATTERN = "^02[0-9]{8}$";
    private static final Pattern PHONE_PATTERN_MATCHER = Pattern.compile(PHONE_PATTERN);

    private PhoneValidatorUtils(){

    }


    public static boolean validate(String phone) {
        return PHONE_PATTERN_MATCHER.matcher(phone).matches();
    }
}

