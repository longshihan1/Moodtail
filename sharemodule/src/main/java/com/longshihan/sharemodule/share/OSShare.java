package com.longshihan.sharemodule.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.longshihan.sharemodule.util.Constanct;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.io.File;
import java.util.ArrayList;

/**
 * @author longshihan
 * @time 2017/5/8 10:52
 * @des
 */

public class OSShare {
    /**
     * 系统级分享
     *
     * @param type    分享类型
     * @param context 上下文
     * @param params  数据
     */
    public void share(String type, Context context, String... params) {
        Intent intent = null;
        if (type.equals(Constanct.TEXTSHARE)) {//分享文字
            intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, params[0]);
            intent.setType("text/plain");
        } else if (type.equals(Constanct.IMAGESHARE)) {//分享（多）图片
            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            for (int i = 0; i < params.length; i++) {
                Uri imageUri = Uri.fromFile(new File(params[i]));
                imageUris.add(imageUri);
            }
            intent = new Intent();
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            intent.setType("image/*");

        }
        context.startActivity(Intent.createChooser(intent, Constanct.TITLE));
    }

    /**
     * @param type
     * @param context
     * @param shareListener
     * @param params
     * @return
     * @des IMAGELOCAL 0->本地图片的地址
     * IMAGEURL   0->网络图片的地址
     * TEXT       0->文字
     * TEXTANDIMAGE 0->文字，1->本地图片地址
     * WEB       0->网址，1->文字说明 2->图片（暂时都是本地）
     * MUSIC     0->网址 1->标题     2->互转链接  3->图片  4->描述
     * VIDEO1    0->网址 1->标题     2->图片      3->描述
     * FILE      0->文件地址 1->文件描述
     */
    public ShareAction shareumen(final String type, final Context context, final UMShareListener shareListener, final String... params) {
        ShareAction mShareAction = new ShareAction((Activity) context).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                SHARE_MEDIA.ALIPAY, SHARE_MEDIA.SMS, SHARE_MEDIA.EMAIL,
                SHARE_MEDIA.YNOTE, SHARE_MEDIA.EVERNOTE
                                                                                     )
                .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                            //开启粘贴板
                            Toast.makeText(context, "复制文本按钮", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                            //开启粘贴板
                            Toast.makeText(context, "复制链接按钮", Toast.LENGTH_LONG).show();
                        } else {
                            if (type.equals(Constanct.SHAREIMAGELOCAL)) {
                                File file = new File(params[0]);
                                UMImage image = new UMImage(context, file);//本地文件
                                new ShareAction((Activity) context).withMedia(image)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREIMAGEURL)) {
                                UMImage image = new UMImage(context, params[0]);//网络图片
                                new ShareAction((Activity) context).withMedia(image)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHARETEXT)) {
                                new ShareAction((Activity) context).withText(params[0])
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHARETEXTANDIMAGE)) {
                                File file = new File(params[1]);
                                UMImage image = new UMImage(context, file);//本地文件
                                new ShareAction((Activity) context).withText(params[0])
                                        .withMedia(image)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREWEB)) {
                                UMWeb umWeb = new UMWeb(params[0]);
                                File file = new File(params[2]);
                                UMImage image = new UMImage(context, file);//本地文件
                                umWeb.setThumb(image);
                                new ShareAction((Activity) context)
                                        .withText(params[1])
                                        .withMedia(umWeb)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREMUSIC)) {
                                File file = new File(params[3]);
                                UMImage image = new UMImage(context, file);//本地文件
                                UMusic music = new UMusic(params[0]);
                                music.setTitle(params[1]);//音乐的标题
                                music.setmTargetUrl(params[2]);//QQ好友微信好友可以设置跳转链接
                                music.setThumb(image);//音乐的缩略图
                                music.setDescription(params[4]);//音乐的描述
                                new ShareAction((Activity) context).withMedia(music)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREVIDEO1)) {
                                UMVideo video = new UMVideo(params[0]);
                                File file = new File(params[2]);
                                UMImage image = new UMImage(context, file);//本地文件
                                video.setTitle(params[1]);//视频的标题
                                video.setThumb(image);//视频的缩略图
                                video.setDescription(params[3]);//视频的描述
                                new ShareAction((Activity) context).withMedia(video)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREFILE)) {//微信收藏和邮件中
                                File file = new File(params[0]);
                                new ShareAction((Activity) context)
                                        .withFile(file)
                                        .withText(params[1])
                                        .setCallback(shareListener).share();
                            } else if (type.equals(Constanct.SHAREMINAPP)) {//小程序，没什么用
                             /*   UMMin umMin = new UMMin(params[0]);
                                //umMin.setThumb(imagelocal);
                                umMin.setTitle(params[1]);
                                umMin.setDescription(params[2]);
                                umMin.setPath("pages/page10007/page10007");
                                umMin.setUserName("gh_3ac2059ac66f");
                                new ShareAction((Activity) context)
                                        .withMedia(umMin)
                                        .setPlatform(share_media)
                                        .setCallback(shareListener).share();*/
                            }
                        }
                    }
                });
        return mShareAction;
    }
}
