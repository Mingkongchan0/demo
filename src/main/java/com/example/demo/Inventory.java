package com.example.demo;

import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @ToString
public class Inventory{
        private String Artist;
        private String Album;
        private Integer Quantity;
        private Integer Price;


}

