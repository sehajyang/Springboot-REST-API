package me.sehajyang.io.events;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id") // 연관관계에 해당되는 것은 추가하면 안됨(상호 참조때문)
//@EqualsAndHashCode(callSuper=true)는 모든 필드 대상으로 만듦
@Entity
public class Event {
    //엔티티간의 상호 참조하는 관계가 되면 @EqualsAndHashCode로 구현한 코드 안에서 스택오버플로우가 발생할 수 있음
    
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임 
    private int basePrice; // (optional) 
    private int maxPrice; // (optional) 
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

}
