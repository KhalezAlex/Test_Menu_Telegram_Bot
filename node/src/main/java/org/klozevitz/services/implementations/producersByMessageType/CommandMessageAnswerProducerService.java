package org.klozevitz.services.implementations.producersByMessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.commonViews.WelcomePage;
import org.klozevitz.enitites.appUsers.AppUser;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.CommandMessageAnswerProducer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.commonViews.WelcomePage.*;
import static org.klozevitz.services.enums.CommonServiceCommands.START;

@Log4j
@Service
@RequiredArgsConstructor
public class CommandMessageAnswerProducerService implements CommandMessageAnswerProducer {
    private final AnswerProducer answerProducer;

    @Override
    public void produce(Update update, AppUser currentUser) {
        distributeByServiceCommand(update, currentUser);
    }

    private void distributeByServiceCommand(Update update, AppUser currentUser) {
        var command = update.getMessage().getText();
        if (START.equals(command)) {
            var answer = distributeWelcomePageAnswerByAppUserRole(update, currentUser);
            answerProducer.produceAnswer(answer);
        }
    }

    private SendMessage distributeWelcomePageAnswerByAppUserRole(Update update, AppUser currentUser) {
        if (currentUser.getCompany() != null) {
            return companyWelcomePage(update);
        } else if (currentUser.getDepartment() != null) {
            return departmentWelcomePage(update);
        } else if (currentUser.getEmployee() != null) {
            return employeeWelcomePage(update);
        } else if (currentUser.getAdmin() != null) {
            return adminWelcomePage(update);
        } else {
            return unregisteredUsersWelcomePage(update);
        }
    }
}
