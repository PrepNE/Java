package com.mikepn.bankingsystem.v1.utils.helpers;

import com.mikepn.bankingsystem.v1.enums.EGender;
import com.mikepn.bankingsystem.v1.exceptions.BadRequestException;
import com.mikepn.bankingsystem.v1.models.User;
import com.mikepn.bankingsystem.v1.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class Helper {

    private static IUserService userService;

    @Autowired
    public Helper(
            IUserService userService){
        Helper.userService = userService;
    }

    public User getLoggedInUser(HttpServletRequest request){
        return  null;
    }

    public static String getActor(){
        return "Done at : " + LocalDateTime.now() +  " By Email : " +  userService.getLoggedInUser().getEmail();
    }
    public static void logAction(String message){
        log.info(message);
        log.info(getActor());
    }
    public static EGender getGender(String gender){
        System.out.println("Gender : " + gender);
        switch (gender.toUpperCase()){
            case "MALE", "M":
                return EGender.MALE;
            case "FEMALE", "F":
                return EGender.FEMALE;
            case "OTHER":
                return  EGender.OTHER;
            default:
                throw new BadRequestException("The provided gender is invalid");
        }
    }

}
