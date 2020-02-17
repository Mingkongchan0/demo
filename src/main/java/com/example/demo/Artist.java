package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "ArtistServlet", urlPatterns = "/ArtistServlet")
@RestController @RequiredArgsConstructor
@EnableAutoConfiguration
//Controller


public class Artist extends HttpServlet
{
    Database db;

    private final Inventory inv = new Inventory(new ArrayList<>());

    @PostMapping("/SetArtist/")
    @ResponseBody
    public void setArtist(@Valid @RequestBody @NonNull String stringdb ) throws JsonProcessingException
    {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(stringdb);
            Iterator<JsonNode> it = root.iterator();
        while (it.hasNext())
        {

            JsonNode Inventory = it.next();
            Inventory temp = new Inventory(new ArrayList<>());
            temp.setArtist(Inventory.findValue("Artist").toString());

            temp.setAlbum(Inventory.findValue("Album").toString());

            temp.setQuantity(Inventory.findValue("Quantity").asInt());

            temp.setPrice(Inventory.findValue("Price").asInt());
            inv.list.add(temp);
        }
    }
    @GetMapping("/GetArtist/{array}/")
    @ResponseBody
    public ResponseEntity<Database> getArtist(@PathVariable String array)
    {
        return ResponseEntity.ok(db);
    }

    @RequestMapping("/UpdateArtist/")
    @ResponseBody
    public void updateArtist()
    {
        List <Inventory> database = db.getList();


        inv.getArtist();

        inv.getAlbum();

        inv.getQuantity();

        inv.getPrice();

    }
}
