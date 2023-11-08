package hostfully.technical.interview.resources;

import hostfully.technical.interview.model.Book;
import hostfully.technical.interview.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBooking(@RequestBody Book booking) {
        Book createdBooking = bookService.createBooking(booking);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBooking.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBooking(@PathVariable long id, @RequestBody Book booking) {
        Book updatedBooking = bookService.updateBooking(id, booking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable long id) {
        bookService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
