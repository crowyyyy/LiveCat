package com.crow.service;

import com.crow.domain.FocusLiver;

import java.util.List;

public interface LiveService {

    List<FocusLiver> allFocusLivers();

    boolean follow(FocusLiver focusLiver);

    boolean unFollow(String platform, String liver);
}
