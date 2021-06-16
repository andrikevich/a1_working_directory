package by.a1.andrikevich.stu.data;

import by.a1.andrikevich.stu.model.Stu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface StuRepository extends JpaRepository<Stu, Integer> {
}
