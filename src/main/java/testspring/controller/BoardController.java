package testspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import testspring.dto.BoardDto;
import testspring.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
public class BoardController {

    @Autowired
     private BoardService boardService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/boardWrite")
    public boolean boardWrite(BoardDto boardDto) {
        return boardService.write(boardDto);
    }

    @GetMapping("/boardList")
    public void BoardList(HttpServletResponse response){

            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println( boardService.getBoardList() );
            }catch(Exception e){
                System.out.println(e);
        }
    }

    @GetMapping("/boardLookup")
    public void boardLookup(HttpServletResponse response){
        int bno=(Integer)request.getSession().getAttribute("bno");
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println( boardService.getLookup(bno) );
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @DeleteMapping("/boardDelete")
    public boolean boardDelete(@RequestParam("bno") int bno){
        if(boardService.delete(bno)==true){
            return true;
        }
      return false;
    }

    @PutMapping("/boardUpdate")
     public boolean boardUpdate(BoardDto boardDto){
        int bno =  (Integer) request.getSession().getAttribute("bno");
        boardDto.setBno( bno );
        return boardService.update( boardDto );
    }
}
