package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import java.util.List;

public class GetUserWiseDetailForgetPasswordPojo {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * user_id : 5547
         * main_school_id : 3
         * username : 1030416061
         * password : 0x4d153a02637b3cfe008fc58a44ffb832
         * college_name : Atmiya Institute of Technology & Science
         * college_logo : cb76afff_4ebe_4efe_90ab_d945b653791e.png
         * institute_id : 1
         * user_code : 1030416061
         * college_code : 3
         * name : Vekaria Vaibhav Pravinbhai
         * emp_user_status : 0
         * mobile_no : 9925050794
         * is_login_otp_require : 0
         * otp_base_forget_pwd_config : 1
         * emp_pwd_config : 1
         * stud_pwd_config : 1
         * sms_api : http://message.smartwave.co.in/rest/services/sendSMS/sendGroupSms?AUTH_KEY=c564eac8f430fd3023e1c046e286885b&senderId=ATMIYA&routeId=1&smsContentType=english&message=addmessage&mobileNos=addmobileno
         */

        private int user_id;
        private int main_school_id;
        private String username;
        private String password;
        private String college_name;
        private String college_logo;
        private int institute_id;
        private String user_code;
        private String college_code;
        private String name;
        private int emp_user_status;
        private String mobile_no;
        private int is_login_otp_require;
        private int otp_base_forget_pwd_config;
        private int emp_pwd_config;
        private int stud_pwd_config;
        private String sms_api;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getMain_school_id() {
            return main_school_id;
        }

        public void setMain_school_id(int main_school_id) {
            this.main_school_id = main_school_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }

        public String getCollege_logo() {
            return college_logo;
        }

        public void setCollege_logo(String college_logo) {
            this.college_logo = college_logo;
        }

        public int getInstitute_id() {
            return institute_id;
        }

        public void setInstitute_id(int institute_id) {
            this.institute_id = institute_id;
        }

        public String getUser_code() {
            return user_code;
        }

        public void setUser_code(String user_code) {
            this.user_code = user_code;
        }

        public String getCollege_code() {
            return college_code;
        }

        public void setCollege_code(String college_code) {
            this.college_code = college_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getEmp_user_status() {
            return emp_user_status;
        }

        public void setEmp_user_status(int emp_user_status) {
            this.emp_user_status = emp_user_status;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public int getIs_login_otp_require() {
            return is_login_otp_require;
        }

        public void setIs_login_otp_require(int is_login_otp_require) {
            this.is_login_otp_require = is_login_otp_require;
        }

        public int getOtp_base_forget_pwd_config() {
            return otp_base_forget_pwd_config;
        }

        public void setOtp_base_forget_pwd_config(int otp_base_forget_pwd_config) {
            this.otp_base_forget_pwd_config = otp_base_forget_pwd_config;
        }

        public int getEmp_pwd_config() {
            return emp_pwd_config;
        }

        public void setEmp_pwd_config(int emp_pwd_config) {
            this.emp_pwd_config = emp_pwd_config;
        }

        public int getStud_pwd_config() {
            return stud_pwd_config;
        }

        public void setStud_pwd_config(int stud_pwd_config) {
            this.stud_pwd_config = stud_pwd_config;
        }

        public String getSms_api() {
            return sms_api;
        }

        public void setSms_api(String sms_api) {
            this.sms_api = sms_api;
        }
    }
}
