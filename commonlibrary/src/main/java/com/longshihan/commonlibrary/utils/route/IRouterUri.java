package com.longshihan.commonlibrary.utils.route;

/**
 * @author longshihan
 * @time 2017/5/8 16:09
 * @des 路由跳转
 */

public interface IRouterUri {
    @RouterUri(routerUri = "xl://moodtail:8888/testactivity")
    void jumpToGoodsDetail(@RouterParam("goodsId") String goodsId, @RouterParam("des") String des);//参数商品Id 商品描述

    @RouterUri(routerUri = "xl://moodtail:8888/moduleactivity")
    void jumpToModule(@RouterParam("goodsId") String goodsId, @RouterParam("des") String des);
}
