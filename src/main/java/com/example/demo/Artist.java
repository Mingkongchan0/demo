package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.*;
@WebServlet(name = "ArtistServlet", urlPatterns = "/ArtistServlet")
@RestController @RequiredArgsConstructor
@EnableAutoConfiguration @ComponentScan @Configuration
//Controller


public class Artist extends HttpServlet
{

    private final Inventory inv = new Inventory(new ArrayList<>());

    @PostMapping("/SetInventory/")
    @ResponseBody
    public void setInventory(@Valid @RequestBody @NonNull String stringdb ) throws JsonProcessingException
    {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(stringdb);
        for (JsonNode Inventory : root) {

            Inventory temp = new Inventory(new ArrayList<>());
            temp.setArtist(Inventory.findValue("Artist").toString());

            temp.setAlbum(Inventory.findValue("Album").toString());

            temp.setQuantity(Inventory.findValue("Quantity").asInt());

            temp.setPrice(Inventory.findValue("Price").asInt());
            inv.list.add(temp);
        }
    }
    @GetMapping("/GetInventory/{strIndex}")
    @ResponseBody
    public ResponseEntity<Inventory> getInventory(@NonNull @Valid @RequestBody @PathVariable String strIndex)
    {
        Inventory index = inv.list.get(Integer.parseInt(strIndex));
        return ResponseEntity.ok(index);
    }
    @GetMapping("GetInventory/all")
    @ResponseBody
    public ResponseEntity <ArrayList<Inventory>> getAllInventory()
    {
        return ResponseEntity.ok(inv.list);
    }

    @RequestMapping("/UpdateInventory/{strIndex}")
    @ResponseBody
    public void updateInventory(@Valid @RequestBody  @PathVariable String strIndex, @RequestBody @Valid @NonNull String stringdb) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(stringdb);
        for (JsonNode jsonNode : root) {
            Inventory index = inv.list.get(Integer.parseInt(strIndex));

            if ((jsonNode.findValue("Artist") != null)) {
                index.setArtist(jsonNode.findValue("Artist").toString());
            }

            if ((jsonNode.findValue("Album") != null)) {
                index.setAlbum(jsonNode.findValue("Album").toString());
            }

            if ((jsonNode.findValue("Quantity") != null)) {
                index.setQuantity(jsonNode.findValue("Quantity").asInt());
            }

            if ((jsonNode.findValue("Price") != null)) {
                index.setPrice(jsonNode.findValue("Price").asInt());
            }
        }
    }
    @RequestMapping("/DelInventory/{strIndex}")
    @ResponseBody
    public void delInventory(@Valid @RequestBody @PathVariable String strIndex)
    {
        inv.list.set(Integer.parseInt(strIndex), null);
    }

}
