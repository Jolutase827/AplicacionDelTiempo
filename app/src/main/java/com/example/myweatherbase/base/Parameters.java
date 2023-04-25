package com.example.myweatherbase.base;

public class Parameters {

    public final static String API = "9991e5046fd15ec7c6c5fbcf91e33d01";
    public static String lang = "es";
    public  static String units = "metric";
    public final static String URL = "https://api.openweathermap.org/data/2.5/";

    public static String urlOptions =   "forecast?appid=" + API + "&lang=" + lang + "&units=" + units;

    public final static String ICON_URL_PRE = "http://openweathermap.org/img/wn/";
    public static final String ICON_URL_POST = "@2x.png";

}
