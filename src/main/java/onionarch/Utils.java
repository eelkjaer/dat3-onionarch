package onionarch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utils {

  public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  private Utils() {}
}
