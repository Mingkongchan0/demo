package com.example.demo;

import lombok.*;
import java.util.ArrayList;

@Getter @Setter @EqualsAndHashCode @ToString
public class Inventory extends Database{
        private String Artist;
        private String Album;
        private Integer Quantity;
        private Integer Price;


        public Inventory(@NonNull ArrayList<Inventory> list) {
                super(list);
        }
}

