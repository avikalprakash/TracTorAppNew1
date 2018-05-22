package com.lueinfo.tractorapp.Utils;

/**
 * Created by lue on 30-08-2017.
 */

public class Urls {

    public static String serverAddress = "http://condoassist2u.com/tractorapp";

    public static String register = serverAddress + "/api/signup";
    public static String login = serverAddress + "/api/login";

    public static String event = serverAddress + "/api/event/getAllEvents";

    public static String resetpass = serverAddress + "/api/resetPassword";

    public static String uploadgal = serverAddress + "/api/galleryupload";

    public static String showgal = serverAddress + "/api/galleryshow";

    public static String fetchprofiledetail = serverAddress + "/api/getProfile/";
    public static String editprofile = serverAddress + "/api/editProfile";
    public static String introducer = serverAddress + "/api/confirmIntroducer";
    public static String introducerfetchdetail = serverAddress + "/api/getQrCode/";
    public static String tablereserve = serverAddress + "/api/tableReservation";
    public static String getNewsAndBuletin = serverAddress + "/api/getNewsAndBuletin";
    public static String newsBuletinSlideshow = serverAddress + "/api/newsBuletinSlideshow";
    public static String getSpecialPromotion = serverAddress + "/api/getSpecialPromotion";
    public static String getPaidItems = serverAddress + "/api/getPaidItems";
    public static String getItemDetails = serverAddress + "/api/getItemDetails";


}
