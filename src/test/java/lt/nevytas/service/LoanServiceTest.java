package lt.nevytas.service;

import lt.nevytas.entity.Loan;
import lt.nevytas.entity.User;
import lt.nevytas.repository.LoanRepository;
import lt.nevytas.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by nevyt on 17-Oct-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;


    @Test
    public void saveEntityMethodSavesLoanEntities() throws Exception {
        Loan testLoan1 = Mockito.mock(Loan.class);
        Loan testLoan2 = Mockito.mock(Loan.class);
        loanRepository.save(testLoan1);
        loanRepository.save(testLoan2);

        List<Loan> LoanList = new LinkedList<>();
        List spy = Mockito.spy(LoanList);
        spy.add(testLoan1);
        spy.add(testLoan2);

        Mockito.verify(spy).add(testLoan1);
        Mockito.verify(spy).add(testLoan2);
        assertEquals(2, spy.size());
        when(loanRepository.save(testLoan1)).thenReturn(testLoan1);
        when(loanRepository.save(testLoan2)).thenReturn(testLoan2);
        Mockito.verify(loanRepository).save(testLoan1);
        assertEquals(loanRepository.save(testLoan1), testLoan1);
        Mockito.verify(loanRepository).save(testLoan2);
        assertEquals(loanRepository.save(testLoan2), testLoan2);
    }

    @Test
    public void getLoanWithInvalidUserIdReturnsNull() throws Exception {
        User testUser1 = Mockito.mock(User.class);
        testUser1.setId(1L);
        Loan testLoan1 = Mockito.mock(Loan.class);
        userRepository.save(testUser1);
        testLoan1.setUser(testUser1);
        loanRepository.save(testLoan1);
        loanService.getAllLoansByUserId(123L);
        when(loanService.getAllLoansByUserId(123L)).thenReturn(null);
        Mockito.verify(loanService).getAllLoansByUserId(123L);
    }



}