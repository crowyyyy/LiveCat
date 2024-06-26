package com.crow.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo {
    private String fileName;
    private String fileUrl;
    private String createTime;
    private Long fileSize;
}
