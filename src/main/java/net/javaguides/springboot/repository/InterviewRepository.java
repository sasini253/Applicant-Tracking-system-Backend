package net.javaguides.springboot.repository;


import net.javaguides.springboot.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long>{
//    Page<Landslide> findByPostId(Long postId, Pageable pageable);
}
