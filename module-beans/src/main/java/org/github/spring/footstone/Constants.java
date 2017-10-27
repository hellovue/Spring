package org.github.spring.footstone;

import java.util.regex.Pattern;

import com.google.common.base.Joiner;

/**
 * 公共常量聚合接口,定义所有通用型常量.
 *
 * @author JYD_XL
 * @since 0.0.1-SNAPSHOT
 */
public interface Constants {
    /** default time zone. */
    String DEFAULT_TIME_ZONE = "GMT+08:00";

    /** default date style pattern. */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** date style pattern of day. */
    String FORMAT_DATE = "yyyy-MM-dd";

    String FORMAT_TIME = "HH:mm:ss";

    /** date style pattern of year. */
    String FORMAT_YEAR = "yyyy";

    /** date style pattern of month. */
    String FORMAT_MONTH = "yyyy-MM";

    /** date style pattern only day. */
    String FORMAT_ONLY_DAY = "dd";

    /** date style pattern only month. */
    String FORMAT_ONLY_MONTH = "MM";

    /** default param pattern. */
    Pattern PATTERN_PARAM = Pattern.compile("^[A-Za-z_$]+[A-Za-z0-9_$]*$");

    Pattern PATTERN_NULL = Pattern.compile("^(null)|[ ]*$");

    String EMPTY = "";

    String EMPTY_XML = "<root/>";

    String EMPTY_JSON = "{}";

    String CONTENT_DISPOSITION = "Content-Disposition";

    String ATTACHMENT_FILENAME = "attachment;fileName=";

    String SPACE = " ";

    String FIELD_SORT_ORDER = "sortOrder";

    String FIELD_SORT_NAME = "sortName";

    String FIELD_PAGE_SIZE = "pageSize";

    String FIELD_PAGE_FLAG = "pageFlag";

    String FIELD_PAGE_NUMBER = "pageNumber";

    /** default page size. */
    int SIZE = 10;

    /** default page number. */
    int NUMBER = 1;

    /** default page zero. */
    int PAGE_ZERO = 0;

    /** default page value. */
    boolean FLAG = true;

    Joiner JOINER_EMPTY = Joiner.on(EMPTY).skipNulls();

    Joiner JOINER_SPACE = Joiner.on(SPACE).skipNulls();

    Joiner JOINER = JOINER_SPACE;

    String UNDER_LINE = "_";

    String CALL_BACK = "callback";

    /** success----code. */
    int RET_OK_CODE = 0;

    /** success----message. */
    String RET_OK_MSG = "OK";

    /** failure----code. */
    int RET_ERROR_CODE = - 1;

    /** failure----message. */
    String RET_ERROR_MSG = "ERROR";

    /** default result list. */
    Object[] ARRAY = {};

    /** default result count. */
    long COUNT = 0L;

    /** prefix of jsp. */
    String PREFIX_JSP = "jsp/";

    /** prefix of html. */
    String PREFIX_HTML = "html/";

    String PREFIX_EXCEL = "excel";

    /** suffix of excel. */
    String SUFFIX_EXCEL = ".xlsx";

    String PREFIX_ZIP = "package";

    String SUFFIX_ZIP = ".zip";

    String FILE_NAME_ZIP = "package.zip";

    /** MethodDescription----AND. */
    String AND = "and";

    /** like. */
    String LIKE = "%";

    String VERSION = "version";

    String UNKNOWN = "unknown";

    String REDIRECT = "redirect:";

    String FORWARD = "forward:";

    String ROOT = "/";

    JSONMapperHolder JSON = JSONMapperHolder.getWebJSONMapper();

    XMLMapperHolder XML = XMLMapperHolder.getWebXMLMapper();

    String[][] INIT = {};

    String GET = "get";
}