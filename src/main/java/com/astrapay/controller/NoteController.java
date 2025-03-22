package com.astrapay.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astrapay.dto.NoteDto;
import com.astrapay.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@Slf4j
@Api(value = "NotesController")
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    @ApiOperation(value = "Get list of notes", notes = "Mengambil semua catatan yang tersimpan di memori")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    })
    public ResponseEntity<List<NoteDto>> listNotes() {
        log.info("Incoming Request : Get list of all notes");
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    @ApiOperation(value = "Add a new note", notes = "Menambahkan catatan baru ke dalam memori")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Note created successfully"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<NoteDto> addNote(@Valid @RequestBody NoteDto noteDto) {
        NoteDto savedNote = noteService.addNote(noteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a note by ID", notes = "Menghapus catatan berdasarkan ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note deleted successfully"),
            @ApiResponse(code = 404, message = "Note not found")
    })
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
