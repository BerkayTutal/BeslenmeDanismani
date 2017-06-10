package tr.com.berkaytutal.beslenmedanismani.Utils;

/**
 * Created by berka on 15.05.2017.
 */

public interface PublicVariables {
    int TYPE_LISTINGS_ALL = 1;
    int TYPE_LISTINGS_HOMEPAGE  = 2;

    String CHEST = "Chest";




    int FILTER_BUTTON_TYPE_PROGRAM = 1;
    int FILTER_BUTTON_TYPE_USER = 2;


    int homepageListingCount = 3;

    String programDiffURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/getProgramDiffParameters";
    String programCategoriesURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/getProgramSpecParameters";
    String allProgramsURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/getAllPrograms/";
    String allUsersURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/getAllTrainers/";
    String loginURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/";
    String registerURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/addNewUser";
    String userUpdateURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/updateUserSimpleData";
    String bodyRateUpdateURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/updateUserBodyRates";
    //getProgramDetailsURL + programID/userID
    String getProgramDetailsURL = "http://denememustafa.azurewebsites.net/bitirmewebservice/webapi/myresource/getProgramDetails/";


}
