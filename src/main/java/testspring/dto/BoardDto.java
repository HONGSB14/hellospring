package testspring.dto;

import lombok.*;
import testspring.domain.BoardEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString
public class BoardDto {

        private int bno;                //게시판 번호
        private String btitle;        //게시판 제목
        private String bcontent;  //게시판 내용
        private String bwriter;    //게시판 작성자
        private String bpwd;       //게시판 비밀번호
    //dto 를 entity 로 변환
    public BoardEntity getBoardEntity(){
        return BoardEntity.builder()
                .bno(this.getBno())
                .btitle(this.getBtitle())
                .bcontent(this.getBcontent())
                .bwriter(this.getBwriter())
                .bpwd(this.getBpwd())
                .build();

    }



}
