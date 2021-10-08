package com.example.ozon.service;

import com.example.ozon.domain.User;
import com.example.ozon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserScedulerService {

    private UserRepository userRepository;
    private final EmailService emailService;
    private static final String CRON = "0 10 * * * *";

    @Autowired
    public UserScedulerService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    private List<User> getUsersByBirthday(int month, int day) {
        return userRepository.findByMatchMonthAndMatchDay(month, day);
    }

   // @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> list = getUsersByBirthday(month, day);

        if (!list.isEmpty()) {
            list.forEach(user -> {
                String message = "Happy Birthday dear " + user.getFirstName() + "!";
                emailService.send(user.getEMail(), "Happy Birthday!", message);
            });
        }
    }

}
