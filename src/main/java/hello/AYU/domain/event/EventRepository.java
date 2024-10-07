package hello.AYU.domain.event;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventRepository {

    private static final Map<Long, Event> events = new HashMap<Long, Event>();
    private static long sequence = 0L;

    public Event save(Event event) {
        event.setId(sequence++);
        events.put(event.getId(), event);
        return event;
    }

    public Event findById(long id) {
        return events.get(id);
    }

    public List<Event> findAll() {
        return new ArrayList<Event>(events.values());
    }

    public void update(Event updateEvent, long id) {
        Event event = findById(id);
        event.setId(id);
        event.setDate(updateEvent.getDate());
        event.setLocation(updateEvent.getLocation());
        event.setTitle(updateEvent.getTitle());

    }
    public void delete(long id) {
        events.remove(id);
    }

    public void clearStore() {
        events.clear();
    }
}
