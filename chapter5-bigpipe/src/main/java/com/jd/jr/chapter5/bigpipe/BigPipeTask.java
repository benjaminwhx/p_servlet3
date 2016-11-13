package com.jd.jr.chapter5.bigpipe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: 吴海旭
 * Date: 2016-11-12
 * Time: 下午6:31
 */
public class BigPipeTask {

    private final Map<String, Object> model;

    private String framePageletName;
    private List<String> pageletNames;

    public BigPipeTask(final Map<String, Object> model, final String framePageletName, final String... pageletNames) {
        this.model = model;
        this.framePageletName = framePageletName;
        if(pageletNames != null && pageletNames.length > 0) {
            this.pageletNames = Arrays.asList(pageletNames);
        } else {
            this.pageletNames = Collections.EMPTY_LIST;
        }
    }

    public String getFramePageletName() {
        return framePageletName;
    }

    public List<String> getPageletNames() {
        return pageletNames;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
