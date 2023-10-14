package domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long id;
    private String name;
    private String description;
}
