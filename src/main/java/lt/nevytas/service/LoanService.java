package lt.nevytas.service;

import lt.nevytas.entity.Loan;
import lt.nevytas.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nevyt
 */
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Transactional
    public Loan save(Loan loan) {
        loanRepository.save(loan);
        return loan;
    }

    public List<Loan> getAllLoansByUserId(long id) {
        List<Loan> loans = new ArrayList<>();
        loanRepository.findByUserId(id).forEach(loans::add);
        return loans;
    }

    public int getUserLoanCountPerDay(long id, String ip){
        List<Loan> loans = new ArrayList<>();
        loanRepository.findByUserId(id).stream()
                .filter(l ->l.getDayIssued()==Calendar.getInstance().get(Calendar.DAY_OF_YEAR) && l.getIpAddress().equals(ip)).forEach(loans::add);
        return loans.size();
    }

    public String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
