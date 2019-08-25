package org.kingsski.kaas.database.division;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the DIVISION view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Division {
    private long id;
    private String name;
    private String competition;
    private String organisation;
}
