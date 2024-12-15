package org.klozevitz.repositories.appUsers;

import org.klozevitz.enitites.appUsers.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
