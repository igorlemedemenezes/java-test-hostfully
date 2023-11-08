package hostfully.technical.interview.service;

import hostfully.technical.interview.dto.BookDTO;
import hostfully.technical.interview.model.Book;
import hostfully.technical.interview.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    @Transactional
    public Book createBooking(Book booking) {
        return repo.save(booking);
    }

    public Book updateBooking(long id, Book booking) {
        if (repo.existsById(id)) {
            booking.setId(id);
            return repo.save(booking);
        }
        //Refactore to handle with controller handler.
        return null;
    }

    public void deleteBooking(long id) {
        repo.deleteById(id);
    }
}
