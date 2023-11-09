package hostfully.technical.interview.resources;

import hostfully.technical.interview.model.Block;
import hostfully.technical.interview.service.BlockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlockResourceTest {

    @Mock
    private BlockService blockService;

    @InjectMocks
    private BlockResource blockResource;

    @Test
    void updateBlock() {
        long id = 1L;
        Block blocking = new Block();

        when(blockService.updateBlock(id, blocking)).thenReturn(blocking);
        ResponseEntity<Block> response = blockResource.updateBlock(id, blocking);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(blockService, times(1)).updateBlock(id, blocking);
    }

    @Test
    void deleteBlock() {
        long id = 1L;

        ResponseEntity<Void> response = blockResource.deleteBlock(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(blockService, times(1)).deleteBlock(id);
    }
}



