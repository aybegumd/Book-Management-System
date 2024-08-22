package com.project.booksapp.controller;

import com.project.booksapp.entity.Publisher;
import com.project.booksapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publisherList = publisherService.getAllPublishers();
        if (publisherList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publisherList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Publisher>> getPublisherById(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherService.getPublisherById(id);
        if (!publisher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
        Publisher savedPublisher = publisherService.savePublisher(publisher);
        return new ResponseEntity<>(savedPublisher, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisherById(@PathVariable Long id, @RequestBody Publisher publisher) {
        Optional<Publisher> publisherData = publisherService.getPublisherById(id);

        if (publisherData.isPresent()) {
            Publisher updatedPublisherData = publisherData.get();
            updatedPublisherData.setName(publisher.getName());

            Publisher savedPublisher = publisherService.savePublisher(updatedPublisherData);
            return new ResponseEntity<>(savedPublisher, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        Optional<Publisher> publisher = publisherService.getPublisherById(id);

        if (!publisher.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        publisherService.deletePublisherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

