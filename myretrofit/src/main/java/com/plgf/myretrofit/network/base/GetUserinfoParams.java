package com.plgf.myretrofit.network.base;

/**
 * Created by Administrator on 2016/10/24.
 */
public class GetUserinfoParams {

    /**
     * result : 200
     * message : success
     * userinfo : {"appAccount":"15755054169","appMobile":"15755054169","appPassword":"e10adc3949ba59abbe56e057f20f883e","appPicture":"","appUserId":"85be8344-4ea1-11e8-aafa-00163e043b1e","appUsername":"","createTime":"2018-05-03 15:13:47","money":"0","registrationid":"140fe1da9eafb6f9405"}
     */

    private int result;
    private String message;
    private UserinfoBean userinfo;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * appAccount : 15755054169
         * appMobile : 15755054169
         * appPassword : e10adc3949ba59abbe56e057f20f883e
         * appPicture :
         * appUserId : 85be8344-4ea1-11e8-aafa-00163e043b1e
         * appUsername :
         * createTime : 2018-05-03 15:13:47
         * money : 0
         * registrationid : 140fe1da9eafb6f9405
         */

        private String appAccount;
        private String appMobile;
        private String appPassword;
        private String appPicture;
        private String appUserId;
        private String appUsername;
        private String createTime;
        private String money;
        private String registrationid;
        private int cardNumber;

        public int getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(int cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getAppAccount() {
            return appAccount;
        }

        public void setAppAccount(String appAccount) {
            this.appAccount = appAccount;
        }

        public String getAppMobile() {
            return appMobile;
        }

        public void setAppMobile(String appMobile) {
            this.appMobile = appMobile;
        }

        public String getAppPassword() {
            return appPassword;
        }

        public void setAppPassword(String appPassword) {
            this.appPassword = appPassword;
        }

        public String getAppPicture() {
            return appPicture;
        }

        public void setAppPicture(String appPicture) {
            this.appPicture = appPicture;
        }

        public String getAppUserId() {
            return appUserId;
        }

        public void setAppUserId(String appUserId) {
            this.appUserId = appUserId;
        }

        public String getAppUsername() {
            return appUsername;
        }

        public void setAppUsername(String appUsername) {
            this.appUsername = appUsername;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRegistrationid() {
            return registrationid;
        }

        public void setRegistrationid(String registrationid) {
            this.registrationid = registrationid;
        }

        @Override
        public String toString() {
            return "UserinfoBean{" +
                    "appAccount='" + appAccount + '\'' +
                    ", appMobile='" + appMobile + '\'' +
                    ", appPassword='" + appPassword + '\'' +
                    ", appPicture='" + appPicture + '\'' +
                    ", appUserId='" + appUserId + '\'' +
                    ", appUsername='" + appUsername + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", money='" + money + '\'' +
                    ", registrationid='" + registrationid + '\'' +
                    ", cardNumber=" + cardNumber +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetUserinfoParams{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", userinfo=" + userinfo +
                '}';
    }
}
