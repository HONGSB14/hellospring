package testspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import testspring.domain.RoomEntity;
import testspring.domain.RoomRepository;
import testspring.dto.RoomDto;

import java.io.File;
import java.util.*;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public boolean room_save(RoomDto roomDto){

        RoomEntity roomEntity =RoomEntity.builder().
                rname(roomDto.getRname()).
                x(roomDto.getX()).
                y(roomDto.getY()).
                build();

        String uuidfile=null;
        if(roomDto.getRimg().size() !=0){
            for(MultipartFile file : roomDto.getRimg()){
                UUID uuid=UUID.randomUUID();
                uuidfile=uuid.toString()+"_"+file.getOriginalFilename().replaceAll("_","_");
                String dir="C:\\Users\\HSB\\IdeaProjects\\hellospring\\src\\main\\resources\\static\\upload\\";
                String filepath=dir+uuidfile;
                try{
                  file.transferTo(new File(filepath));
                  roomEntity.setRimg(file.getOriginalFilename());
                }catch(Exception e){
                    System.out.println("파일 저장 실패 ..ㅠ  "+ e);
                }
            }
        }

       roomRepository.save(roomEntity);
        return true;
    }
    //형태 [{ key :{ {key:value},{key:value},{key:value},{key:value}.... }]
    public Map<String, List <Map <String,String>>>  room_list(Map<String, String> Location){
        //{{key : value}, {key : value }, {key : value } ..}리턴할 리스트 생성
        List<Map<String,String>> mapList = new ArrayList<>();

        //현재 보고 있는 지도 범위
        double qa=Double.parseDouble(Location.get("qa"));
        double pa=Double.parseDouble(Location.get("pa"));
        double ha=Double.parseDouble(Location.get("ha"));
        double oa=Double.parseDouble(Location.get("oa"));

        //1.모든 엔티티 꺼내오기
        List<RoomEntity> roomEntityList =roomRepository.findAll();

        //2. 엔티티-> map
        for(RoomEntity entity : roomEntityList){
            //만약  조건이 부합한다면
            if(  Double.parseDouble(  entity.getY() ) > qa
                    && Double.parseDouble(  entity.getY() ) < pa
                    && Double.parseDouble(  entity.getX() )   > ha
                    && Double.parseDouble(  entity.getX() )   < oa
            ){
                //3.map 객체 생성
                Map<String,String> map= new HashMap<>();
                map.put("roomname",entity.getRname());
                map.put("lng",entity.getX());
                map.put("lat",entity.getY());
                map.put("rno", entity.getRno()+"" );
                map.put("rimg", entity.getRimg() );
                //arraylist 에다가 map 을 넣기
                mapList.add(map);
            }

        }
        Map<String,List <Map <String,String>>> object = new HashMap<>();
        object.put("positions", mapList );
        return object;
    }


}
