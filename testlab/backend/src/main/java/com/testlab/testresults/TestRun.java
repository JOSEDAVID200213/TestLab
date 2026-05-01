package com.testlab.testresults;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_runs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private Integer totalTests;
    private Integer passedTests;
    private Integer failedTests;
    private Double coveragePercentage;
    private String status; // PASSED, FAILED
}
