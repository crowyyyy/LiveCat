package com.crow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crow.mapper.FocusLiverMapper;
import com.crow.domain.FocusLiver;
import com.crow.service.FocusLiverService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FocusLiverServiceImpl extends ServiceImpl<FocusLiverMapper, FocusLiver> implements FocusLiverService {

    @Override
    public List<FocusLiver> getFocusLivers() {
        return query().list();
    }

    @Override
    public boolean deleteLivers(String platform, String liver) {
        return false;
    }

    @Override
    public boolean addLivers(FocusLiver liver) {
        return false;
    }

    @Override
    public boolean hasLiver(String platform, String liver) {
        return false;
    }
}
