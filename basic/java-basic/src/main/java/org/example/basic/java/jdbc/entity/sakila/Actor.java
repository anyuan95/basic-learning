package org.example.basic.java.jdbc.entity.sakila;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.basic.java.jdbc.annotations.TableId;
import org.example.basic.java.jdbc.annotations.TableName;

import java.sql.Timestamp;

/**
 * @author anyuan
 * @since 2021-07-21 13:45
 */
@Data
@Builder
@TableName("actor")
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @TableId
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;

}
