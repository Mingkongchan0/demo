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

    Inventory inv = new Inventory();

    @PostMapping("/SetArtist/")
    @ResponseBody
    public void setArtist(@Valid @RequestBody @NonNull String stringdb ) throws JsonProcessingException
    {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(stringdb);
            Iterator<JsonNode> it = root.iterator();
            db = new Database(new ArrayList<Inventory>());
            db.list = new ArrayList<Inventory>();

        while (it.hasNext())
        {

            JsonNode Inventory = it.next();
            inv.setArtist(Inventory.findValue("Artist").toString());

            inv.setAlbum(Inventory.findValue("Album").toString());

            inv.setQuantity(Inventory.findValue("Quantity").asInt());

            inv.setPrice(Inventory.findValue("Price").asInt());
            db.list.add(inv);
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
