package hello.AYU.domain.event;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EventRepositoryTest {

    EventRepository eventRepository = new EventRepository();

    @AfterEach
    public void clear() {
        eventRepository.clearStore();
    }

    @Test
    void save(){
        Event event = new Event("축제", "화", "아리관");
        Event saveEvent = eventRepository.save(event);


        Event findEvent = eventRepository.findById(event.getId());
        assertThat(saveEvent).isEqualTo(event);

    }

    @Test
    void findAll(){
        Event event1 = new Event("시험", "월", "수봉관");
        Event event2 = new Event("수업", "화", "수리관");
        Event event3 = new Event("보충", "수", "아리관");

        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);

        List<Event> findAll = eventRepository.findAll();

        assertThat(findAll.size()).isEqualTo(3);
        assertThat(findAll).contains(event1, event2, event3);

    }

    @Test
    void updateItem(){
        Event event1 = new Event("시험", "월", "수봉관");
        eventRepository.save(event1);
        Event event2 = new Event("수업", "화", "수리관");
        eventRepository.update(event2,event1.getId());

        assertThat(event2.getTitle()).isEqualTo("수업");
        assertThat(event2.getDate()).isEqualTo("화");
        assertThat(event2.getLocation()).isEqualTo("수리관");

    }
}
