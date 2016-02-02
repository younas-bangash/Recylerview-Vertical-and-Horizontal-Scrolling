package com.uowd.sport.nestdrecylerviews_vert_horizontal;

/**
 * Created by Developer on 1/24/2016.
 */
public class GetMixCatalougeData {
    private String Item1Value;
    private String Item2Value;
    private String Item3Value;
    private String Item4Value;
    private String Item5Value;
    private String Item6Value;
    private String Item7Value;
    private String FooterDetails;





    public GetMixCatalougeData(String item1Value, String item2Value, String item3Value, String item4Value,
                               String item5Value, String item6Value, String item7Value,String footer) {
        Item1Value = item1Value;
        Item2Value = item2Value;
        Item3Value = item3Value;
        Item4Value = item4Value;
        Item5Value = item5Value;
        Item6Value = item6Value;
        Item7Value = item7Value;
        FooterDetails=footer;
    }

    public String getFooterDetails() {
        return FooterDetails;
    }

    public String getItem1Value() {
        return Item1Value;
    }

    public String getItem2Value() {
        return Item2Value;
    }

    public String getItem3Value() {
        return Item3Value;
    }

    public String getItem4Value() {
        return Item4Value;
    }

    public String getItem5Value() {
        return Item5Value;
    }

    public String getItem6Value() {
        return Item6Value;
    }

    public String getItem7Value() {
        return Item7Value;
    }
}
