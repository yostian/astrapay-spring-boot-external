package com.astrapay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.astrapay.dto.NoteDto;

@Service
public class NoteService {
    private final Map<Long, NoteDto> noteStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<NoteDto> getAllNotes() {
        return new ArrayList<>(noteStore.values());
    }

    public NoteDto addNote(NoteDto noteDto) {
        Long newId = idCounter.incrementAndGet();

        NoteDto newNote = new NoteDto();
        newNote.setId(newId);
        newNote.setTitle(noteDto.getTitle().trim());
        newNote.setContent(noteDto.getContent().trim());

        noteStore.put(newId, newNote);
        return newNote;
    }

    public boolean deleteNote(Long id) {
        return noteStore.remove(id) != null;
    }

    public NoteDto getNoteById(Long id) {
        NoteDto note = noteStore.get(id);
        if (note == null) {
            throw new NoSuchElementException("Catatan dengan ID " + id + " tidak ditemukan!");
        }
        return note;
    }
}