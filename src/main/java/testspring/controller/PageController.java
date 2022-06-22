package testspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/boardList")
    public String board(){
        return "board/boardlist";
    }

    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "board/boardwrite";
    }

    @GetMapping("/boardView/{bno}")
    public String boardView(@PathVariable("bno") int bno){
        request.getSession().setAttribute("bno",bno);
        return "board/boardview";
    }

}
