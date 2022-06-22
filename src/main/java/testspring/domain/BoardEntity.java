package testspring.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@Table(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;                //게시판 번호
    private String btitle;        //게시판 제목
    private String bcontent;  //게시판 내용
    private String bwriter;    //게시판 작성자
    private String bpwd;       //게시판 비밀번호

}
