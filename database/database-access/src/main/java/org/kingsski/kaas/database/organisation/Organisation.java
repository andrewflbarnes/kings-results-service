package org.kingsski.kaas.database.organisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the ORGANISATION view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"competitionCount"})
public class Organisation {
    private long id;
    private String name;
    private long competitionCount;
}
