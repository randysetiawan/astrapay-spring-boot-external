package com.astrapay.controller;

import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "NoteController")
@Slf4j
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    @ApiOperation(value = "Get all Notes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Note.class, responseContainer = "List")
    })
    public ResponseEntity<List<Note>> getAll() {
        log.info("Getting all notes");
        return ResponseEntity.ok(noteService.getAll());
    }

    @PostMapping("/notes")
    @ApiOperation(value = "Create a new Note")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Note.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<Note> create(@Valid @RequestBody Note note) {
        log.info("Creating new note with title: " +note.getNoteTitle());
        return ResponseEntity.ok(noteService.create(note));
    }

    @DeleteMapping("/notes/{id}")
    @ApiOperation(value = "Delete a Note")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting note with id: " +id);
        boolean deleted = noteService.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
