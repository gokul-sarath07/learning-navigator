package com.crio.learning_navigator.config;

public class PathConstants {
    public static final String API_BASE_PATH = "/api/v1";

//    Student Paths
    public static final String REGISTER_STUDENT = "/student";
    public static final String GET_ALL_STUDENTS = "/student/all";
    public static final String GET_STUDENT = "/student/{studentId}";
    public static final String ENROLL_SUBJECT = "/student/enroll";
    public static final String UNENROLL_SUBJECT = "/student/unenroll";

    //    Subject Paths
    public static final String ADD_SUBJECT = "/subject";
    public static final String DELETE_SUBJECT = "/subject/{subjectId}";
    public static final String GET_ALL_SUBJECTS = "/subject/all";
    public static final String GET_SUBJECT = "/subject/{subjectId}";

//    Exam Paths
    public static final String REGISTER_EXAM = "/exam/register";
    public static final String DEREGISTER_EXAM = "/exam/deregister";
    public static final String GET_ALL_EXAMS = "/exam/all";
}
