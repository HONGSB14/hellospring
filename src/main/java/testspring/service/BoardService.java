package testspring.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testspring.domain.BoardEntity;
import testspring.domain.BoardRepositroy;
import testspring.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepositroy boardRepositroy;

    public boolean write(BoardDto boardDto) {

        boardRepositroy.save(boardDto.getBoardEntity());
        return true;

    }

    public JSONArray getBoardList(){
       List<BoardEntity> boardList = boardRepositroy.findAll();
        JSONArray jsonArray = new JSONArray();
       for(BoardEntity boardEntity :boardList){

           JSONObject jsonObject = new JSONObject();
           jsonObject.put("btitle",boardEntity.getBtitle());
           jsonObject.put("bwriter",boardEntity.getBwriter());
           jsonObject.put("bno",boardEntity.getBno());
           jsonObject.put("bpwd",boardEntity.getBpwd());
           jsonObject.put("bcontent",boardEntity.getBcontent());
           jsonArray.put(jsonObject);
       }
       return jsonArray;
    }

    public JSONObject getLookup(int bno){
        Optional<BoardEntity>optional= boardRepositroy.findById(bno);
        BoardEntity entity = optional.get();

        JSONObject jo = new JSONObject();

            jo.put("btitle",entity.getBtitle());
            jo.put("bno",entity.getBno());
            jo.put("bwriter",entity.getBwriter());
            jo.put("bcontent",entity.getBcontent());
            jo.put("bpwd",entity.getBpwd());
            return jo;

    }
    @Transactional
    public boolean delete(int bno){

        BoardEntity entity = boardRepositroy.findById(bno).get();
        if(entity != null){
            boardRepositroy.delete(entity);
            return true;
        }
      return false;
    }
    @Transactional
    public boolean update(BoardDto boardDto){
       Optional<BoardEntity> optinal= boardRepositroy.findById(boardDto.getBno());
       BoardEntity entity=optinal.get();
      entity.setBtitle(boardDto.getBtitle());
      entity.setBwriter(boardDto.getBwriter());
      entity.setBcontent(boardDto.getBcontent());
      return true;
    }
}
