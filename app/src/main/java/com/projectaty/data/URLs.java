package com.projectaty.data;

public class URLs {
    /*
        Centralized URLs
     */

    public static final String localHost = "http://10.0.2.2:5000/";
    /*
    Student Managemnt Requets URls
     */
    public static final String GET_USERS_URL = localHost +"user/all";
    public static final String GET_ONE_USER_URL = localHost +"user/";
    public static final String GET_USER_BY_NAME_AND_PASSWORD_URL = localHost +"user/login/";
    public static final String ADD_USER_URL = localHost +"user/add";
    public static final String UPDATE_USER_URL = localHost +"user/update";
    public static final String DELETE_USER_URL = localHost +"user/delete/";

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
    public static final String ADD_TEAM_URL = localHost +"team/add/";
    public static final String UPDATE_TEAM_URL = localHost +"team/update/";
    public static final String DELETE_TEAM_URL = localHost +"team/delete/";
    public static final String GET_ONE_TEAM_URL = localHost +"team/";
    public static final String FIND_TEAM_BY_ID_OR_NAME_URL = localHost +"team/";
    public static final String GET_TEAMS_URL = localHost +"team/all";
    public static final String GET_IS_PRIVATE_URL = localHost +"team/";
    public static final String ADD_TEAM_MEMBER_URL = localHost +"team/";
    public static final String GET_TEAM_MEMBERS_URL = localHost +"team/members/";
    public static final String ADD_TEAM_PROJECT_URL = localHost +"team/";
    public static final String GET_TEAM_PROJECTS_URL = localHost +"team/";
    public static final String DELETE_TEAM_MEMBER_URL = localHost +"team/";
    public static final String DELETE_TEAM_PROJECT_URL = localHost +"team/";
}
