package fr.eni.encheres.dal;

public abstract class CodesResultatDAL {

	public static final int SELECT_OBJECT_NULL = 10000;
	public static final int LECTURE_ARTICLE_INEXISTANT = 10001;
	public static final int SELECT_ARTICLE_ECHEC = 10002;
	public static final int LECTURE_USER_INEXISTANT = 10003;
	public static final int SELECT_USER_ECHEC = 10004;
	
	public static final int SELECT_ALL_ECHEC = 10005;
	
	public static final int SELECT_NAME_ECHEC = 10006;
	
	public static final int UPDATE_OBJECT_NULL = 10010;
	public static final int UPDATE_ARTICLE_ECHEC = 10011;
	public static final int UPDATE_USER_ECHEC = 10012;

	public static final int INSERT_OBJECT_NULL = 10020;
	public static final int INSERT_ARTICLE_ECHEC = 10021;	
	public static final int INSERT_USER_ECHEC = 10022;	

	public static final int DELETE_OBJECT_NULL = 10030;
	public static final int DELETE_ARTICLE_ECHEC = 10031;
	public static final int DELETE_USER_ECHEC = 10032;
	
	public static final int CONNECT_USER_ECHEC = 10050;
	public static final int TOKEN_NOT_CREATED = 10051;
	public static final int TOKEN_NOT_FOUND = 10052;

}
