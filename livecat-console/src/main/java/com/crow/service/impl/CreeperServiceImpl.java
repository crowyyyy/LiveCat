package com.crow.service.impl;

import com.crow.core.creeper.CreeperMetaRegistry;
import com.crow.core.creeper.CreeperTaskManager;
import com.crow.domain.CreeperMeta;
import com.crow.domain.CreeperTaskVo;
import com.crow.service.CreeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreeperServiceImpl implements CreeperService {

    private CreeperMetaRegistry creeperMetaRegistry  = CreeperMetaRegistry.getInstance();


    public List<CreeperMeta> getAllCreeper(){
        return creeperMetaRegistry.getCreeperBeans();
    }

    @Override
    public List<CreeperTaskVo> getAllCreeperTask() {
        CreeperTaskManager.getInstance();
        return null;
    }

}
