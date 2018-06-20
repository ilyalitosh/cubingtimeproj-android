package com.litosh.ilya.cubingtimeproj.globalmodels;

import com.litosh.ilya.ct_sdk.models.BaseCookie;

/**
 * Created by ilya_ on 18.06.2018.
 */

public class UserCookie extends BaseCookie {

    private static String cbtl;
    private static String cbtp;
    private static String phpSessId;
    private static String lang;
    private static String noprev;
    private static String night;

    public static void setCbtl(String cbtl) {
        UserCookie.cbtl = cbtl;
    }

    public static void setCbtp(String cbtp) {
        UserCookie.cbtp = cbtp;
    }

    public static void setPhpSessId(String phpSessId) {
        UserCookie.phpSessId = phpSessId;
    }

    public static void setLang(String lang) {
        UserCookie.lang = lang;
    }

    public static void setNoprev(String noprev) {
        UserCookie.noprev = noprev;
    }

    public static void setNight(String night) {
        UserCookie.night = night;
    }

    @Override
    public String getCbtl() {
        return cbtl;
    }

    @Override
    public String getCbtp() {
        return cbtp;
    }

    @Override
    public String getPhpSessId() {
        return phpSessId;
    }

    @Override
    public String getLang() {
        return lang;
    }

    @Override
    public String getNight() {
        return night;
    }

    @Override
    public String getNoprev() {
        return noprev;
    }
}
