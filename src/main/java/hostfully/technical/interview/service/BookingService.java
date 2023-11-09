package hostfully.technical.interview.service;

import hostfully.technical.interview.dto.BookingDTO;
import hostfully.technical.interview.exceptions.BadRequestException;
import hostfully.technical.interview.exceptions.EntityNotFound;
import hostfully.technical.interview.model.Booking;
import hostfully.technical.interview.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    @Autowired
    private BlockService blockService;

    @Autowired
    private UserService userService;

    @Transactional
    public Booking createBook(Booking booking) {
        if (!blockService.hasConflicts(booking)) {
            return repo.save(booking);
        }
        throw new BadRequestException("The booking conflicts with an existing block.");
    }

    public BookingDTO updateBook(Long id, BookingDTO booking) {
        final Booking bookingFound = repo.getReferenceById(id);
        if (blockService.hasConflicts(bookingFound)) {
            throw new BadRequestException("The booking conflicts with an existing block.");
        }
        bookingFound.setStartDate(booking.getStartDate());
        bookingFound.setEndDate(booking.getEndDate());
        bookingFound.setBookingStatus(booking.getBookingStatus());
        bookingFound.setUser(userService.getUserById(booking.getUserId()));
        repo.save(bookingFound);
        return booking;
    }

    public void deleteBook(long id) {
        repo.deleteById(id);
    }

    public List<BookingDTO> getAllBooks() {
        List<Booking> books = repo.findAll();
        return books.stream()
                .map(book -> new BookingDTO(book.getStartDate(), book.getEndDate(), book.getUser().getId(), book.getBookingStatus()))
                .collect(Collectors.toList());
    }
}
