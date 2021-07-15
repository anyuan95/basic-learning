package org.example.basic.data.structure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductType {

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer status;

}
