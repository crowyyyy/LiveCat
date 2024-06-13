package com.crow.domain.live;

import com.crow.constant.SystemConst;
import com.crow.domain.Live;

public class BiliBiliLive extends Live {
    // 分区id
    private String area_id;
    // 分区名
    private String area_name;
    // 主播id
    private String uid;
    //
    private String parent_area_id;

    private String parent_area_name;

    private String session_id;

    private String group_id;

    public BiliBiliLive(int watcherNum, String liveId, String liveName, String liver, String description,
                        String uid,String area_id, String area_name, String parent_area_id, String parent_area_name, String session_id, String group_id) {
        super(watcherNum, liveId, liveName, liver, description, SystemConst.BILIBILI,area_id,area_name);
        this.uid = uid;
        this.parent_area_id = parent_area_id;
        this.parent_area_name = parent_area_name;
        this.session_id = session_id;
        this.group_id = group_id;
    }
}
