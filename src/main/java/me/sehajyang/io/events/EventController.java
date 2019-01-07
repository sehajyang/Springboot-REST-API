package me.sehajyang.io.events;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class EventController {
    
    @PostMapping("/api/events")
     public ResponseEntity createEvent(@RequestBody Event event) {
          URI createdUri = linkTo(methodOn(EventController.class).createEvent(null)).slash("{id}").toUri(); 
           //hateos가 제공, Location URL만들기
           return ResponseEntity.created(createdUri).build();
           //201 응답 만듦
         
     }
}
