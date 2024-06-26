package com.crow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreeperMeta {
    private String name;
    private String description;
    private String group;
    private String platform;
    private String creeperId;
}