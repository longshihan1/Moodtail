package com.longshihan.moodtail.activity;

import android.os.Bundle;

import com.longshihan.moodtail.R;
import com.longshihan.moodtail.base.BaseActivityPresenter;
import com.longshihan.moodtail.contract.LoginContract;
import com.longshihan.moodtail.model.bean.TestModel;
import com.longshihan.moodtail.persenter.LoginPersenter;

public class LoginActivity extends BaseActivityPresenter<LoginContract.View,
        LoginPersenter> implements LoginContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected LoginPersenter createPresenter() {
        return null;
    }

    @Override
    protected void initData() {
      /*  Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        // 系统版本大于N的统一用FileProvider处理
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            // 将文件转换成content://Uri的形式
            Uri photoURI = FileProvider.getUriForFile(this,
                                                      this.getPackageName()+ ".provider",
                                                      new File(photoPath));

            // 申请临时访问权限
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        } else {
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            Uri uri = Uri.parse("file://" + photoPath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        this.startActivityForResult(intent, requestCode);*/

    }

    @Override
    public void showError(int type, String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showData(TestModel mtopBean) {

    }
}
