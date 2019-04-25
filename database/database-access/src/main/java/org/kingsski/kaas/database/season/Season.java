package org.kingsski.kaas.database.season;

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
public class Season {
    private long id;
    private String name;
    private String comnpetition;
    private String organisation;
}
