package com.qudan.qingcloud.msqudan.util.responses.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by word on 2016/7/7.
 */
@JsonSerialize(nullsUsing = NullDataSerializer.class)
public class NullData {
}
