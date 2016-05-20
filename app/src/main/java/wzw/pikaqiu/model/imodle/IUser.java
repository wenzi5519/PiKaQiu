package wzw.pikaqiu.model.imodle;

public interface IUser {
	String getName();

	String getPasswd();

	int checkUserValidity(String name, String passwd);
}