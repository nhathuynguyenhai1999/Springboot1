package cg.codegym.minitest.springboot2.Service.iml;

import cg.codegym.minitest.springboot2.Model.Notes;
import cg.codegym.minitest.springboot2.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    @Autowired
    private NotesRepository notesRepository;
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    public Notes addNote(String content) {
        Notes note = new Notes(content);
        return notesRepository.save(note);
    }

    public Optional<Notes> getNoteById(Long id) {
        return notesRepository.findById(id);
    }

    public Notes updateNoteContent(Long id, String newContent) {
        Optional<Notes> note = notesRepository.findById(id);
        if (note.isPresent()) {
            Notes updatedNote = note.get();
            updatedNote.setContent(newContent);
            return notesRepository.save(updatedNote);
        } else {
            throw new RuntimeException("Note not found with id: " + id);
        }
    }

    public void deleteNoteById(Long id) {
        notesRepository.deleteById(id);
    }
}
