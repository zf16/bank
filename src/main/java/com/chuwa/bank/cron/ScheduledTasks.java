package com.chuwa.bank.cron;


import com.chuwa.bank.dto.AccountDto;
import com.chuwa.bank.dto.StatementDto;
import com.chuwa.bank.service.AccountService;
import com.chuwa.bank.service.StatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {

    private final AccountService accountService;

    private final StatementService statementService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
          "MM/dd/yyyy HH:mm:ss");

    public ScheduledTasks(AccountService accountService, StatementService statementService) {
        this.accountService = accountService;
        this.statementService = statementService;
    }


//    @Scheduled(cron = "*/5 * * * * *")
//    public void performTaskUsingCron() {
//        log.info("Regular task performed using Cron at " + dateFormat.format(new Date()));
//    }


    /**
     * Monthly banking statement fired at the last day of every month
     */
    @Scheduled(cron = "0 0 0 L * ?")
    public void monthlyBankingStatement() {

        // Retrieve all accounts
        List<Long> accountIds = accountService.getAllAccounts().stream()
              .map(AccountDto::getId)
              .toList();

        // generate statement for each account
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDay = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);

        for (Long id : accountIds) {
            log.info("Generating statement for account: {}", id);
            StatementDto statementDto = statementService.getStatement(id, firstDay, currentDate);

            // subsequent logic...

        }

    }

}
