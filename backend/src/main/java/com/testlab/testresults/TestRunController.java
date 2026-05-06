package com.testlab.testresults;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/test-results")
@RequiredArgsConstructor
public class TestRunController {
    private final TestRunService service;

    @GetMapping("/history")
    public List<TestRun> getHistory() {
        return service.getHistory();
    }

    @GetMapping("/latest")
    public TestRun getLatest() {
        return service.getLatest();
    }
}
