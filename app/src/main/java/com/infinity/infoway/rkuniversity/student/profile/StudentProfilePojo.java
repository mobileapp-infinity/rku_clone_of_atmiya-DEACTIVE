package com.infinity.infoway.rkuniversity.student.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentProfilePojo {

    @SerializedName("stud_is_login_deactive")
    @Expose
    private Integer studIsLoginDeActive;
    @SerializedName("stud_id")
    @Expose
    private Integer studId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ac_full_name")
    @Expose
    private String acFullName;
    @SerializedName("dm_full_name")
    @Expose
    private String dmFullName;
    @SerializedName("course_fullname")
    @Expose
    private String courseFullname;
    @SerializedName("sm_name")
    @Expose
    private String smName;
    @SerializedName("stud_enrollment_no")
    @Expose
    private String studEnrollmentNo;
    @SerializedName("stud_admission_no")
    @Expose
    private String studAdmissionNo;
    @SerializedName("stud_admission_year")
    @Expose
    private Integer studAdmissionYear;
    @SerializedName("gen_name")
    @Expose
    private String genName;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("admission_type")
    @Expose
    private String admissionType;
    @SerializedName("Fee_Type")
    @Expose
    private String feeType;
    @SerializedName("Student_Quata")
    @Expose
    private String studentQuata;
    @SerializedName("Stud_Shift")
    @Expose
    private String studShift;
    @SerializedName("Stud_Current_Address")
    @Expose
    private Object studCurrentAddress;
    @SerializedName("Stud_Address1")
    @Expose
    private String studAddress1;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("state_name")
    @Expose
    private Object stateName;
    @SerializedName("country_name")
    @Expose
    private Object countryName;
    @SerializedName("stud_land_line_no")
    @Expose
    private Object studLandLineNo;
    @SerializedName("religion_name")
    @Expose
    private Object religionName;
    @SerializedName("dvm_name")
    @Expose
    private Object dvmName;
    @SerializedName("bch_name")
    @Expose
    private Object bchName;
    @SerializedName("swd_roll_no")
    @Expose
    private Integer swdRollNo;
    @SerializedName("stud_internet_username")
    @Expose
    private String studInternetUsername;
    @SerializedName("stud_internet_password")
    @Expose
    private String studInternetPassword;
    @SerializedName("stud_surname")
    @Expose
    private String studSurname;
    @SerializedName("stud_name")
    @Expose
    private String studName;
    @SerializedName("stud_father_name")
    @Expose
    private String studFatherName;
    @SerializedName("Stud_mobile_no")
    @Expose
    private String studMobileNo;
    @SerializedName("Stud_email")
    @Expose
    private String studEmail;
    @SerializedName("stud_fathers_name")
    @Expose
    private String studFathersName;
    @SerializedName("Stud_father_Occupation")
    @Expose
    private String studFatherOccupation;
    @SerializedName("Stud_father_mobile_no")
    @Expose
    private String studFatherMobileNo;
    @SerializedName("stud_mothers_name")
    @Expose
    private String studMothersName;
    @SerializedName("Stud_Mother_Occupation")
    @Expose
    private String studMotherOccupation;
    @SerializedName("Stud_mother_Mobile_no")
    @Expose
    private String studMotherMobileNo;
    @SerializedName("Stud_Address")
    @Expose
    private String studAddress;
    @SerializedName("Stud_city")
    @Expose
    private Integer studCity;
    @SerializedName("stud_district")
    @Expose
    private Integer studDistrict;
    @SerializedName("Stud_state")
    @Expose
    private Integer studState;
    @SerializedName("Stud_pin_no")
    @Expose
    private String studPinNo;
    @SerializedName("Stud_Country")
    @Expose
    private Integer studCountry;
    @SerializedName("stud_birth_place")
    @Expose
    private Object studBirthPlace;
    @SerializedName("Stud_religion")
    @Expose
    private Integer studReligion;
    @SerializedName("stud_bdate")
    @Expose
    private String studBdate;
    @SerializedName("stud_blood_group")
    @Expose
    private Integer studBloodGroup;
    @SerializedName("stud_category")
    @Expose
    private Integer studCategory;
    @SerializedName("Stud_gender")
    @Expose
    private Integer studGender;
    @SerializedName("stud_adhar_no")
    @Expose
    private String studAdharNo;
    @SerializedName("Stud_photo")
    @Expose
    private Object studPhoto;
    @SerializedName("stud_signature")
    @Expose
    private Object studSignature;
    @SerializedName("profile_completed")
    @Expose
    private Integer profileCompleted;
    @SerializedName("Previous_Month_Avg_Att")
    @Expose
    private Integer previousMonthAvgAtt;
    @SerializedName("Current_Month_Avg_Att")
    @Expose
    private Integer currentMonthAvgAtt;
    @SerializedName("Semester_Avg_Att")
    @Expose
    private Integer semesterAvgAtt;
    @SerializedName("unread_notif_count")
    @Expose
    private String unread_notif_count;

    public Integer getStudIsLoginDeActive() {
        return studIsLoginDeActive;
    }

    public void setStudIsLoginDeActive(Integer studIsLoginDeActive) {
        this.studIsLoginDeActive = studIsLoginDeActive;
    }

    public String getUnread_notif_count() {
        return unread_notif_count;
    }

    public void setUnread_notif_count(String unread_notif_count) {
        this.unread_notif_count = unread_notif_count;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcFullName() {
        return acFullName;
    }

    public void setAcFullName(String acFullName) {
        this.acFullName = acFullName;
    }

    public String getDmFullName() {
        return dmFullName;
    }

    public void setDmFullName(String dmFullName) {
        this.dmFullName = dmFullName;
    }

    public String getCourseFullname() {
        return courseFullname;
    }

    public void setCourseFullname(String courseFullname) {
        this.courseFullname = courseFullname;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getStudEnrollmentNo() {
        return studEnrollmentNo;
    }

    public void setStudEnrollmentNo(String studEnrollmentNo) {
        this.studEnrollmentNo = studEnrollmentNo;
    }

    public String getStudAdmissionNo() {
        return studAdmissionNo;
    }

    public void setStudAdmissionNo(String studAdmissionNo) {
        this.studAdmissionNo = studAdmissionNo;
    }

    public Integer getStudAdmissionYear() {
        return studAdmissionYear;
    }

    public void setStudAdmissionYear(Integer studAdmissionYear) {
        this.studAdmissionYear = studAdmissionYear;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getStudentQuata() {
        return studentQuata;
    }

    public void setStudentQuata(String studentQuata) {
        this.studentQuata = studentQuata;
    }

    public String getStudShift() {
        return studShift;
    }

    public void setStudShift(String studShift) {
        this.studShift = studShift;
    }

    public Object getStudCurrentAddress() {
        return studCurrentAddress;
    }

    public void setStudCurrentAddress(Object studCurrentAddress) {
        this.studCurrentAddress = studCurrentAddress;
    }

    public String getStudAddress1() {
        return studAddress1;
    }

    public void setStudAddress1(String studAddress1) {
        this.studAddress1 = studAddress1;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Object getStateName() {
        return stateName;
    }

    public void setStateName(Object stateName) {
        this.stateName = stateName;
    }

    public Object getCountryName() {
        return countryName;
    }

    public void setCountryName(Object countryName) {
        this.countryName = countryName;
    }

    public Object getStudLandLineNo() {
        return studLandLineNo;
    }

    public void setStudLandLineNo(Object studLandLineNo) {
        this.studLandLineNo = studLandLineNo;
    }

    public Object getReligionName() {
        return religionName;
    }

    public void setReligionName(Object religionName) {
        this.religionName = religionName;
    }

    public Object getDvmName() {
        return dvmName;
    }

    public void setDvmName(Object dvmName) {
        this.dvmName = dvmName;
    }

    public Object getBchName() {
        return bchName;
    }

    public void setBchName(Object bchName) {
        this.bchName = bchName;
    }

    public Integer getSwdRollNo() {
        return swdRollNo;
    }

    public void setSwdRollNo(Integer swdRollNo) {
        this.swdRollNo = swdRollNo;
    }

    public String getStudInternetUsername() {
        return studInternetUsername;
    }

    public void setStudInternetUsername(String studInternetUsername) {
        this.studInternetUsername = studInternetUsername;
    }

    public String getStudInternetPassword() {
        return studInternetPassword;
    }

    public void setStudInternetPassword(String studInternetPassword) {
        this.studInternetPassword = studInternetPassword;
    }

    public String getStudSurname() {
        return studSurname;
    }

    public void setStudSurname(String studSurname) {
        this.studSurname = studSurname;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudFatherName() {
        return studFatherName;
    }

    public void setStudFatherName(String studFatherName) {
        this.studFatherName = studFatherName;
    }

    public String getStudMobileNo() {
        return studMobileNo;
    }

    public void setStudMobileNo(String studMobileNo) {
        this.studMobileNo = studMobileNo;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public String getStudFathersName() {
        return studFathersName;
    }

    public void setStudFathersName(String studFathersName) {
        this.studFathersName = studFathersName;
    }

    public String getStudFatherOccupation() {
        return studFatherOccupation;
    }

    public void setStudFatherOccupation(String studFatherOccupation) {
        this.studFatherOccupation = studFatherOccupation;
    }

    public String getStudFatherMobileNo() {
        return studFatherMobileNo;
    }

    public void setStudFatherMobileNo(String studFatherMobileNo) {
        this.studFatherMobileNo = studFatherMobileNo;
    }

    public String getStudMothersName() {
        return studMothersName;
    }

    public void setStudMothersName(String studMothersName) {
        this.studMothersName = studMothersName;
    }

    public String getStudMotherOccupation() {
        return studMotherOccupation;
    }

    public void setStudMotherOccupation(String studMotherOccupation) {
        this.studMotherOccupation = studMotherOccupation;
    }

    public String getStudMotherMobileNo() {
        return studMotherMobileNo;
    }

    public void setStudMotherMobileNo(String studMotherMobileNo) {
        this.studMotherMobileNo = studMotherMobileNo;
    }

    public String getStudAddress() {
        return studAddress;
    }

    public void setStudAddress(String studAddress) {
        this.studAddress = studAddress;
    }

    public Integer getStudCity() {
        return studCity;
    }

    public void setStudCity(Integer studCity) {
        this.studCity = studCity;
    }

    public Integer getStudDistrict() {
        return studDistrict;
    }

    public void setStudDistrict(Integer studDistrict) {
        this.studDistrict = studDistrict;
    }

    public Integer getStudState() {
        return studState;
    }

    public void setStudState(Integer studState) {
        this.studState = studState;
    }

    public String getStudPinNo() {
        return studPinNo;
    }

    public void setStudPinNo(String studPinNo) {
        this.studPinNo = studPinNo;
    }

    public Integer getStudCountry() {
        return studCountry;
    }

    public void setStudCountry(Integer studCountry) {
        this.studCountry = studCountry;
    }

    public Object getStudBirthPlace() {
        return studBirthPlace;
    }

    public void setStudBirthPlace(Object studBirthPlace) {
        this.studBirthPlace = studBirthPlace;
    }

    public Integer getStudReligion() {
        return studReligion;
    }

    public void setStudReligion(Integer studReligion) {
        this.studReligion = studReligion;
    }

    public String getStudBdate() {
        return studBdate;
    }

    public void setStudBdate(String studBdate) {
        this.studBdate = studBdate;
    }

    public Integer getStudBloodGroup() {
        return studBloodGroup;
    }

    public void setStudBloodGroup(Integer studBloodGroup) {
        this.studBloodGroup = studBloodGroup;
    }

    public Integer getStudCategory() {
        return studCategory;
    }

    public void setStudCategory(Integer studCategory) {
        this.studCategory = studCategory;
    }

    public Integer getStudGender() {
        return studGender;
    }

    public void setStudGender(Integer studGender) {
        this.studGender = studGender;
    }

    public String getStudAdharNo() {
        return studAdharNo;
    }

    public void setStudAdharNo(String studAdharNo) {
        this.studAdharNo = studAdharNo;
    }

    public Object getStudPhoto() {
        return studPhoto;
    }

    public void setStudPhoto(Object studPhoto) {
        this.studPhoto = studPhoto;
    }

    public Object getStudSignature() {
        return studSignature;
    }

    public void setStudSignature(Object studSignature) {
        this.studSignature = studSignature;
    }

    public Integer getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(Integer profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public Integer getPreviousMonthAvgAtt() {
        return previousMonthAvgAtt;
    }

    public void setPreviousMonthAvgAtt(Integer previousMonthAvgAtt) {
        this.previousMonthAvgAtt = previousMonthAvgAtt;
    }

    public Integer getCurrentMonthAvgAtt() {
        return currentMonthAvgAtt;
    }

    public void setCurrentMonthAvgAtt(Integer currentMonthAvgAtt) {
        this.currentMonthAvgAtt = currentMonthAvgAtt;
    }

    public Integer getSemesterAvgAtt() {
        return semesterAvgAtt;
    }

    public void setSemesterAvgAtt(Integer semesterAvgAtt) {
        this.semesterAvgAtt = semesterAvgAtt;
    }


}
