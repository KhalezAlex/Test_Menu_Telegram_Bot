package org.klozevitz.repositories.appUsers;

import org.klozevitz.enitites.appUsers.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
