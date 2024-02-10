package com.projectaty.data;

public class URLs {
    /*
        Centralized URLs
     */

    public static final String localHost = "http://10.0.2.2:5000/";
    /*
    Student Managemnt Requets URls
     */
    public static final String GET_USERS_URL = localHost +"user/studentsS/";
    public static final String GET_ONE_USER_URL = localHost +"user/studentsS/";
    public static final String GET_USER_BY_NAME_AND_PASSWORD_URL = localHost +"user/studentsS/";
    public static final String ADD_USER_URL = localHost +"user/studentsI";
    public static final String UPDATE_USER_URL = localHost +"user/studentsU";
    public static final String DELETE_USER_URL = localHost +"user/studentsD/";


    /*
    Tasks Managemnt Requets URls
    */
    public static final String GET_TASKS_BY_ID = localHost +"task/";
    public static final String POST_TASK = localHost +"task/add";
    public static final String PUT_TASK_BY_ID = localHost +"task/update/"; //follows a task_id
    public static final String DELETE_TASK_BY_ID = localHost +"task/delete/"; //follows a task_id
    public static final String GET_DONE = localHost +"task/done/"; //follows a proj. id
    public static final String GET_TODO = localHost +"task/todo/";  //follows a proj. id parameter
    public static final String GET_INPROGRESS = localHost +"task/inprogress/";  //follows a proj. id
    public static final String FIND_BY_KEYorMONTH = localHost +"task/find/";  //follows a proj. id

    public static final String GET_PROJECTS_URL = "project/all/";
    public static final String ADD_PROJECT_URL = "project/add/";
    public static final String UPDATE_PROJECT_URL = "project/update/";
    public static final String DELETE_PROJECT_URL = "project/delete/";
    public static final String GET_ONE_PROJECT_URL = "project/<int:ProjectID>";
    /*
    Teams Managemnt Requets URls
     */
    public static final String GET_TEAMS_URL = "";
    public static final String ADD_TEAM_URL = "";
    public static final String UPDATE_TEAM_URL = "";
    public static final String DELETE_TEAM_URL = "";

}
