package com.wix.spirinmikhail.helpers;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikhails on 08.01.2016
 */
public class SelectorsDataBase {

    public enum SelectorsKeys {
        NAV_BUTTON,
        COMMENT_TEXT_FIELD,
        CAT_ZERO_CHECKBOX,
        ADD_SELECTED_CATEGORY,
        SAVE_RETURN_BUTTON,

        COMMENT_LINE,
        COMMENT_NUMBER_IN_LINE,
        COMMENT_TEXT_IN_LINE,
        COMMENT_ACTIVE_IN_LINE,
        COMMENT_CATEGORIES_IN_LINE,

        FIRST_COMMENT_CHECKBOX,
        FIRST_COMMENT_TEXT, NUMBER_TEXT_FIELD,
        DELETE_POPUP_YES_BUTTON,
        NEXT_PAGE, FIRST_PAGE,
        ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT,
        REFRESH_LINK,
        MAIN_HEADER,

        ERROR_MESSAGE_FIELD,
        LINK_FOR_SORTING,
        DROPDOWN_CATEGORY,
        DROPDOWN_STATUS,
        FILTERING_BUTTON,
        STATUS_FIELD,
        DIVIDER,
        HEADERS,
    }

    private static Map<SelectorsKeys, Map> selectorsBase = new HashMap<SelectorsKeys, Map>(){{
        put(SelectorsKeys.NAV_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div#command-navigation input[value='%s']");
            put("XPATH", "//div[@id='command-navigation']/input[@value='%s']");
        }});

        put(SelectorsKeys.COMMENT_TEXT_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Text");
            put("XPATH", "//div[@class='commenteditor']/input[@id='Text']");
        }});

        put(SelectorsKeys.CAT_ZERO_CHECKBOX, new HashMap<String, String>() {{
            put("CSS", "div#alvailablecategories div:nth-of-type(1) input#Categories");
            put("XPATH", "//div[@id='alvailablecategories']/div[contains(.,'Cat0')]/input[@id='Categories']");
        }});

        put(SelectorsKeys.ADD_SELECTED_CATEGORY, new HashMap<String, String>() {{
            put("CSS", "div.selectbuttons input[value='>']");
            put("XPATH", "//div[@class='selectbuttons']/input[@value='>']");
        }});

        put(SelectorsKeys.SAVE_RETURN_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div#editor-navigation input[value='Save & Return']");
            put("XPATH", "//div[@id='editor-navigation']/input[@value='Save & Return']");
        }});
        
        put(SelectorsKeys.COMMENT_LINE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-row-style, tr.webgrid-alternating-row");
            put("XPATH", "//tr[@class='webgrid-row-style'] | //tr[@class='webgrid-alternating-row']");
        }});
        
        put(SelectorsKeys.COMMENT_NUMBER_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.numbercolumn");
            put("XPATH", "td[@class='numbercolumn']");
        }});
        
        put(SelectorsKeys.COMMENT_TEXT_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.textcolumn");
            put("XPATH", "td[@class='textcolumn']");
        }});
        
        put(SelectorsKeys.COMMENT_ACTIVE_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.inactivecolumn");
            put("XPATH", "td[@class='inactivecolumn']");
        }});

        put(SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.categorycolumn");
            put("XPATH", "td[@class='categorycolumn']");
        }});

        put(SelectorsKeys.FIRST_COMMENT_CHECKBOX, new HashMap<String, String>() {{
            put("CSS", "tbody tr:nth-of-type(1) td.checkedcolumn input[type=checkbox]");
            put("XPATH", "//tbody/tr[1]/td[@class='checkedcolumn']/input[@name='SelectedId']");
        }});

        put(SelectorsKeys.FIRST_COMMENT_TEXT, new HashMap<String, String>() {{
            put("CSS", "tbody tr:nth-of-type(1) td.textcolumn");
            put("XPATH", "//tbody/tr[1]/td[@class='textcolumn']");
        }});

        put(SelectorsKeys.NUMBER_TEXT_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Number");
            put("XPATH", "//div[@class='commenteditor']/input[@id='Number']");
        }});
        
        put(SelectorsKeys.DELETE_POPUP_YES_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div.ui-dialog-buttonset button:nth-of-type(1)");
            put("XPATH", "//div[@class='ui-dialog-buttonset']/button[1]");
        }});

        put(SelectorsKeys.NEXT_PAGE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer a:last-child");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*[last()]");
        }});

        put(SelectorsKeys.FIRST_PAGE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer a[href='/?page=1']");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*[contains(., '1')]");
        }});

        put(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer td *");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*");
        }});

        put(SelectorsKeys.REFRESH_LINK, new HashMap<String, String>() {{
            put("CSS", "div#logindisplay a");
            put("XPATH", "//div[@id='logindisplay']/a");
        }});

        put(SelectorsKeys.MAIN_HEADER, new HashMap<String, String>() {{
            put("CSS", "header div#title h1");
            put("XPATH", "//header/div[@id='title']/h1");
        }});

        put(SelectorsKeys.ERROR_MESSAGE_FIELD, new HashMap<String, String>() {{
            put("CSS", "div#errorfield");
            put("XPATH", "//div[@id='errorfield']");
        }});

        put(SelectorsKeys.LINK_FOR_SORTING, new HashMap<String, String>() {{
            put("CSS", "table.webgrid thead tr.webgrid-header th a[href^='/?sort=%s']");
            put("XPATH", "");
        }});

        put(SelectorsKeys.DROPDOWN_CATEGORY, new HashMap<String, String>() {{
            put("CSS", "select#SelectedCateg");
            put("XPATH", "");
        }});

        put(SelectorsKeys.DROPDOWN_STATUS, new HashMap<String, String>() {{
            put("CSS", "select#SelectedStatus");
            put("XPATH", "");
        }});

        put(SelectorsKeys.FILTERING_BUTTON, new HashMap<String, String>() {{
            put("CSS", "input#applybutton");
            put("XPATH", "");
        }});

        put(SelectorsKeys.STATUS_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Active");
            put("XPATH", "");
        }});

        put(SelectorsKeys.DIVIDER, new HashMap<String, String>() {{
            put("CSS", "section#main div hr");
            put("XPATH", "");
        }});

        put(SelectorsKeys.HEADERS, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-header th a");
            put("XPATH", "");
        }});





    }};

///////////////////////////*
//    // CSS selectors base
//    private static Map<SelectorsKeys, String> cssSelectors = new HashMap<SelectorsKeys, String>(){{
//        put(SelectorsKeys.NEW_BUTTON, "div#command-navigation input[value='New...']");
//        put(SelectorsKeys.DUPLICATE_BUTTON, "div#command-navigation input[value='Duplicate...']");
//        put(SelectorsKeys.EDIT_BUTTON, "div#command-navigation input[value='Edit...']");
//        put(SelectorsKeys.DELETE_BUTTON, "div#command-navigation input[value='Delete']");
//        put(SelectorsKeys.COMMENT_TEXT_FIELD, "div.commenteditor input#Text");
//        put(SelectorsKeys.CAT_ZERO_CHECKBOX, "div#alvailablecategories div:nth-of-type(1) input#Categories");
//        put(SelectorsKeys.ADD_SELECTED_CATEGORY, "div.selectbuttons input[value='>']");
//        put(SelectorsKeys.SAVE_RETURN_BUTTON, "div#editor-navigation input[value='Save & Return']");
//
//        put(SelectorsKeys.COMMENT_LINE, "tr.webgrid-row-style, tr.webgrid-alternating-row");
//        put(SelectorsKeys.COMMENT_NUMBER_IN_LINE, "td.numbercolumn");
//        put(SelectorsKeys.COMMENT_TEXT_IN_LINE, "td.textcolumn");
//        put(SelectorsKeys.COMMENT_ACTIVE_IN_LINE, "td.inactivecolumn");
//        put(SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, "td.categorycolumn");
//
//        put(SelectorsKeys.FIRST_COMMENT_CHECKBOX, "tbody tr:nth-of-type(1) td.checkedcolumn input[type=checkbox]");
//        put(SelectorsKeys.FIRST_COMMENT_TEXT, "tbody tr:nth-of-type(1) td.textcolumn");
//        put(SelectorsKeys.NUMBER_TEXT_FIELD, "div.commenteditor input#Number");
//
//        put(SelectorsKeys.DELETE_POPUP_YES_BUTTON, "div.ui-dialog-buttonset button:nth-of-type(1)");
//
//        put(SelectorsKeys.NEXT_PAGE, "tr.webgrid-footer a:last-child");
//        put(SelectorsKeys.FIRST_PAGE, "tr.webgrid-footer a[href='/?page=1']");
//        put(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT, "tr.webgrid-footer td *");
//        put(SelectorsKeys.REFRESH_LINK, "div#logindisplay a");
//        put(SelectorsKeys.MAIN_HEADER, "header div#title h1");
//    }};
//
//    // xpath selectors base
//    private static Map<SelectorsKeys, String> xpathSelectors = new HashMap<SelectorsKeys, String>(){{
//        put(SelectorsKeys.NEW_BUTTON, "//div[@id='command-navigation']/input[@value='New...']");
//        put(SelectorsKeys.DUPLICATE_BUTTON, "//div[@id='command-navigation']/input[@value='Duplicate...']");
//        put(SelectorsKeys.EDIT_BUTTON, "//div[@id='command-navigation']/input[@value='Edit...']");
//        put(SelectorsKeys.DELETE_BUTTON, "//div[@id='command-navigation']/input[@value='Delete']");
//        put(SelectorsKeys.COMMENT_TEXT_FIELD, "//div[@class='commenteditor']/input[@id='Text']");
//        put(SelectorsKeys.CAT_ZERO_CHECKBOX, "//div[@id='alvailablecategories']/div[contains(.,'Cat0')]/input[@id='Categories']");
//        put(SelectorsKeys.ADD_SELECTED_CATEGORY, "//div[@class='selectbuttons']/input[@value='>']");
//        put(SelectorsKeys.SAVE_RETURN_BUTTON, "//div[@id='editor-navigation']/input[@value='Save & Return']");
//
//        put(SelectorsKeys.COMMENT_LINE, "//tr[@class='webgrid-row-style' or 'webgrid-alternating-row']/td[@class='textcolumn']");
//        put(SelectorsKeys.COMMENT_NUMBER_IN_LINE, "");
//        put(SelectorsKeys.COMMENT_TEXT_IN_LINE, "");
//        put(SelectorsKeys.COMMENT_ACTIVE_IN_LINE, "");
//        put(SelectorsKeys.COMMENT_CATEGORIES_IN_LINE, "");
//
//        put(SelectorsKeys.FIRST_COMMENT_CHECKBOX, "//tbody/tr[1]/td[@class='checkedcolumn']/input[@name='SelectedId']");
//        put(SelectorsKeys.FIRST_COMMENT_TEXT, "//tbody/tr[1]/td[@class='textcolumn']");
//        put(SelectorsKeys.NUMBER_TEXT_FIELD, "//div[@class='commenteditor']/input[@id='Number']");
//
//        put(SelectorsKeys.DELETE_POPUP_YES_BUTTON, "//div[@class='ui-dialog-buttonset']/button[1]");
//
//        put(SelectorsKeys.NEXT_PAGE, "//tr[@class='webgrid-footer']/td/*[last()]");
//        put(SelectorsKeys.FIRST_PAGE, "//tr[@class='webgrid-footer']/td/*[contains(., '1')]");
//        put(SelectorsKeys.ALL_PAGINATION_ELEMENTS_WITHOUT_TEXT, "//tr[@class='webgrid-footer']/td/*");
//        put(SelectorsKeys.REFRESH_LINK, "//div[@id='logindisplay']/a");
//        put(SelectorsKeys.MAIN_HEADER, "//header/div[@id='title']/h1");
//    }};
//////////////////////*/

    public static By getSelector(SelectorsKeys query, String type) {
        By result = null;
        if (type.equals("CSS")) result = By.cssSelector((String)selectorsBase.get(query).get(type));
        if (type.equals("XPATH")) result = By.xpath((String) selectorsBase.get(query).get(type));
        return result;
    }

    public static By getSelector(SelectorsKeys query, String type, String parameter) {
        By result = null;
        String queryWithParsedParam = String.format((String)selectorsBase.get(query).get(type), parameter);
        if (type.equals("CSS")) result = By.cssSelector(queryWithParsedParam);
        if (type.equals("XPATH")) result = By.xpath(queryWithParsedParam);
        return result;

    }


}
