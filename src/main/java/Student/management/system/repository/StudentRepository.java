package Student.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Student.management.system.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
