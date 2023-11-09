package hostfully.technical.interview.resources;

import hostfully.technical.interview.model.Block;
import hostfully.technical.interview.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/block")
public class BlockResource {

    @Autowired
    private BlockService blockService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Block> createBlock(@RequestParam Long userId, @RequestBody Block Blocking) {
        Block createdBlocking = blockService.createBlock(userId, Blocking);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBlocking.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBlocking);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Block> updateBlock(@PathVariable long id, @RequestBody Block Blocking) {
        Block updatedBlocking = blockService.updateBlock(id, Blocking);
        return new ResponseEntity<>(updatedBlocking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable long id) {
        blockService.deleteBlock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
