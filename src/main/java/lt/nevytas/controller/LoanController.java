package lt.nevytas.controller;

import lt.nevytas.entity.Loan;
import lt.nevytas.entity.User;
import lt.nevytas.exceptions.RiskyLoanTimeException;
import lt.nevytas.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by nevyt
 */

//TODO Exception handling && Get request IP adress, and limit requests [3] for one IP
@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;


    @RequestMapping(method = RequestMethod.POST, value = "/users/{id}/loans")
    public ResponseEntity<Object> addLoan(@RequestBody(required = false) Loan loan, @PathVariable long id, HttpServletRequest httpServletRequest) {
        loan.setUser(new User(id, "", "", "", "", "", ""));
        loan.setIpAddress(loanService.getClientIp(httpServletRequest));
        if (loan.getTimeIssued() >= 0 && loan.getTimeIssued() <= 5) {
            ResponseEntity<Object> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            Assert.state(badRequest.hasBody(), "Request denied. Cannot process quick loan between 00:00 and 06:00");
            System.out.println("Request denied. Cannot process quick loan between 00:00 and 06:00");
            return badRequest;
        } else if (loanService.getUserLoanCountPerDay(id, loan.getIpAddress()) >= 3) {
            ResponseEntity<Object> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            Assert.state(badRequest.hasBody(), "Can't process your request. Daily loan limit reached. [3]");
            System.out.println("Can't process your request. Daily loan limit reached. [3]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            loanService.save(loan);
        }
        return null;
    }

    @ExceptionHandler(RiskyLoanTimeException.class)
    public ResponseEntity handleRiskyLoanTimeException(RiskyLoanTimeException ex) {
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}/loans")
    public List<Loan> getAllLoans(@PathVariable long id) {
        return loanService.getAllLoansByUserId(id);

    }


}
