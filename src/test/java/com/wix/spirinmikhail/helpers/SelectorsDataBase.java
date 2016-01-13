package com.wix.spirinmikhail.helpers;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikhails on 08.01.2016
 */
public class SelectorsDataBase {

    public enum MainPg {
        NAV_BUTTON,
        DROPDOWN_CATEGORY,
        DROPDOWN_STATUS,
        REFRESH_LINK,
        FILTERING_BUTTON,
        DIVIDER,
        HEADERS,
        MAIN_HEADER,
    }

    public enum EditPg {
        ERROR_MESSAGE_FIELD,
        COMMENT_TEXT_FIELD,
        NUMBER_TEXT_FIELD,
        STATUS_FIELD,

        CAT_ZERO_CHECKBOX,
        ADD_SELECTED_CATEGORY_BUTTON,
        SAVE_RETURN_BUTTON,

        DELETE_POPUP_YES_BUTTON,
    }

    public enum TblSel {
        // links for sorting in header
        LINKS_FOR_SORTING,

        // Should be obsolete after getting first comment with conditional
        FIRST_COMMENT_CHECKBOX,
        FIRST_COMMENT_TEXT,

        // for table iteration
        COMMENT_LINE,
        COMMENT_CHECKBOX,
        COMMENT_NUMBER_IN_LINE,
        COMMENT_TEXT_IN_LINE,
        COMMENT_ACTIVE_IN_LINE,
        COMMENT_CATEGORIES_IN_LINE,

        // table pagination
        PAGINATION_NEXT_PAGE,
        PAGINATION_FIRST_PAGE,
        PAGINATION_ELEMENTS_WITHOUT_TEXT,
    }

    private static Map<Enum, Map> selectorsBase = new HashMap<Enum, Map>(){{
        put(MainPg.NAV_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div#command-navigation input[value='%s']");
            put("XPATH", "//div[@id='command-navigation']/input[@value='%s']");
        }});

        put(EditPg.COMMENT_TEXT_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Text");
            put("XPATH", "//div[@class='commenteditor']/input[@id='Text']");
        }});

        put(EditPg.CAT_ZERO_CHECKBOX, new HashMap<String, String>() {{
            put("CSS", "div#alvailablecategories div:nth-of-type(1) input#Categories");
            put("XPATH", "//div[@id='alvailablecategories']/div[contains(.,'Cat0')]/input[@id='Categories']");
        }});

        put(EditPg.ADD_SELECTED_CATEGORY_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div.selectbuttons input[value='>']");
            put("XPATH", "//div[@class='selectbuttons']/input[@value='>']");
        }});

        put(EditPg.SAVE_RETURN_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div#editor-navigation input[value='Save & Return']");
            put("XPATH", "//div[@id='editor-navigation']/input[@value='Save & Return']");
        }});
        
        put(TblSel.COMMENT_LINE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-row-style, tr.webgrid-alternating-row");
            put("XPATH", "//tr[@class='webgrid-row-style'] | //tr[@class='webgrid-alternating-row']");
        }});
        
        put(TblSel.COMMENT_NUMBER_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.numbercolumn");
            put("XPATH", "td[@class='numbercolumn']");
        }});
        
        put(TblSel.COMMENT_TEXT_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.textcolumn");
            put("XPATH", "td[@class='textcolumn']");
        }});
        
        put(TblSel.COMMENT_ACTIVE_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.inactivecolumn");
            put("XPATH", "td[@class='inactivecolumn']");
        }});

        put(TblSel.COMMENT_CATEGORIES_IN_LINE, new HashMap<String, String>() {{
            put("CSS", "td.categorycolumn");
            put("XPATH", "td[@class='categorycolumn']");
        }});

        put(TblSel.FIRST_COMMENT_CHECKBOX, new HashMap<String, String>() {{
            put("CSS", "tbody tr:nth-of-type(1) td.checkedcolumn input[type=checkbox]");
            put("XPATH", "//tbody/tr[1]/td[@class='checkedcolumn']/input[@name='SelectedId']");
        }});

        put(TblSel.COMMENT_CHECKBOX, new HashMap<String, String>() {{
            put("CSS", "td.checkedcolumn input[type=checkbox]");
            put("XPATH", "td[@class='checkedcolumn']/input[@name='SelectedId']");
        }});

        put(TblSel.FIRST_COMMENT_TEXT, new HashMap<String, String>() {{
            put("CSS", "tbody tr:nth-of-type(1) td.textcolumn");
            put("XPATH", "//tbody/tr[1]/td[@class='textcolumn']");
        }});

        put(EditPg.NUMBER_TEXT_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Number");
            put("XPATH", "//div[@class='commenteditor']/input[@id='Number']");
        }});
        
        put(EditPg.DELETE_POPUP_YES_BUTTON, new HashMap<String, String>() {{
            put("CSS", "div.ui-dialog-buttonset button:nth-of-type(1)");
            put("XPATH", "//div[@class='ui-dialog-buttonset']/button[1]");
        }});

        put(TblSel.PAGINATION_NEXT_PAGE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer a:last-child");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*[last()]");
        }});

        put(TblSel.PAGINATION_FIRST_PAGE, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer a[href='/?page=1']");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*[contains(., '1')]");
        }});

        put(TblSel.PAGINATION_ELEMENTS_WITHOUT_TEXT, new HashMap<String, String>() {{
            put("CSS", "tr.webgrid-footer td *");
            put("XPATH", "//tr[@class='webgrid-footer']/td/*");
        }});

        put(MainPg.REFRESH_LINK, new HashMap<String, String>() {{
            put("CSS", "div#logindisplay a");
            put("XPATH", "//div[@id='logindisplay']/a");
        }});

        put(MainPg.MAIN_HEADER, new HashMap<String, String>() {{
            put("CSS", "header div#title h1");
            put("XPATH", "//header/div[@id='title']/h1");
        }});

        put(EditPg.ERROR_MESSAGE_FIELD, new HashMap<String, String>() {{
            put("CSS", "div#errorfield");
            put("XPATH", "//div[@id='errorfield']");
        }});

        put(TblSel.LINKS_FOR_SORTING, new HashMap<String, String>() {{
            put("CSS", "table.webgrid thead tr.webgrid-header th a[href^='/?sort=%s']");
            put("XPATH", "");
        }});

        put(MainPg.DROPDOWN_CATEGORY, new HashMap<String, String>() {{
            put("CSS", "select#SelectedCateg");
            put("XPATH", "");
        }});

        put(MainPg.DROPDOWN_STATUS, new HashMap<String, String>() {{
            put("CSS", "select#SelectedStatus");
            put("XPATH", "");
        }});

        put(MainPg.FILTERING_BUTTON, new HashMap<String, String>() {{
            put("CSS", "input#applybutton");
            put("XPATH", "");
        }});

        put(EditPg.STATUS_FIELD, new HashMap<String, String>() {{
            put("CSS", "div.commenteditor input#Active");
            put("XPATH", "");
        }});

        put(MainPg.DIVIDER, new HashMap<String, String>() {{
            put("CSS", "section#main div hr");
            put("XPATH", "");
        }});

    }};

    public static By getSelector(Enum query, String type) {
        By result = null;
        if (type.equals("CSS")) result = By.cssSelector((String)selectorsBase.get(query).get(type));
        if (type.equals("XPATH")) result = By.xpath((String) selectorsBase.get(query).get(type));
        return result;
    }

    public static By getSelector(Enum query, String type, String parameter) {
        By result = null;
        String queryWithParsedParam = String.format((String)selectorsBase.get(query).get(type), parameter);
        if (type.equals("CSS")) result = By.cssSelector(queryWithParsedParam);
        if (type.equals("XPATH")) result = By.xpath(queryWithParsedParam);
        return result;

    }


}
