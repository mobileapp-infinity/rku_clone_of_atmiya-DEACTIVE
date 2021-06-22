package com.infinity.infoway.rkuniversity.student.exam.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExaminationScheduleProgramWiseTimetablePojo {

    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public class Table {

        @SerializedName("Srno")
        @Expose
        private Integer srno;
        @SerializedName("paper_id")
        @Expose
        private Integer paperId;
        @SerializedName("tt_program_id")
        @Expose
        private Integer ttProgramId;
        @SerializedName("tt_paper")
        @Expose
        private Integer ttPaper;
        @SerializedName("tt_date")
        @Expose
        private String ttDate;
        @SerializedName("tt_day")
        @Expose
        private String ttDay;
        @SerializedName("Exam_time")
        @Expose
        private String examTime;
        @SerializedName("tt_start_time")
        @Expose
        private String ttStartTime;
        @SerializedName("tt_end_time")
        @Expose
        private String ttEndTime;
        @SerializedName("Subject_Paper")
        @Expose
        private String subjectPaper;
        @SerializedName("Subject_Code")
        @Expose
        private String subjectCode;
        @SerializedName("year_name")
        @Expose
        private String yearName;
        @SerializedName("Semester_Name")
        @Expose
        private String semesterName;
        @SerializedName("course")
        @Expose
        private String course;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("university_line_1")
        @Expose
        private String universityLine1;
        @SerializedName("university_line_3")
        @Expose
        private String universityLine3;
        @SerializedName("university_line_4")
        @Expose
        private String universityLine4;
        @SerializedName("university_logo")
        @Expose
        private String universityLogo;
        @SerializedName("university_name")
        @Expose
        private String universityName;
        @SerializedName("Name_of_Institute")
        @Expose
        private String nameOfInstitute;
        @SerializedName("Name_of_College")
        @Expose
        private String nameOfCollege;
        @SerializedName("Name_of_Faculty")
        @Expose
        private String nameOfFaculty;
        @SerializedName("Name_of_Course")
        @Expose
        private String nameOfCourse;
        @SerializedName("Name_of_Semester")
        @Expose
        private String nameOfSemester;
        @SerializedName("Name_of_Department")
        @Expose
        private String nameOfDepartment;
        @SerializedName("Name_of_Division")
        @Expose
        private String nameOfDivision;
        @SerializedName("Name_of_Batch")
        @Expose
        private String nameOfBatch;
        @SerializedName("Name_of_Subject")
        @Expose
        private String nameOfSubject;
        @SerializedName("institute_logo_path")
        @Expose
        private String instituteLogoPath;
        @SerializedName("ac_full_name")
        @Expose
        private String acFullName;
        @SerializedName("im_full_name")
        @Expose
        private String imFullName;
        @SerializedName("college_logo_path")
        @Expose
        private String collegeLogoPath;
        @SerializedName("institute_logo")
        @Expose
        private String instituteLogo;
        @SerializedName("college_logo")
        @Expose
        private String collegeLogo;

        public Integer getSrno() {
            return srno;
        }

        public void setSrno(Integer srno) {
            this.srno = srno;
        }

        public Integer getPaperId() {
            return paperId;
        }

        public void setPaperId(Integer paperId) {
            this.paperId = paperId;
        }

        public Integer getTtProgramId() {
            return ttProgramId;
        }

        public void setTtProgramId(Integer ttProgramId) {
            this.ttProgramId = ttProgramId;
        }

        public Integer getTtPaper() {
            return ttPaper;
        }

        public void setTtPaper(Integer ttPaper) {
            this.ttPaper = ttPaper;
        }

        public String getTtDate() {
            return ttDate;
        }

        public void setTtDate(String ttDate) {
            this.ttDate = ttDate;
        }

        public String getTtDay() {
            return ttDay;
        }

        public void setTtDay(String ttDay) {
            this.ttDay = ttDay;
        }

        public String getExamTime() {
            return examTime;
        }

        public void setExamTime(String examTime) {
            this.examTime = examTime;
        }

        public String getTtStartTime() {
            return ttStartTime;
        }

        public void setTtStartTime(String ttStartTime) {
            this.ttStartTime = ttStartTime;
        }

        public String getTtEndTime() {
            return ttEndTime;
        }

        public void setTtEndTime(String ttEndTime) {
            this.ttEndTime = ttEndTime;
        }

        public String getSubjectPaper() {
            return subjectPaper;
        }

        public void setSubjectPaper(String subjectPaper) {
            this.subjectPaper = subjectPaper;
        }

        public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public String getYearName() {
            return yearName;
        }

        public void setYearName(String yearName) {
            this.yearName = yearName;
        }

        public String getSemesterName() {
            return semesterName;
        }

        public void setSemesterName(String semesterName) {
            this.semesterName = semesterName;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUniversityLine1() {
            return universityLine1;
        }

        public void setUniversityLine1(String universityLine1) {
            this.universityLine1 = universityLine1;
        }

        public String getUniversityLine3() {
            return universityLine3;
        }

        public void setUniversityLine3(String universityLine3) {
            this.universityLine3 = universityLine3;
        }

        public String getUniversityLine4() {
            return universityLine4;
        }

        public void setUniversityLine4(String universityLine4) {
            this.universityLine4 = universityLine4;
        }

        public String getUniversityLogo() {
            return universityLogo;
        }

        public void setUniversityLogo(String universityLogo) {
            this.universityLogo = universityLogo;
        }

        public String getUniversityName() {
            return universityName;
        }

        public void setUniversityName(String universityName) {
            this.universityName = universityName;
        }

        public String getNameOfInstitute() {
            return nameOfInstitute;
        }

        public void setNameOfInstitute(String nameOfInstitute) {
            this.nameOfInstitute = nameOfInstitute;
        }

        public String getNameOfCollege() {
            return nameOfCollege;
        }

        public void setNameOfCollege(String nameOfCollege) {
            this.nameOfCollege = nameOfCollege;
        }

        public String getNameOfFaculty() {
            return nameOfFaculty;
        }

        public void setNameOfFaculty(String nameOfFaculty) {
            this.nameOfFaculty = nameOfFaculty;
        }

        public String getNameOfCourse() {
            return nameOfCourse;
        }

        public void setNameOfCourse(String nameOfCourse) {
            this.nameOfCourse = nameOfCourse;
        }

        public String getNameOfSemester() {
            return nameOfSemester;
        }

        public void setNameOfSemester(String nameOfSemester) {
            this.nameOfSemester = nameOfSemester;
        }

        public String getNameOfDepartment() {
            return nameOfDepartment;
        }

        public void setNameOfDepartment(String nameOfDepartment) {
            this.nameOfDepartment = nameOfDepartment;
        }

        public String getNameOfDivision() {
            return nameOfDivision;
        }

        public void setNameOfDivision(String nameOfDivision) {
            this.nameOfDivision = nameOfDivision;
        }

        public String getNameOfBatch() {
            return nameOfBatch;
        }

        public void setNameOfBatch(String nameOfBatch) {
            this.nameOfBatch = nameOfBatch;
        }

        public String getNameOfSubject() {
            return nameOfSubject;
        }

        public void setNameOfSubject(String nameOfSubject) {
            this.nameOfSubject = nameOfSubject;
        }

        public String getInstituteLogoPath() {
            return instituteLogoPath;
        }

        public void setInstituteLogoPath(String instituteLogoPath) {
            this.instituteLogoPath = instituteLogoPath;
        }

        public String getAcFullName() {
            return acFullName;
        }

        public void setAcFullName(String acFullName) {
            this.acFullName = acFullName;
        }

        public String getImFullName() {
            return imFullName;
        }

        public void setImFullName(String imFullName) {
            this.imFullName = imFullName;
        }

        public String getCollegeLogoPath() {
            return collegeLogoPath;
        }

        public void setCollegeLogoPath(String collegeLogoPath) {
            this.collegeLogoPath = collegeLogoPath;
        }

        public String getInstituteLogo() {
            return instituteLogo;
        }

        public void setInstituteLogo(String instituteLogo) {
            this.instituteLogo = instituteLogo;
        }

        public String getCollegeLogo() {
            return collegeLogo;
        }

        public void setCollegeLogo(String collegeLogo) {
            this.collegeLogo = collegeLogo;
        }

    }

}
