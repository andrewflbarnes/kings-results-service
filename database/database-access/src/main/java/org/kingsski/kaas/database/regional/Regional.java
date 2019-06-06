package org.kingsski.kaas.database.regional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the SEASON view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regional {
    private long id;
    private String name;
    private String season;
    private String league;
    private String competition;
    private String organisation;
}
