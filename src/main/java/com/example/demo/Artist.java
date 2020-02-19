package com.example.demo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.*;
@WebServlet(name = "ArtistServlet", urlPatterns = "/ArtistServlet")
@RestController @RequiredArgsConstructor
@EnableAutoConfiguration
//Controller


public class Artist extends HttpServlet
{
    private final Inventory inv = new Inventory();

    @PostMapping("/SetInventory/")
    @ResponseBody
    public void setInventory(@NotNull @Valid @RequestBody @NonNull JsonNode jsonnode )
    {
            Inventory temp = new Inventory();

            temp.setArtist(jsonnode.findValue("Artist").toString());

            temp.setAlbum(jsonnode.findValue("Album").toString());

            temp.setQuantity(jsonnode.findValue("Quantity").asInt());

            temp.setPrice(jsonnode.findValue("Price").floatValue());

            inv.list.add(temp);

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
    public void updateInventory(@Valid @RequestBody  @PathVariable String strIndex, @NotNull @RequestBody @Valid @NonNull JsonNode jsonnode)
    {
            Inventory index = inv.list.get(Integer.parseInt(strIndex));

            if ((jsonnode.findValue("Artist") != null)) {
                index.setArtist(jsonnode.findValue("Artist").toString());
            }

            if ((jsonnode.findValue("Album") != null)) {
                index.setAlbum(jsonnode.findValue("Album").toString());
            }

            if ((jsonnode.findValue("Quantity") != null)) {
                index.setQuantity(jsonnode.findValue("Quantity").asInt());
            }

            if ((jsonnode.findValue("Price") != null)) {
                index.setPrice(jsonnode.findValue("Price").floatValue());
            }

    }
    @RequestMapping("/DelInventory/{strIndex}")
    @ResponseBody
    public void delInventory(@Valid @RequestBody @PathVariable String strIndex)
    {
        inv.list.set(Integer.parseInt(strIndex), null);
    }

}
