package org.kingsski.kaas.database.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the TEAM view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private long id;
    private String name;
    private String club;
}
