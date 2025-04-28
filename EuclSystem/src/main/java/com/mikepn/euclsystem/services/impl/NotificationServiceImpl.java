package com.mikepn.euclsystem.services.impl;

import com.mikepn.euclsystem.enums.ETokenStatus;
import com.mikepn.euclsystem.enums.IEmailTemplate;
import com.mikepn.euclsystem.models.Notification;
import com.mikepn.euclsystem.models.PurchasedToken;
import com.mikepn.euclsystem.repositories.ICustomerRepository;
import com.mikepn.euclsystem.repositories.INotificationRepository;
import com.mikepn.euclsystem.repositories.IPurchasedTokenRepository;
import com.mikepn.euclsystem.services.INotificationService;
import com.mikepn.euclsystem.standalone.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private INotificationRepository notificationRepository;
    private IPurchasedTokenRepository purchasedTokenRepository;
    private ICustomerRepository customerRepository;
    private final EmailService emailService;


    @Override
    public void checkExpiringTokens() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fiveHoursFromNow = now.plusHours(5);

        List<PurchasedToken> tokens = purchasedTokenRepository.findAll()
                .stream()
                .filter(token -> token.getStatus() == ETokenStatus.NEW)
                .filter(token -> token.getExpirationDate() != null)
                .filter(token -> {
                    LocalDateTime exp = token.getExpirationDate();
                    return exp.isAfter(now) && exp.isBefore(fiveHoursFromNow);
                }).toList();


        for (PurchasedToken token : tokens) {
            customerRepository.findByMeters_MeterNumber(token.getMeter()).ifPresent(customer -> {
                String message = String.format(
                        "Dear %s, REG is pleased to remind you that the token in meter %s is going to expire in 5 hours. Please purchase a new token.",
                        customer.getProfile().getLastName(), token.getMeter());

                Notification notification = Notification.builder()
                        .issuedDate(LocalDateTime.now())
                        .meterNumber(token.getMeter())
                        .message(message)
                        .build();

                notificationRepository.save(notification);

                try{
                    Map<String, Object> vars = new HashMap<>();
                    vars.put("message", message);

                    emailService.sendEmail(
                            customer.getProfile().getEmail(),
                            customer.getProfile().getFirstName(),
                            "Your Electricity Token is Expiring",
                            IEmailTemplate.NOTIFICATION,
                            vars
                    );
                }catch (Exception e){
                    throw new RuntimeException("Failed to send notification: " + e.getMessage());
                }
            });


        }

    }
}
