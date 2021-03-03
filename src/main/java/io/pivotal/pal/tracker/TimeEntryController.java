package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        System.out.println("create");
        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        System.out.println("read");
        TimeEntry timeEntry = this.timeEntryRepository.find(timeEntryId);

        if (timeEntry == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(timeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        System.out.println("list");
        List<TimeEntry> list = this.timeEntryRepository.list();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        System.out.println("update");
        TimeEntry update = this.timeEntryRepository.update(timeEntryId, timeEntryToUpdate);

        if (update == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        System.out.println("delete");
        this.timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
