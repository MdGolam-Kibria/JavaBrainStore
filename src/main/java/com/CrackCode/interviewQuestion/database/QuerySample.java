package com.CrackCode.interviewQuestion.database;

/**
 * Comment All code only for java version
 */
public class QuerySample {
 /*   *//**
     * For get multiple table with hard business handle with single query(see brac bank standUpMeeting project QueryConstraint class)
     *//*


    *//**
     * For UPDATE multiple table sample (Sample Collect From My Brac bank project)
     *//*
    public static final String UPDATE_COMMENT_AND_DAILY_STATUS_CREATOR_BY_CREATED_BY_ID = """
            UPDATE comment c,
                daily_status ds
            SET
                c.creator = :creatorName,
                ds.creator = :creatorName
            WHERE
                c.created_by = :createdById AND ds.created_by = :createdById
            """;
    *//**
     * For UPDATE multiple table using joining query with much more business logic
     *//*
    public static final String UPDATE_JOIN_TABLE= """
            UPDATE t1
            INNER JOIN t2 ON t2.t1_id = t1.id
            INNER JOIN t3 ON t2.t3_id = t3.id
            SET t1.a = 'something',
                t2.b = 42,
                t3.c = t2.c
            WHERE t1.a = 'blah';
            """;
    *//**
     * For UPDATE with join and sub join logic
     *//*
    public static final String UPDATE_SUB_JOIN= """
            UPDATE Region
            SET
              Region.Column1 = r.Column1
              Region.Column2 = r.Column2
            FROM
              Region
            INNER JOIN
             (
               SELECT
                    Column1,
                    Column2
               FROM Region
               WHERE (your condition here)
              ) r ON r.ID = Region.ID
            """;

    *//**
     * For DELETE data from multiple table using join sample 1
     *//*
    public static final String DELETE_MULTIPLE_TABLE_JOIN= """
            DELETE T1, T2
            FROM T1
            INNER JOIN T2 ON T1.key = T2.key
            WHERE condition
            """;

    *//**
     * For DELETE data from multiple table using join sample 2
     *//*
    public static final String DELETE_MULTIPLE_TABLE= """
            DELETE
                            FROM
                                Student,
                                Enrollment USING Student
                            INNER JOIN
                                Enrollment
                                    ON Student.studentId = Enrollment.studentId
                            WHERE
                                Student.studentId= 51;
            """;
*/
}
