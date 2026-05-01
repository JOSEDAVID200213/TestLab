package com.testlab.testresults;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestRunService {
    private final TestRunRepository repository;

    public List<TestRun> getHistory() {
        return repository.findTop30ByOrderByTimestampDesc();
    }

    public TestRun getLatest() {
        return repository.findTop30ByOrderByTimestampDesc().stream().findFirst().orElse(null);
    }

    public TestRun saveRun(TestRun run) {
        return repository.save(run);
    }
}
