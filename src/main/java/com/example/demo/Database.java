package com.example.demo;

import lombok.*;

import java.util.ArrayList;
@Data
@Getter
@Setter
@AllArgsConstructor
public class Database
{
        //Some data the DB contains
        @NonNull ArrayList<Inventory> list;
}
