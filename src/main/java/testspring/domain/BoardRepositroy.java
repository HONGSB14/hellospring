package testspring.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositroy extends JpaRepository<BoardEntity,Integer> {

}
