package co.edu.unisabana.parcial.repository.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "CHECKPOINT")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Checkpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String type;
    private String facility;
    private String driver;
    private int dayOfMonth;
    private LocalDateTime creationDate;
    private boolean finalized;
}

}
