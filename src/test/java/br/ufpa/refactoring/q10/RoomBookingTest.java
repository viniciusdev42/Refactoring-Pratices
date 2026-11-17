package br.ufpa.refactoring.q10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomBookingTest {

    @Test
    void quote_examples() {
        RoomBooking rb = new RoomBooking();

        assertEquals(100.0, rb.quote("B", 1, "101", 8, 10, false, false, 10), 0.001);
        assertEquals((2*50.0 + 30 + 20) * 1.15 + 10, rb.quote("A", 3, "101", 8, 10, true, true, 50), 0.01);
        assertEquals((2*50.0) + 40, rb.quote("B", 1, "LAB", 8, 10, false, false, 10), 0.01);
    }

    @Test
    void refactorGoal_introduceParameterObject() {
        // TODO: introduzir BookingRequest/Room/Schedule e reduzir parâmetros primitivos.
        // Ajuste as chamadas acima se necessário, mantendo os valores esperados.
    }
}
