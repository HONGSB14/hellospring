package testspring.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString@Builder
@Table(name="room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rname;
    private String x;
    private String y;
    private String rimg;

}
