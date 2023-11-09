package hostfully.technical.interview.services;

import hostfully.technical.interview.exceptions.EntityNotFound;
import hostfully.technical.interview.model.Block;
import hostfully.technical.interview.repository.BlockRepository;
import hostfully.technical.interview.service.BlockService;
import hostfully.technical.interview.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BlockServiceTests {

    @Mock
    private BlockRepository blockRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private BlockService blockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(blockService, "userService", userService);
    }

    @Test
    void deleteBlockShouldDeleteBlock() {
        blockService.deleteBlock(1L);
        verify(blockRepository).deleteById(eq(1L));
    }

    @Test
    void updateBlockShouldUpdateBlock() {
        Block block = new Block();
        block.setId(1L);

        when(blockRepository.existsById(eq(1L))).thenReturn(true);
        when(blockRepository.save(any(Block.class))).thenReturn(block);

        Block updatedBlock = blockService.updateBlock(1L, block);

        assertEquals(block, updatedBlock);
    }

    @Test
    void updateBlockShouldThrowEntityNotFound() {
        when(blockRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFound.class, () -> blockService.updateBlock(1L, new Block()));
    }

    @Test
    void isDateRangeOverlappingShouldReturnFalse() {
        LocalDateTime startBooking = LocalDateTime.now();
        LocalDateTime endBooking = LocalDateTime.now().plusHours(2);

        LocalDateTime startBlock = LocalDateTime.now().plusHours(3);
        LocalDateTime endBlock = LocalDateTime.now().plusHours(4);

        boolean overlapping = blockService.isDateRangeOverlapping(
                startBooking, endBooking, startBlock, endBlock);

        assertFalse(overlapping);
    }
}