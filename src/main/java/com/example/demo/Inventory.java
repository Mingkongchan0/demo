package com.example.demo;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
@Data
@NoArgsConstructor @Getter @Setter @EqualsAndHashCode @ToString @Entity
public class Inventory extends Database{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Integer id;
        private String Artist;
        private String Album;
        private Integer Quantity;
        private Float Price;
}

