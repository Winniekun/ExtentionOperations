package com.wkk.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kongweikun@163.com
 * @date 2023/2/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    private Integer id;

    private Integer parentId;

    private String name;
}
