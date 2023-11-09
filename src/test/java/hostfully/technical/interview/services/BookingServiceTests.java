package hostfully.technical.interview.services;

import hostfully.technical.interview.dto.BookingDTO;
import hostfully.technical.interview.exceptions.BadRequestException;
import hostfully.technical.interview.model.Booking;
import hostfully.technical.interview.repository.BookingRepository;
import hostfully.technical.interview.service.BlockService;
import hostfully.technical.interview.service.BookingService;
import hostfully.technical.interview.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BlockService blockService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(bookingService, "blockService", blockService);
        ReflectionTestUtils.setField(bookingService, "userService", userService);
    }

    @Test
    void createBookShouldCreateBooking() {
        Booking booking = new Booking();
        booking.setStartDate(LocalDateTime.now());
        booking.setEndDate(LocalDateTime.now().plusHours(1));

        when(blockService.hasConflicts(any(Booking.class))).thenReturn(false);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBook(booking);

        assertEquals(booking, createdBooking);
    }

    @Test
    void createBookShouldThrowBadRequestException() {
        Booking booking = new Booking();

        when(blockService.hasConflicts(any(Booking.class))).thenReturn(true);

        assertThrows(BadRequestException.class, () -> bookingService.createBook(booking));
    }

    @Test
    void updateBookShouldUpdateBooking() {
        BookingDTO booking = new BookingDTO();
        booking.setStartDate(LocalDateTime.now());
        booking.setEndDate(LocalDateTime.now().plusHours(1));

        Booking bookingFound = new Booking();
        bookingFound.setStartDate(LocalDateTime.now());
        bookingFound.setEndDate(LocalDateTime.now().plusHours(2));

        when(bookingRepository.getReferenceById(anyLong())).thenReturn(bookingFound);
        when(blockService.hasConflicts(any(Booking.class))).thenReturn(false);
        when(bookingRepository.save(any(Booking.class))).thenReturn(bookingFound);

        BookingDTO updatedBooking = bookingService.updateBook(1L, booking);

        assertEquals(booking, updatedBooking);
    }
}
