package hello.AYU.domain.event;


import lombok.Data;

@Data
public class Event {

    private Long id;
    private String title;
    private String date;
    private String location;



    public Event(){

    }

    public Event(String title, String date, String location) {

        this.title = title;
        this.date = date;
        this.location = location;
    }
}
