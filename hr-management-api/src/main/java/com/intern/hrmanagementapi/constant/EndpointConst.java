package com.intern.hrmanagementapi.constant;

public final class EndpointConst {

<<<<<<< HEAD
  protected static final String ROOT_V1 = "/api/v1";

  public static final String EMPLOYEE_BASE_PATH = ROOT_V1 + "/employee";
  public static final String EMPLOYEE_LIST = "/batch";
  public static final String SEARCH = "/search";
=======
  private static final String ROOT_V1 = "/api/v1";

  public static final String AUTH_BASE_PATH = ROOT_V1 + "/auth";
  public static final String REGISTER = "/register";
  public static final String LOGIN = "/login";
  public static final String LOGOUT = "/logout";


  public static final String FILE_BASE_PATH = ROOT_V1 + "/files";
  public static final String FILE_UPLOAD = "";
  public static final String FILE_GET_BY_ID = "{id}";
  public static final String FILE_UPLOAD_ONE = "/upload";
  public static final String FILE_DOWNLOAD = "/download/{id}";
>>>>>>> 16ff767a41d34ece1b5653f01397e32f2380ebef
}
