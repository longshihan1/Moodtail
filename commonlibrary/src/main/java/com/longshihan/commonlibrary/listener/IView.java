package com.longshihan.commonlibrary.listener;


/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 07:55
 * @des View的接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface IView {

    /**
     * 出错显示错误信息
     *
     * @param
     */
    void showError(int type, String msg);

    /**
     * 展示等待图
     */
    void showProgress();
}
