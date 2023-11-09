package hostfully.technical.interview.resources;
import hostfully.technical.interview.dto.BookingDTO;
import hostfully.technical.interview.model.Booking;
import hostfully.technical.interview.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingResource.class)
class BookingResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    void getAllBookingsShouldReturnEmptyList() throws Exception {
        when(bookingService.getAllBooks()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllBookingsShouldReturnListOfBookings() throws Exception {
        // Mocking the service to return a list with one booking
        when(bookingService.getAllBooks()).thenReturn(Collections.singletonList(new BookingDTO()));

        mockMvc.perform(MockMvcRequestBuilders.get("/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void createBookingShouldReturnCreated() throws Exception {
        Booking booking = new Booking();
        when(bookingService.createBook(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(MockMvcRequestBuilders.post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBookingShouldReturnOk() throws Exception {
        when(bookingService.updateBook(any(Long.class), any(BookingDTO.class))).thenReturn(new BookingDTO());

        mockMvc.perform(MockMvcRequestBuilders.put("/booking/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBookingShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/booking/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}