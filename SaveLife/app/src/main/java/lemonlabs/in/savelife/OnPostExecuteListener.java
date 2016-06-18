package lemonlabs.in.savelife;

/**
 * Created by Santhosh.Joseph on 17-06-2016.
 */
public interface OnPostExecuteListener {
    public void onSuccess(ModelClassMapper model);
    public void onFailure();
}
