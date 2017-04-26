package com.longshihan.commonlibrary.proxy;

/**
 * author : zhongwr on 2016/12/31
 * 代理的监听器的回调
 */
public class ProxyListenerConfigBuilder {
    private OnListenerProxyCallBack.OnClickProxyListener onClickProxyListener;
    private OnListenerProxyCallBack.OnLongClickProxyListener onLongClickProxyListener;
    private OnListenerProxyCallBack.OnFocusChangeListener onFocusChangeListener;
    private OnListenerProxyCallBack.OnScrollChangeListener onScrollChangeListener;
    private OnListenerProxyCallBack.OnKeyListener onKeyListener;
    private OnListenerProxyCallBack.OnTouchListener onTouchListener;
    private OnListenerProxyCallBack.OnHoverListener onHoverListener;
    private OnListenerProxyCallBack.OnGenericMotionListener onGenericMotionListener;
    private OnListenerProxyCallBack.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener;

    private OnListenerProxyCallBack.OnItemClickProxyListener onItemClickProxyListener;
    private OnListenerProxyCallBack.OnItemLongClickProxyListener mOnItemLongClickProxyListener;
    private OnListenerProxyCallBack.OnItemSelectedProxyListener mOnItemSelectedProxyListener;

    public ProxyListenerConfigBuilder buildOnClickProxyListener(OnListenerProxyCallBack.OnClickProxyListener onClickProxyListener) {
        this.onClickProxyListener = onClickProxyListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnLongClickProxyListener(OnListenerProxyCallBack.OnLongClickProxyListener onLongClickProxyListener) {
        this.onLongClickProxyListener = onLongClickProxyListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnFocusChangeListener(OnListenerProxyCallBack.OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnScrollChangeListener(OnListenerProxyCallBack.OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnKeyListener(OnListenerProxyCallBack.OnKeyListener onKeyListener) {
        this.onKeyListener = onKeyListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnTouchListener(OnListenerProxyCallBack.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnHoverListener(OnListenerProxyCallBack.OnHoverListener onHoverListener) {
        this.onHoverListener = onHoverListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnGenericMotionListener(OnListenerProxyCallBack.OnGenericMotionListener onGenericMotionListener) {
        this.onGenericMotionListener = onGenericMotionListener;
        return this;
    }

    public ProxyListenerConfigBuilder buildOnSystemUiVisibilityChangeListener(OnListenerProxyCallBack.OnSystemUiVisibilityChangeListener
                                                                                      onSystemUiVisibilityChangeListener) {
        this.onSystemUiVisibilityChangeListener = onSystemUiVisibilityChangeListener;
        return this;
    }

    /***
     * listview 的点击代理监听器回调
     *
     * @param onItemClickProxyListener
     * @return
     */
    public ProxyListenerConfigBuilder buildOnItemClickProxyListener(OnListenerProxyCallBack.OnItemClickProxyListener onItemClickProxyListener) {
        this.onItemClickProxyListener = onItemClickProxyListener;
        return this;
    }

    /***
     * listview 的长按代理监听器回调
     *
     * @param onItemLongClickProxyListener
     * @return
     */
    public ProxyListenerConfigBuilder buildOnItemLongClickProxyListener(OnListenerProxyCallBack.OnItemLongClickProxyListener onItemLongClickProxyListener) {
        mOnItemLongClickProxyListener = onItemLongClickProxyListener;
        return this;
    }

    /***
     * listview 的onItemSelected代理监听器
     *
     * @param onItemSelectedProxyListener
     * @return
     */
    public ProxyListenerConfigBuilder buildOnItemSelectedProxyListener(OnListenerProxyCallBack.OnItemSelectedProxyListener onItemSelectedProxyListener) {
        mOnItemSelectedProxyListener = onItemSelectedProxyListener;
        return this;
    }

    public OnListenerProxyCallBack.OnClickProxyListener getOnClickProxyListener() {
        return onClickProxyListener;
    }

    public OnListenerProxyCallBack.OnLongClickProxyListener getOnLongClickProxyListener() {
        return onLongClickProxyListener;
    }

    public OnListenerProxyCallBack.OnFocusChangeListener getOnFocusChangeListener() {
        return onFocusChangeListener;
    }

    public OnListenerProxyCallBack.OnScrollChangeListener getOnScrollChangeListener() {
        return onScrollChangeListener;
    }

    public OnListenerProxyCallBack.OnKeyListener getOnKeyListener() {
        return onKeyListener;
    }

    public OnListenerProxyCallBack.OnTouchListener getOnTouchListener() {
        return onTouchListener;
    }

    public OnListenerProxyCallBack.OnHoverListener getOnHoverListener() {
        return onHoverListener;
    }

    public OnListenerProxyCallBack.OnGenericMotionListener getOnGenericMotionListener() {
        return onGenericMotionListener;
    }

    public OnListenerProxyCallBack.OnSystemUiVisibilityChangeListener getOnSystemUiVisibilityChangeListener() {
        return onSystemUiVisibilityChangeListener;
    }

    public OnListenerProxyCallBack.OnItemClickProxyListener getOnItemClickProxyListener() {
        return onItemClickProxyListener;
    }

    public OnListenerProxyCallBack.OnItemLongClickProxyListener getOnItemLongClickProxyListener() {
        return mOnItemLongClickProxyListener;
    }

    public OnListenerProxyCallBack.OnItemSelectedProxyListener getOnItemSelectedProxyListener() {
        return mOnItemSelectedProxyListener;
    }
}
