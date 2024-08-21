package com.project.booksapp.controller;

import com.project.booksapp.entity.Publisher;
import com.project.booksapp.repository.PublisherRepository;
import com.project.booksapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("getAllPublishers")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publisherList = publisherRepository.findAll();
        if (publisherList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(publisherList, HttpStatus.OK);
    }

    @GetMapping("/getPublisherById/{id}")
    public ResponseEntity<Optional<Publisher>> getPublisherById(@PathVariable Long id) {
        Optional<Publisher> publisherData = publisherRepository.findById(id);
        return publisherData.isPresent() ? new ResponseEntity<>(publisherData, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/savePublisher")
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
        Publisher savedPublisher = publisherService.savePublisher(publisher);
        return new ResponseEntity<>(savedPublisher, HttpStatus.OK);
    }

    @PutMapping("/updatePublisherById/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        Optional<Publisher> publisherData = publisherRepository.findById(id);

        if (publisherData.isPresent()) {
            Publisher updatedPublisherData = publisherData.get();
            updatedPublisherData.setName(publisher.getName());

            Publisher savedPublisher = publisherRepository.save(updatedPublisherData);
            return new ResponseEntity<>(savedPublisher, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePublisherById/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        if (!publisherService.getPublisherById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

