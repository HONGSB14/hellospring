package testspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import testspring.dto.RoomDto;
import testspring.service.RoomService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/write")
    public String write(){
        return "room/write";
    }

    @PostMapping("/write")
    @ResponseBody
    public boolean write_save(RoomDto roomDto){
        System.out.println(roomDto.toString());
        return roomService.room_save(roomDto);
    }

    @GetMapping("/list")
    public String list(){
        return "room/list";
    }

    @PostMapping("/roomlist")
    @ResponseBody
    public Map<String, List<Map<String,String>>> roomlist(@RequestBody Map<String,String> Location){

        return roomService.room_list(Location);
    }
}
