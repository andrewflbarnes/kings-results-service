package org.kingsski.kaas.database.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the CLUB view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"teamCount"})
public class Club {
    private long id;
    private String name;
    private long teamCount;
}
