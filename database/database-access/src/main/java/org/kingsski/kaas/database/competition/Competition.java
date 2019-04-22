package org.kingsski.kaas.database.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the COMPETITION view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    private long id;
    private String name;
    private String organisation;
}
