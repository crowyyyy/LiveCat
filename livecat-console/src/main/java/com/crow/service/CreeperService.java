package com.crow.service;

import com.crow.domain.CreeperMeta;
import com.crow.domain.CreeperTaskVo;

import java.util.List;

public interface CreeperService {
    List<CreeperMeta> getAllCreeper();

    List<CreeperTaskVo> getAllCreeperTask();
}
