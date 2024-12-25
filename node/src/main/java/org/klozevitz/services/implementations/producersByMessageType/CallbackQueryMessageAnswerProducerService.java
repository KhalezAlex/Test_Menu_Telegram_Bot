package org.klozevitz.services.implementations.producersByMessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.klozevitz.enitites.appUsers.AppUser;
import org.klozevitz.services.enums.callbackServiceCommands.EmployeeCallBackServiceCommands;
import org.klozevitz.services.interfaces.base.AnswerProducer;
import org.klozevitz.services.interfaces.producersByMessageType.CallbackQueryMessageAnswerProducer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.klozevitz.employeeViews.ErrorViews.unknownCallbackServiceCommandErrorView;
import static org.klozevitz.employeeViews.ErrorViews.userSelfRegistrationErrorView;
import static org.klozevitz.services.enums.callbackServiceCommands.EmployeeCallBackServiceCommands.REGISTER_EMPLOYEE;

@Log4j
@Service
@RequiredArgsConstructor
public class CallbackQueryMessageAnswerProducerService implements CallbackQueryMessageAnswerProducer {
    private final AnswerProducer answerProducer;

    @Override
    public void produce(Update update, AppUser currentUser) {
        var answer = distributeCallbackByAppUserRole(update, currentUser);
        answerProducer.produceAnswer(answer);
    }

    private SendMessage distributeCallbackByAppUserRole(Update update, AppUser currentUser) {
        var serviceCommand = update.getCallbackQuery().getData();
        if (!EmployeeCallBackServiceCommands.hasCommand(serviceCommand)) {
            log.error(String.format(
                    "По какой-то причине, пользователь получил доступ к команде, " +
                            "которой нет в списке доступных команд:\n%s",
                    serviceCommand)
            );
            return unknownCallbackServiceCommandErrorView(update);
        }
        if (currentUser.getCompany() != null) {
            return distributeCompanyCallbackByServiceCommand(update);
        } else if (currentUser.getDepartment() != null) {
            return distributeDepartmentCallbackByServiceCommand(update);
        } else if (currentUser.getEmployee() != null) {
            return distributeEmployeeCallbackByServiceCommand(update);
        } else if (currentUser.getAdmin() != null) {
            return distributeAdminCallbackByServiceCommand(update);
        } else {
            return distributeUnregisteredUserCallbackByServiceCommand(update);
        }
    }

    private SendMessage distributeUnregisteredUserCallbackByServiceCommand(Update update) {
        var serviceCommand = update.getCallbackQuery().getData();
        if (REGISTER_EMPLOYEE.equals(serviceCommand)) {
            return userSelfRegistrationErrorView(update);
        }
        return null;
    }

    private SendMessage distributeAdminCallbackByServiceCommand(Update update) {
        return null;
    }

    private SendMessage distributeEmployeeCallbackByServiceCommand(Update update) {

        return null;
    }

    private SendMessage distributeDepartmentCallbackByServiceCommand(Update update) {
        return null;
    }

    private SendMessage distributeCompanyCallbackByServiceCommand(Update update) {
        return null;
    }
}
