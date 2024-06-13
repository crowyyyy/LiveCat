package com.crow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.crow.domain.FocusLiver;

import java.util.List;

public interface FocusLiverService extends IService<FocusLiver> {

    List<FocusLiver> getFocusLivers();

    boolean deleteLivers(String platform,String liver);

    boolean addLivers(FocusLiver liver);

    boolean hasLiver(String platform,String liver);
}
