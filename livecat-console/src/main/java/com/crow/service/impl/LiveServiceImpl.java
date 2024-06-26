package com.crow.service.impl;

import com.crow.domain.FocusLiver;
import com.crow.service.LiveService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveServiceImpl implements LiveService {
    @Override
    public List<FocusLiver> allFocusLivers() {
        return List.of();
    }

    @Override
    public boolean follow(FocusLiver focusLiver) {
        return false;
    }

    @Override
    public boolean unFollow(String platform, String liver) {
        return false;
    }
}
