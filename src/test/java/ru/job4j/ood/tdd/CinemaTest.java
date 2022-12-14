package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Disabled
public class CinemaTest {

    /**
     * ???????? ? ????????? ??? ? ??????? ??? ??
     */
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    /**
     * ????????? ????? ? ?????????, ??? ?? ???? ????? ?????? ???????
     */
    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(session1 -> true);
        assertThat(sessions.contains(session));
    }

    /**
     * ?????????, ??? ?????? ?????? ????? ?? ?????????????? ?????
     */
    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, -1, date);
        });
    }

    /**
     * ?????????, ??? ?????? ?????? ????? ?? ?????????????? ????
     */
    @Test
    public void whenBuyTicketOnInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(2017, Calendar.JANUARY, 32);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 2, 3, date);
        });
    }

    /**
     * ?????????, ??? ?????? ?????? ????? ?? ????? ??????? ??????
     */
    @Test
    public void whenBuyButTicketButPlaceIsTaken() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket soldTicket = cinema.buy(account, 1, 2, date);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 2, date);
                });
    }
}