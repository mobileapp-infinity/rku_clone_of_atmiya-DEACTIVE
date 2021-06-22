package com.infinity.infoway.rkuniversity.forgot_password.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetForgetPasswordDetailByUserIDPojo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("institute_id")
    @Expose
    private Integer instituteId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("encrypted_pwd_config")
    @Expose
    private Integer encryptedPwdConfig;
    @SerializedName("forget_pwd_link")
    @Expose
    private String forgetPwdLink;
    @SerializedName("im_domain_name")
    @Expose
    private String imDomainName;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("emp_stud_status")
    @Expose
    private Integer empStudStatus;
    @SerializedName("sms_api")
    @Expose
    private String smsApi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEncryptedPwdConfig() {
        return encryptedPwdConfig;
    }

    public void setEncryptedPwdConfig(Integer encryptedPwdConfig) {
        this.encryptedPwdConfig = encryptedPwdConfig;
    }

    public String getForgetPwdLink() {
        return forgetPwdLink;
    }

    public void setForgetPwdLink(String forgetPwdLink) {
        this.forgetPwdLink = forgetPwdLink;
    }

    public String getImDomainName() {
        return imDomainName;
    }

    public void setImDomainName(String imDomainName) {
        this.imDomainName = imDomainName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEmpStudStatus() {
        return empStudStatus;
    }

    public void setEmpStudStatus(Integer empStudStatus) {
        this.empStudStatus = empStudStatus;
    }

    public String getSmsApi() {
        return smsApi;
    }

    public void setSmsApi(String smsApi) {
        this.smsApi = smsApi;
    }
}
