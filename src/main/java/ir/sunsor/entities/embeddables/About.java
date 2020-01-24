package ir.sunsor.entities.embeddables;

import lombok.*;

import javax.persistence.Embeddable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Embeddable
public class About {

    private String country;
    private String city;
    private String favorites;
    private String status;
    private String description;
}
