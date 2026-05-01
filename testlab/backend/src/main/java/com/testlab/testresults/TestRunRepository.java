package com.testlab.testresults;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {
    List<TestRun> findTop30ByOrderByTimestampDesc();
}
