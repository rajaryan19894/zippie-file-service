package com.googne.zippie.service.file.model;

import com.googne.zippie.common.lib.model.BaseEntity;
import com.googne.zippie.service.file.dto.HeaderRuleRequest;
import com.googne.zippie.service.file.model.enums.EmptyRowEvaluationMode;
import com.googne.zippie.service.file.model.enums.FileFormat;
import com.googne.zippie.service.file.model.enums.SourceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FileTemplate extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private SourceType sourceType; // matches FileUpload.sourceType

    @Column(nullable = false)
    private String name; // human-readable name e.g., "Expense Excel"

    @Enumerated(EnumType.STRING)
    private FileFormat format; // XLSX, CSV, etc.

    @Column(nullable = false)
    private Integer emptyCellThreshold = 0; // Number of empty mapped cells after which row is treated as EOD

    @Column(nullable = false)
    private boolean stopOnThresholdCross = true;
    // If true -> break extraction
    // If false -> just skip row

    private Double emptyCellPercentageThreshold; // e.g., 0.8 = 80% empty

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EmptyRowEvaluationMode emptyRowEvaluationMode = EmptyRowEvaluationMode.REQUIRED_ONLY;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<HeaderRule> headerRules = new ArrayList<>(); // list of column rules

    @ElementCollection
    @CollectionTable(
            name = "template_denomination_rules",
            joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "denomination")
    private List<String> denominationRules;

    // Helper method to add HeaderRule safely
    public void addHeaderRule(HeaderRule rule) {
        if (!headerRules.contains(rule)) {
            headerRules.add(rule);
        }

        if (Objects.isNull(rule.getTemplate())) {
            rule.setTemplate(this);
        }
    }

    // Helper method to remove HeaderRule safely
    public void removeHeaderRule(HeaderRule rule) {
        headerRules.remove(rule);
        rule.setTemplate(null);
    }

    public void clearHeaderRule() {
        headerRules = new ArrayList<>();
    }

    public void addHeaderRule(List<HeaderRule> headerRules) {
        headerRules.forEach(this::addHeaderRule);
    }
}
