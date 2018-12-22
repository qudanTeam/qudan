package com.qudan.qingcloud.msqudan.util;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUrl {
    protected static final Log logger = LogFactory.getLog(StringUrl.class);

    public static String appendUri(String uri, String appendQuery)  {
        try {
            URI oldUri = new URI(uri); String newQuery = oldUri.getQuery();
            if (newQuery == null) { newQuery = appendQuery; } else { newQuery += "&" + appendQuery; }
            URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(), oldUri.getPath(), newQuery, oldUri.getFragment());
            return newUri.toString();
        }catch (Exception ex){
            logger.error("追加URL错误，url:"+ uri, ex);
        }
        return null;
    }

}
