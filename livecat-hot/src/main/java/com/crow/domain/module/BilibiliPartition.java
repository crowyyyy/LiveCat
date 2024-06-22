package com.crow.domain.module;

import com.crow.domain.Partition;
import com.crow.domain.Live;
import lombok.Data;

@Data
public class BilibiliPartition extends Partition<Live> {

    private String parent_id;

    private String parent_name;

    private String act_id;

    private String pic;

    private int area_type;

    public BilibiliPartition(String tagId, String tagName, String parent_id, String parent_name,
                             String act_id, String pic, int area_type) {
        super(tagId, tagName);
        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.act_id = act_id;
        this.pic = pic;
        this.area_type = area_type;
    }
}
