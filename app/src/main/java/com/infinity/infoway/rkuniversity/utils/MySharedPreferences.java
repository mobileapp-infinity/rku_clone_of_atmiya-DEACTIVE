package com.infinity.infoway.rkuniversity.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    SharedPreferences sharedPreferencesForStudentOrFaculty;
    SharedPreferences.Editor editorForStudentOrFaculty;
    private SharedPreferences sharedPreferencesForFirebaseFcmToken;
    private SharedPreferences.Editor editorForFirebaseFcmToken;
    Context context;
    private static final String PREFERENCES_ATMIYA_STUDENT_OR_FACULTY_LOGIN_DETAILS = "rku_student_or_faculty_login_details";

    private static final String FIREBASE_FCM_TOKEN = "fcm_token_details";//please do not use this file name in other places

    public MySharedPreferences(Context context) {
        this.context = context;
        sharedPreferencesForStudentOrFaculty = context.getSharedPreferences(PREFERENCES_ATMIYA_STUDENT_OR_FACULTY_LOGIN_DETAILS, MODE_PRIVATE);
        editorForStudentOrFaculty = sharedPreferencesForStudentOrFaculty.edit();
        sharedPreferencesForFirebaseFcmToken = context.getSharedPreferences(FIREBASE_FCM_TOKEN, MODE_PRIVATE);
        editorForFirebaseFcmToken = sharedPreferencesForFirebaseFcmToken.edit();
    }

    public void setEmpId(String empId) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_ID, empId);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_ID, "");
    }

    public void setEmpNumber(String empNumber) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_NUMBER, empNumber);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpNumber() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_NUMBER, "");
    }

    public void setEmpName(String empName) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_NAME, empName);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpName() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_NAME, "");
    }

    public void setEmpMainSchoolId(String empMainSchoolId) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_MAIN_SCHOOL_ID, empMainSchoolId);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpMainSchoolId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_MAIN_SCHOOL_ID, "");
    }

    public void setEmpUserName(String empUserName) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_USERNAME, empUserName);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpUserName() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_USERNAME, "");
    }

    public void setEmpPassword(String empPassword) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_PASSWORD, empPassword);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpPassword() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_PASSWORD, "");
    }

    public void setEmpAcFullName(String empAcFullName) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_AC_FULL_NAME, empAcFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpAcFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_AC_FULL_NAME, "");
    }

    public void setEmpAcLogo(String empAcLogo) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_AC_LOGO, empAcLogo);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpAcLogo() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_AC_LOGO, "");
    }

    public void setEmpPhotoUrl(String empPhotoUrl) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_PHOTO_URL, empPhotoUrl);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpPhotoUrl() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_PHOTO_URL, "");
    }

//    public void setEmpStatus(String empStatus) {
//        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_STATUS, empStatus);
//        editorForStudentOrFaculty.apply();
//    }
//
//    public String getEmpStatus() {
//        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_STATUS, "");
//    }

    public void setEmpAcCode(String empAcCode) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_AC_CODE, empAcCode);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpAcCode() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_AC_CODE, "");
    }

    public void setEmpIsDirectory(String empIsDirectory) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_IS_DIRECTOR, empIsDirectory);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpIsDirectory() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_IS_DIRECTOR, "");
    }

    public void setEmpYearId(String empIsDirectory) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_YEAR_ID, empIsDirectory);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpYearId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_YEAR_ID, "");
    }

    public void setEmpInstituteId(String empInstituteId) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_INSTITUTE_ID, empInstituteId);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpInstituteId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_INSTITUTE_ID, "");
    }

    public void setEmpImDomainName(String empImDomainName) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_IM_DOMAIN_NAME, empImDomainName);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpImDomainName() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_IM_DOMAIN_NAME, "");
    }

    public void setEmpUserStatus(String empUserStatus) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_USER_STATUS, empUserStatus);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpUserStatus() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_USER_STATUS, "");
    }

    public void setEmpPermanentCollege(String empPermanentCollege) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_PERMANENT_COLLEGE, empPermanentCollege);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpPermanentCollege() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_PERMANENT_COLLEGE, "");
    }

    public void setEmpDepartmentId(String empDepartmentId) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_DEPARTMENT_ID, empDepartmentId);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpDepartmentId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_DEPARTMENT_ID, "");
    }

    public void setEmpIsAdminOrNot(String empAdminOrNot) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_IS_ADMIN, empAdminOrNot);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpIsAdminOrNot() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_IS_ADMIN, "");
    }

    public void setEmpAcId(String acId) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_AC_ID, acId);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpAcId() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_AC_ID, "");
    }


    public void setLoginUserType(int loginUserType) {
        editorForStudentOrFaculty.putInt(CommonPreferencesConstants.LOGIN_USER_TYPE, loginUserType);
        editorForStudentOrFaculty.apply();
    }

    public int getLoginUserType() {
        return sharedPreferencesForStudentOrFaculty.getInt(CommonPreferencesConstants.LOGIN_USER_TYPE, 0);
    }


    /////
    public void setEmpIsHOD(String isHod) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_IS_HOD, isHod);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpIsHod() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_IS_HOD, "");
    }

    public void setEmpIsCoOrdinator(String isCoOrdinator) {
        editorForStudentOrFaculty.putString(EmployeeSidePreferencesConstants.EMP_IS_COORDINATOR, isCoOrdinator);
        editorForStudentOrFaculty.apply();
    }

    public String getEmpIsCoOrdinator() {
        return sharedPreferencesForStudentOrFaculty.getString(EmployeeSidePreferencesConstants.EMP_IS_COORDINATOR, "");
    }


    public void setFCMToken(String fcmToken) {
        editorForFirebaseFcmToken.putString(CommonPreferencesConstants.FCM_TOKEN, fcmToken);
        editorForFirebaseFcmToken.apply();
    }

    public String getFCMToken() {
        return sharedPreferencesForFirebaseFcmToken.getString(CommonPreferencesConstants.FCM_TOKEN, "");
    }


    public boolean checkIsStudentCurrentlyLoggedIn() {
        return sharedPreferencesForStudentOrFaculty.contains(StudentSidePreferencesConstants.STUDENT_ID) &&
                sharedPreferencesForStudentOrFaculty.contains(CommonPreferencesConstants.LOGIN_USER_TYPE);
    }

    public boolean checkIsFacultyCurrentlyLoggedIn() {
        return sharedPreferencesForStudentOrFaculty.contains(EmployeeSidePreferencesConstants.EMP_ID) &&
                sharedPreferencesForStudentOrFaculty.contains(CommonPreferencesConstants.LOGIN_USER_TYPE);
    }


    public void logoutStudentOrFaulty() {
        editorForStudentOrFaculty.clear();
        editorForStudentOrFaculty.commit();
    }

    public void setStudentUsername(String studentUsername) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_USER_NAME, studentUsername);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentUsername() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_USER_NAME, "");
    }

    public void setStudentPassword(String studentPassword) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_PASSWORD, studentPassword);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentPassword() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_PASSWORD, "");
    }

    public void setStudentId(String studentId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUDENT_ID, studentId);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUDENT_ID, "");
    }

    public void setDMId(String dmId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.DM_ID, dmId);
        editorForStudentOrFaculty.apply();
    }

    public String getDmId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.DM_ID, "");
    }

    public void setDmFullName(String dmFullName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.DM_FULL_NAME, dmFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getDmFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.DM_FULL_NAME, "");
    }

    public void setCourseId(String courseId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.COURSE_ID, courseId);
        editorForStudentOrFaculty.apply();
    }

    public String getCourseId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.COURSE_ID, "");
    }

    public void setCourseFullName(String courseFullName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.COURSE_FULL_NAME, courseFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getCourseFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.COURSE_FULL_NAME, "");
    }

    public void setSmId(String smId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SM_ID, smId);
        editorForStudentOrFaculty.apply();
    }

    public String getSmId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SM_ID, "");
    }

    public void setSmName(String smName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SM_NAME, smName);
        editorForStudentOrFaculty.apply();
    }

    public String getSmName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SM_NAME, "");
    }

    public void setAcId(String acId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.AC_ID, acId);
        editorForStudentOrFaculty.apply();
    }

    public String getAcId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.AC_ID, "");
    }

    public void setAcFullName(String acFullName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.AC_FULL_NAME, acFullName);
        editorForStudentOrFaculty.apply();
    }

    public String getAcFullName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.AC_FULL_NAME, "");
    }

    public void setSwdYearId(String swdYearId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SWD_YEAR_ID, swdYearId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdYearId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SWD_YEAR_ID, "");
    }

    public void setStatus(String status) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STATUS, status);
        editorForStudentOrFaculty.apply();
    }

    public String getStatus() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STATUS, "");
    }

    public void setAcCode(String acCode) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.AC_CODE, acCode);
        editorForStudentOrFaculty.apply();
    }

    public String getAcCode() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.AC_CODE, "");
    }

    public void setHostelCode(String hostelCode) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.HOSTEL_CODE, hostelCode);
        editorForStudentOrFaculty.apply();
    }

    public String getHostelCode() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.HOSTEL_CODE, "");
    }

    public void setStudentName(String studentName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUDENT_NAME, studentName);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUDENT_NAME, "");
    }

    public void setStudAdmissionNo(String studAdmissionNo) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_ADMISSION_NO, studAdmissionNo);
        editorForStudentOrFaculty.apply();
    }

    public String getStudAdmissionNo() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_ADMISSION_NO, "");
    }

    public void setStudentEnrollmentNo(String studentEnrollmentNo) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_ENROLLMENT_NO, studentEnrollmentNo);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentEnrollmentNo() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_ENROLLMENT_NO, "");
    }

    public void setStudentPhotoUrl(String studentPhotoUrl) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_PHOTO, studentPhotoUrl);
        editorForStudentOrFaculty.apply();
    }

    public String getStudentPhotoUrl() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_PHOTO, "");
    }

    public void setSwdDivisionId(String swdDivisionId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SWD_DIVISION_ID, swdDivisionId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdDivisionId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SWD_DIVISION_ID, "");
    }

    public void setSwdBatchId(String swdBatchId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SWD_BATCH_ID, swdBatchId);
        editorForStudentOrFaculty.apply();
    }

    public String getSwdBatchId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SWD_BATCH_ID, "");
    }

    public void setShiftId(String shiftId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.SHIFT_ID, shiftId);
        editorForStudentOrFaculty.apply();
    }

    public String getShiftId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.SHIFT_ID, "");
    }

    public void setImDomainName(String imDomainName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.IM_DOMAIN_NAME, imDomainName);
        editorForStudentOrFaculty.apply();
    }

    public String getImDomainName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.IM_DOMAIN_NAME, "");
    }

    public void setInstituteId(String instituteId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.INSTITUTE_ID, instituteId);
        editorForStudentOrFaculty.apply();
    }

    public String getInstituteId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.INSTITUTE_ID, "");
    }

    public void setFcFile(String fcFile) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.FC_FILE, fcFile);
        editorForStudentOrFaculty.apply();
    }

    public String getFcFile() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.FC_FILE, "");
    }

    public void setImExamDbName(String imExamDbName) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.IM_EXAM_DB_NAME, imExamDbName);
        editorForStudentOrFaculty.apply();
    }

    public String getImExamDbName() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.IM_EXAM_DB_NAME, "");
    }


    public void setStudMainSchoolId(String studMainSchoolId) {
        editorForStudentOrFaculty.putString(StudentSidePreferencesConstants.STUD_MAIN_SCHOOL_ID, studMainSchoolId);
        editorForStudentOrFaculty.apply();
    }

    public String getStudMainSchoolId() {
        return sharedPreferencesForStudentOrFaculty.getString(StudentSidePreferencesConstants.STUD_MAIN_SCHOOL_ID, "");
    }


    //=========  //CODE FOR STORING ONLY LOGGED IN STUDENT LIST //==================================================//

    private static final String LOGGED_IN_STUDENT_LIST_PREFERENCES = "logged_in_student_list"; //Please do not use this preference name in other places


    HashMap<String, ArrayList<String>> testHashMap = new HashMap();


    Gson gson = new Gson();

    public void storeStudentIdAndName(String studentEnNo, String studentPassword, String studentName, Context context) {


        ArrayList<String> studentNameAndPassArrayList = new ArrayList<>();
        studentNameAndPassArrayList.add(studentName);
        studentNameAndPassArrayList.add(studentPassword);

        testHashMap.put(studentEnNo, studentNameAndPassArrayList);

        String hashMapString = gson.toJson(testHashMap);

        SharedPreferences getReg = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editPref = getReg.edit();
        HashMap<String, ArrayList<String>> existingHashMap = getStudentIdAndName(context);
        if (existingHashMap == null) {
            existingHashMap = new HashMap<>();
        }
        existingHashMap.putAll(jsonToMap(hashMapString));
        hashMapString = gson.toJson(existingHashMap);
        editPref.putString(StudentSidePreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, hashMapString);
        editPref.apply();

    }

    public HashMap<String, ArrayList<String>> getStudentIdAndName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<String>>>() {
        }.getType();
        String storedHashMapString = preferences.getString(StudentSidePreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, "");
        return gson.fromJson(storedHashMapString, type);
    }

    private Map<String, ArrayList<String>> jsonToMap(String jsonString) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<?> keys = jObject.keys();

        while (keys.hasNext()) {

            JSONArray studentNameAndPassArray;
            ArrayList<String> studentNameAndPassArrayList = null;


            String key = (String) keys.next();
//            String value = null;
            try {
                studentNameAndPassArrayList = new ArrayList<>();
                studentNameAndPassArray = jObject.getJSONArray(key);

                for (int i = 0; i < studentNameAndPassArray.length(); i++) {
                    studentNameAndPassArrayList.add(studentNameAndPassArray.getString(i));
                }

//                value = jObject.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map.put(key, studentNameAndPassArrayList);

        }
        return map;
    }


    public void deleteStudentNameAndId(String studentEnNo, Context context) {
        HashMap<String, ArrayList<String>> existingHashMap = getStudentIdAndName(context);
        if (existingHashMap != null && existingHashMap.size() > 0) {
            existingHashMap.remove(studentEnNo);
        }
        SharedPreferences getReg = context.getSharedPreferences(LOGGED_IN_STUDENT_LIST_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editPref = getReg.edit();
        String hashMapString = gson.toJson(existingHashMap);
        editPref.putString(StudentSidePreferencesConstants.LOGGED_IN_STUDENT_PASSWORD_AND_NAME_JASON, hashMapString);
        editPref.apply();
    }


}
