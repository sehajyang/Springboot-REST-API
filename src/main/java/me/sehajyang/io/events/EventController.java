package me.sehajyang.io.events;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {
    
    private final EventRepository eventRepository;
    
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @PostMapping
     public ResponseEntity createEvent(@RequestBody Event event) {
        Event newEvent = this.eventRepository.save(event);
        
          URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri(); 
           //hateos가 제공, Location URL만들기
          event.setId(10);
           return ResponseEntity.created(createdUri).body(event);
           //201 응답 만듦
         
     }
}
