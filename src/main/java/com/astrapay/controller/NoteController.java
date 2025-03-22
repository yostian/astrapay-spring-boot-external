package com.astrapay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
@Slf4j
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
    public ResponseEntity<Map<String, Object>> addNote(@Valid @RequestBody NoteDto noteDto) {
        NoteDto savedNote = noteService.addNote(noteDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Note created successfully");
        response.put("data", savedNote);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a note by ID", notes = "Menghapus catatan berdasarkan ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note deleted successfully"),
            @ApiResponse(code = 404, message = "Note not found")
    })
    public ResponseEntity<Map<String, String>> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);

        if (deleted) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Note deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
