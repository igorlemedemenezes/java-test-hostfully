package hostfully.technical.interview.service;

import hostfully.technical.interview.exceptions.EntityNotFound;
import hostfully.technical.interview.exceptions.ForbiddenException;
import hostfully.technical.interview.model.Block;
import hostfully.technical.interview.model.Booking;
import hostfully.technical.interview.repository.BlockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BlockService {

    @Autowired
    private BlockRepository repo;

    @Autowired
    private UserService userService;

    public void deleteBlock(long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Block updateBlock(long id, Block block) {
        if (repo.existsById(id)) {
            block.setId(id);
            return repo.save(block);
        }
        throw new EntityNotFound();
    }

    @Transactional
    public Block createBlock(Long userId, Block block) {
        if (userService.isUserAdmin(userId)){
            repo.save(block);
        }
        throw new ForbiddenException();
    }

    public boolean hasConflicts(Booking booking) {
        final List<Block> allBlocks = repo.findAll();
        return allBlocks.stream()
                .filter(block -> block.getProperty().equals(booking.getProperty()))
                .anyMatch(block -> isDateRangeOverlapping(
                        booking.getStartDate(), booking.getEndDate(), block.getStartDate(), block.getEndDate()));
    }

    public boolean isDateRangeOverlapping(LocalDateTime startBooking, LocalDateTime endBooking, LocalDateTime startBlock, LocalDateTime endBlock) {
        return startBooking.isBefore(startBlock) && endBooking.isAfter(endBlock);
    }
}
