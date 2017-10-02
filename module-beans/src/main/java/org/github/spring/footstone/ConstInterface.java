package org.github.spring.footstone;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * 公共常量聚合接口,定义所有通用型常量.
 *
 * @author JYD_XL
 */
public interface ConstInterface {
  /** default time zone. */
  String DEFAULT_TIME_ZONE = "GMT+08:00";

  /** default date format pattern. */
  String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /** date format pattern of day. */
  String FORMAT_DATE = "yyyy-MM-dd";

  String FORMAT_TIME = "HH:mm:ss";

  /** date format pattern of year. */
  String FORMAT_YEAR = "yyyy";

  /** date format pattern of month. */
  String FORMAT_MONTH = "yyyy-MM";

  /** date format pattern only day. */
  String FORMAT_ONLY_DAY = "dd";

  /** date format pattern only month. */
  String FORMAT_ONLY_MONTH = "MM";

  /** default param pattern. */
  Pattern PATTERN_PARAM = Pattern.compile("^[A-Za-z_$]+[A-Za-z0-9_$]*$");

  Pattern PATTERN_NULL = Pattern.compile("^(null)|[ ]*$");

  String EMPTY = "";

  String EMPTY_XML = "<root/>";

  String EMPTY_JSON = "{}";

  String ASC = "ASC";

  String DESC = "DESC";

  String SPACE = " ";

  String FIELD_SORT_ORDER = "sortOrder";

  String FIELD_SORT_NAME = "sortName";

  String FIELD_PAGE_SIZE = "pageSize";

  String FIELD_PAGE_FLAG = "pageFlag";

  String FIELD_PAGE_NUMBER = "pageNumber";

  /** default page size. */
  int PAGE_SIZE = 10;

  /** default page number. */
  int PAGE_NUMBER = 1;

  /** default page zero. */
  int PAGE_ZERO = 0;

  /** default page flag. */
  boolean PAGE_FLAG = true;

  Joiner JOINER_EMPTY = Joiner.on(EMPTY).skipNulls();

  Joiner JOINER_SPACE = Joiner.on(SPACE).skipNulls();

  Joiner JOINER = JOINER_SPACE;

  String UNDER_LINE = "_";

  String CALL_BACK = "callback";

  Charset CHAR_SET_UTF8 = StandardCharsets.UTF_8;

  String UTF8 = CHAR_SET_UTF8.name();

  /** success----code. */
  int RET_OK_CODE = SC_OK;

  /** success----message. */
  String RET_OK_MSG = "OK";

  /** failure----code. */
  int RET_ERROR_CODE = SC_INTERNAL_SERVER_ERROR;

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

  String FILE_NAME_ZIP = "package.zip";

  /** MethodDescription----AND. */
  String AND = "and";

  /** like. */
  String LIKE = "%";
}
