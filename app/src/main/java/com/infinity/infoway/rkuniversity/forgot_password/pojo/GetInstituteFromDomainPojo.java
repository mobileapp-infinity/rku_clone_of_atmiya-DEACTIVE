package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetInstituteFromDomainPojo {

    @SerializedName("Table")
    @Expose
    private List<GetInstituteFromDomainPojo.Table> table = null;

    public List<GetInstituteFromDomainPojo.Table> getTable() {
        return table;
    }

    public void setTable(List<GetInstituteFromDomainPojo.Table> table) {
        this.table = table;
    }


    public class Table {

        @SerializedName("im_id")
        @Expose
        private Integer imId;
        @SerializedName("im_domain_name")
        @Expose
        private String imDomainName;
        @SerializedName("im_prefix")
        @Expose
        private String imPrefix;
        @SerializedName("im_full_name")
        @Expose
        private String imFullName;
        @SerializedName("im_favicon_logo")
        @Expose
        private Object imFaviconLogo;
        @SerializedName("im_logo")
        @Expose
        private String imLogo;
        @SerializedName("im_menubar_title")
        @Expose
        private String imMenubarTitle;
        @SerializedName("im_company_id")
        @Expose
        private Integer imCompanyId;
        @SerializedName("im_short_name")
        @Expose
        private String imShortName;
        @SerializedName("im_web_title_1")
        @Expose
        private String imWebTitle1;
        @SerializedName("im_exam_db_name")
        @Expose
        private String imExamDbName;
        @SerializedName("emp_pwd_config")
        @Expose
        private Integer empPwdConfig;
        @SerializedName("stud_pwd_config")
        @Expose
        private Integer studPwdConfig;
        @SerializedName("im_otp_base_forget_pwd")
        @Expose
        private Integer imOtpBaseForgetPwd;

        public Integer getImId() {
            return imId;
        }

        public void setImId(Integer imId) {
            this.imId = imId;
        }

        public String getImDomainName() {
            return imDomainName;
        }

        public void setImDomainName(String imDomainName) {
            this.imDomainName = imDomainName;
        }

        public String getImPrefix() {
            return imPrefix;
        }

        public void setImPrefix(String imPrefix) {
            this.imPrefix = imPrefix;
        }

        public String getImFullName() {
            return imFullName;
        }

        public void setImFullName(String imFullName) {
            this.imFullName = imFullName;
        }

        public Object getImFaviconLogo() {
            return imFaviconLogo;
        }

        public void setImFaviconLogo(Object imFaviconLogo) {
            this.imFaviconLogo = imFaviconLogo;
        }

        public String getImLogo() {
            return imLogo;
        }

        public void setImLogo(String imLogo) {
            this.imLogo = imLogo;
        }

        public String getImMenubarTitle() {
            return imMenubarTitle;
        }

        public void setImMenubarTitle(String imMenubarTitle) {
            this.imMenubarTitle = imMenubarTitle;
        }

        public Integer getImCompanyId() {
            return imCompanyId;
        }

        public void setImCompanyId(Integer imCompanyId) {
            this.imCompanyId = imCompanyId;
        }

        public String getImShortName() {
            return imShortName;
        }

        public void setImShortName(String imShortName) {
            this.imShortName = imShortName;
        }

        public String getImWebTitle1() {
            return imWebTitle1;
        }

        public void setImWebTitle1(String imWebTitle1) {
            this.imWebTitle1 = imWebTitle1;
        }

        public String getImExamDbName() {
            return imExamDbName;
        }

        public void setImExamDbName(String imExamDbName) {
            this.imExamDbName = imExamDbName;
        }

        public Integer getEmpPwdConfig() {
            return empPwdConfig;
        }

        public void setEmpPwdConfig(Integer empPwdConfig) {
            this.empPwdConfig = empPwdConfig;
        }

        public Integer getStudPwdConfig() {
            return studPwdConfig;
        }

        public void setStudPwdConfig(Integer studPwdConfig) {
            this.studPwdConfig = studPwdConfig;
        }

        public Integer getImOtpBaseForgetPwd() {
            return imOtpBaseForgetPwd;
        }

        public void setImOtpBaseForgetPwd(Integer imOtpBaseForgetPwd) {
            this.imOtpBaseForgetPwd = imOtpBaseForgetPwd;
        }

    }

}
