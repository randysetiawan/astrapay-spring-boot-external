package com.astrapay.service;

import com.astrapay.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class NoteService {

    private final List<Note> noteList = new ArrayList<>();
    private final AtomicLong atomicLong = new AtomicLong(1);

    public List<Note> getAll() {
        return noteList;
    }

    public Note create(Note note) {
        Long id = atomicLong.getAndIncrement();
        note.setId(id);
        noteList.add(note);
        return note;
    }

    public Boolean delete(Long id) {
        return noteList.removeIf(note -> note.getId().equals(id));
    }
}
