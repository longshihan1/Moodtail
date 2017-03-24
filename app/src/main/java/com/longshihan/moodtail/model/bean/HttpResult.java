package com.longshihan.moodtail.model.bean;

import java.io.Serializable;

/**
 * @author Administrator
 * @time 2016/10/28 11:26
 * @des
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class HttpResult implements Serializable {
    public int code;
    public String data;
    public String msg;
    public String debug;

    public String token;
    public String key;
    public int user_id;

}
