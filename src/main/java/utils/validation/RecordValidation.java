package utils.validation;

import models.entities.Record;
import services.exceptions.CustomGenericException;
import utils.constants.ServerResponseConstants;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RecordValidation {



    private static boolean isMinimumBelowAge(Date dob){
        LocalDate birthday = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        long daysSinceBirth = now.toEpochDay() - birthday.toEpochDay();
        int age = (int) (daysSinceBirth/365);
        return age >= 16;
    }

    private static boolean isDateInPast(Date dob){
        return dob.before(new Date());
    }

    public static void validateDateOfBirth(Date dob) throws CustomGenericException {
        if (!isMinimumBelowAge(dob)){
            throw new CustomGenericException(ServerResponseConstants.INVALID_AGE_CODE,ServerResponseConstants.INVALID_AGE_TEXT);
        }

        if (!isDateInPast(dob)){
            throw new CustomGenericException(ServerResponseConstants.INVALID_DATE_OF_BIRTH_CODE,ServerResponseConstants.INVALID_DATE_OF_BIRTH_TEXT);
        }
    }

    private static boolean isNameTooLong(String name){
        if (name.length() >25){
            return true;
        }
        return false;
    }

    private static void validateName(String name) {
        if(isNameTooLong(name)){
            throw new CustomGenericException(ServerResponseConstants.INVALID_NAME_CODE,ServerResponseConstants.INVALID_NAME_TEXT);
        }
    }

    private static boolean invalidMobilePrefix(String mobileNumber) {
        if (mobileNumber.substring(0,2).equals("08")){
            return true;
        }else {
            return false;
        }
    }

    private static void validateMobileNumber(String mobileNumber) {
        if (invalidMobilePrefix(mobileNumber)){
            throw new CustomGenericException(ServerResponseConstants.INVALID_MOBILE_CODE,ServerResponseConstants.INVALID_MOBILE_TEXT);
        }
    }

    public static void validateRecord(Record record) {
        validateName(record.getName());
        validateDateOfBirth(record.getDateOfBirth());
        if(record.getMobileNumber() != null && !record.getMobileNumber().isEmpty()) {
            validateMobileNumber(record.getMobileNumber());
        }
    }

}
