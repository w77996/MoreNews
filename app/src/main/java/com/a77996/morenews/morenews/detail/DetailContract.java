package com.a77996.morenews.morenews.detail;

import android.webkit.WebView;

import com.a77996.morenews.morenews.BasePresenter;
import com.a77996.morenews.morenews.BaseView;

/**
 * Created by Administrator on 2017/3/8.
 */
interface DetailContract {
    interface View extends BaseView<Presenter>{
        void showLoading();
        void stopLoading();
        void showLoadingError();
        void showResult(String result);
        void showHtml(String result);
        void showCovert(String url);
        void setTitle(String title);
        void setImageMode(boolean imageMode);
        void showBrowserNotFoundError();
        void showTextCopied();
        void showCopiedTextError();
    }
    interface Presenter extends BasePresenter{

        void openInBrowser();
        void shareAsText();
        void openUrl(WebView webView,String url);
        void requestData();
        void copyText();
        void copyLink();
    }
}
