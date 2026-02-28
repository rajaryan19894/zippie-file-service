package com.googne.zippie.service.file.model;

import com.googne.zippie.common.lib.model.BaseEntity;
import com.googne.zippie.service.file.model.enums.ColumnDataType;
import com.googne.zippie.service.file.model.enums.EmptyCellStrategy;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class HeaderRule extends BaseEntity {
    private ColumnDataType dataType;   // e.g., "Date", "String"
    private String columnName;   // e.g., "transactionDate", "amount"

    @ElementCollection
    @CollectionTable(
            name = "header_rule_aliases",
            joinColumns = @JoinColumn(name = "header_rule_id"))
    @Column(name = "alias")
    private List<String> aliases;       // e.g., ["Date", "Transaction Date"]

    private boolean required; // true if column is mandatory

//    @Column(nullable = false)
//    private String sheetName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_template_id")
    private FileTemplate template;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmptyCellStrategy emptyCellStrategy = EmptyCellStrategy.NULL;
}
