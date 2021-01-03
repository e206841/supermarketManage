package cn.stylefeng.guns.modular.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATETIME_FORMAT_QUERY = "yyyy-MM-dd HH:mm";

    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATETIME_FORMAT_2 = "yyyyMMddHHmmss";
    /**
     * 时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT2 = "HHmmss";

    /**
     * 短日期格式
     **/
    public static final String DEFAULT_DATE_SHORT_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_DATE_SHORT_FORMAT1 = "yyyy/MM/dd";

    public static final String YEAR_FORMAT = "yyyy";
    /**
     * 每天小时数
     */
    private static final long HOURS_PER_DAY = 24;
    /**
     * 每小时分钟数
     */
    private static final long MINUTES_PER_HOUR = 60;
    /**
     * 每分钟秒数
     */
    private static final long SECONDS_PER_MINUTE = 60;
    /**
     * 每秒的毫秒数
     */
    private static final long MILLIONSECONDS_PER_SECOND = 1000;
    /**
     * 每分钟毫秒数
     */
    private static final long MILLIONSECONDS_PER_MINUTE = MILLIONSECONDS_PER_SECOND * SECONDS_PER_MINUTE;
    /**
     * 每天毫秒数
     */
    private static final long MILLIONSECONDS_SECOND_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIONSECONDS_PER_SECOND;

    public static TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    private DateUtils() {
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date) {
        return getDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期对象
     *
     * @param date 待转换字符串
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDateTime(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    public static Date getDateTimeQuery(String date) {
        return getDate(date, DEFAULT_DATETIME_FORMAT_QUERY, null);
    }

    public static Date getDateTimeYYYY(String date) {
        return getDate(date, YEAR_FORMAT, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   待转换字符串
     * @param format 日期格式
     * @return 转换后日期对象
     * @see #getDate(String, String, Date)
     */
    public static Date getDate(String date, String format) {
        return getDate(date, format, null);
    }

    /**
     * 将指定格式的字符串转换为日期对象
     *
     * @param date   日期对象
     * @param format 日期格式
     * @param defVal 转换失败时的默认返回值
     * @return 转换后的日期对象
     */
    public static Date getDate(String date, String format, Date defVal) {
        Date d;
        try {
            d = new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            d = defVal;
        }
        return d;
    }
    /**
     * 将指定格式的字符串转换为日期对象
     *gjy添加
     * @param date   日期对象
     * @return 转换后的日期对象
     */
    public static String strToDateFormat(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate= formatter.parse(date);
        formatter = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return formatter.format(newDate);
    }


    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatShortDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT, null);
    }

    /**
     * 将日期对象格式化成yyyyMMdd格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatYYYYMMDDDate(Date date) {
        return formatDate(date, DEFAULT_DATE_SHORT_FORMAT, null);
    }

    public static String formatYYYYMMDDDate1(Date date) {
        return formatDate(date, DEFAULT_DATE_SHORT_FORMAT1, null);
    }

    /**
     * 将日期对象格式化成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String forDatetime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT, null);
    }

    public static String forDatetimeQuery(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT_QUERY, null);
    }

    public static String forDatetime_2(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT_2, null);
    }

    /**
     * 根据指定格式来展现年
     *
     * @param date
     * @return
     */
    public static String forYearDatetime(Date date) {
        return formatDate(date, YEAR_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HH:mm:ss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT, null);
    }

    /**
     * 将日期对象格式化成HHmmss格式的字符串
     *
     * @param date 待格式化日期对象
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatTime2(Date date) {
        return formatDate(date, DEFAULT_TIME_FORMAT2, null);
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @return 格式化后的字符串
     * @see #formatDate(Date, String, String)
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }


    /**
     * 将传入的Date类型改为"yyyy-MM-dd HH:mm:ss"的String类型
     *
     * @param date 日期
     * @return String "yyyy-MM-dd HH:mm:ss",如果传入的date为null，则返回null
     */
    public static String formatDateYYYYMMMDDHHMMSS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return s.format(date);
    }

    /**
     * 带时区的格式化时间
     *
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    public static String formatDateTimeZone(Date date, String format, TimeZone timeZone) {
        String ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setTimeZone(timeZone);
            ret = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将日期对象格式化成指定类型的字符串
     *
     * @param date   待格式化日期对象
     * @param format 格式化格式
     * @param defVal 格式化失败时的默认返回空
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date, String format, String defVal) {
        String ret;
        try {
            ret = new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            ret = defVal;
        }
        return ret;
    }


    /**
     * 返回指定日期加上days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, int days) {
        return changeDays(date, days);
    }

    public static Date plusDaysToday(int days) {
        return plusDays(getToday(), days);
    }

    public static Date minusDaysToday(int days) {
        return minusDays(getToday(), days);
    }

    /**
     * 添加指定月份
     *
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonthsToDate(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回指定日期减去days天后的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, int days) {
        return changeDays(date, -days);
    }

    private static Date changeDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 获取当前日期加时间
     *
     * @return
     */
    public static Date getToday() {
        return new Date();
    }

    public static Date now() {
        return getToday();
    }

    public static long currentTimeMillis() {
        return new Date().getTime();
    }

    /**
     * 获得当前时间sql.date
     */
    public static java.sql.Date getTodaySqlDate() {
        return new java.sql.Date(getToday().getTime());
    }

    /**
     * 获取今天日期, 格式: YYYY-MM-DD
     *
     * @return
     */
    public static String getTodayStr() {
        return formatDate(getToday(), DEFAULT_DATE_FORMAT);
    }

    /**
     * 比较传入日期与当前日期相差的天数
     *
     * @param d
     * @return
     */
    public static int intervalDay(Date d) {
        return intervalDay(getToday(), d);
    }

    /**
     * 获取时间格式为yyyyMMdd
     *
     * @return
     */
    public static String getShortDate() {
        return formatDate(getToday(), DEFAULT_DATE_SHORT_FORMAT);
    }

    /**
     * 比较两个日期相差的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int intervalDay(Date d1, Date d2) {
        long intervalMillSecond = setToDayStartTime(d1).getTime() - setToDayStartTime(d2).getTime();
        //相差的天数 = 相差的毫秒数 / 每天的毫秒数 (小数位采用去尾制)
        return (int) (intervalMillSecond / MILLIONSECONDS_SECOND_PER_DAY);
    }

    /**
     * 将时间调整到当天0:0:0
     *
     * @param date
     * @return
     */
    private static Date setToDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }


    /**
     * 判断当前时间
     *
     * @return
     */
    public static String getDateStatus() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 12) {
            return "morning";
        } else if (hour >= 12 && hour < 18) {
            return "noon";
        } else if (hour >= 18 && hour < 24) {
            return "evning";
        } else {
            return "midnight";
        }
    }

    /**
     * 获得两个日期之间相差的分钟数。（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return 返回两个日期之间相差的分钟数值
     */
    public static int intervalMinutes(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        //相差的分钟数 = 相差的毫秒数 / 每分钟的毫秒数 (小数位采用进位制处理，即大于0则加1)
        return (int) (intervalMillSecond / MILLIONSECONDS_PER_MINUTE
                + (intervalMillSecond % MILLIONSECONDS_PER_MINUTE > 0 ? 1 : 0));
    }

    /**
     * 获得两个日期之间相差的秒数差（date1 - date2）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int intervalSeconds(Date date1, Date date2) {
        long intervalMillSecond = date1.getTime() - date2.getTime();

        return (int) (intervalMillSecond / MILLIONSECONDS_PER_SECOND
                + (intervalMillSecond % MILLIONSECONDS_PER_SECOND > 0 ? 1 : 0));
    }

    public static int getAge(Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        //取得生日年份
        int year = birth.get(Calendar.YEAR);
        //年龄
        int age = now.get(Calendar.YEAR) - year;
        //修正
        now.set(Calendar.YEAR, year);
        age = (now.before(birth)) ? age - 1 : age;
        return age;
    }

    /**
     * d1 和 d2 是同一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isSameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(d1.getTime());
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(d2.getTime());

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是否d2是d1的后一天
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isContinueDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;
        if (intervalDay(d1, d2) == 1)
            return true;
        return false;
    }

    /**
     * 得到没有时间的日期
     *
     * @param date
     * @return
     */
    public static Date truncDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 得到旬.
     *
     * @param input
     * @return
     * @author <a href="mailto:wangxin@knet.cn">北京王欣</a>
     */
    public static String getCnDecade(Date input) {
        String day = formatDate(input);
        String decade = day.replaceAll("01日", "上旬").replaceAll("11日", "中旬").replaceAll("21日", "下旬");
        return decade;
    }

    public static Date getTodayZero() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getTheDayBefore(Date date) {
        return new Date(date.getTime() - (long) 24 * (long) 60 * (long) 60 * (long) 1000);
    }

    /**
     * long转date，在转string
     *
     * @param time
     * @return
     */
    public static String getLongToDateStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        Date dt = new Date(time * 1000);
        String dataTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        return dataTime;
    }

    public static Date getLongToDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        Date dt = new Date(time);
        return dt;
    }

    public static Date[] getTenDayBefore() {//计算之前一旬的起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);//0点0分0秒
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {//今天处在某月的上旬，起始时间是前一个月的21号，终止时间是本月的1号
            c.set(Calendar.DAY_OF_MONTH, 1);//本月的1号
            ret[1] = new Date(c.getTime().getTime());
            c.setTime(getTheDayBefore(c.getTime()));//往前翻一天，到上一个月
            c.set(Calendar.DAY_OF_MONTH, 21);
            ret[0] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {//今天处在某月的中旬，起始时间是本月的1号，终止时间是本月的11号
                c.set(Calendar.DAY_OF_MONTH, 1);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[1] = new Date(c.getTime().getTime());
            } else {//今天处在某月的下旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            }
        }
        return ret;
    }

    public static Date[] getCurrentTenDay(Date input) {//计算某个输入时间的当前旬起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(input);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);//0点0分0秒
        int day = c.get(Calendar.DAY_OF_MONTH);
        if (day < 10) {//今天处在某月的上旬，起始时间是本月的1号，终止时间是本月的11号
            c.set(Calendar.DAY_OF_MONTH, 1);//本月的1号
            ret[0] = new Date(c.getTime().getTime());
            c.set(Calendar.DAY_OF_MONTH, 11);
            ret[1] = new Date(c.getTime().getTime());
        } else {//

            if (10 < day && day <= 20) {//今天处在某月的中旬，起始时间是本月的11号，终止时间是本月的21号
                c.set(Calendar.DAY_OF_MONTH, 11);
                ret[0] = new Date(c.getTime().getTime());
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[1] = new Date(c.getTime().getTime());
            } else {//今天处在某月的下旬，起始时间是本月的21号，终止时间是下个月的1号
                c.set(Calendar.DAY_OF_MONTH, 21);
                ret[0] = new Date(c.getTime().getTime());
                ret[1] = getNextMonthFirst(c.getTime());
            }
        }
        return ret;
    }

    public static Date getNextMonthFirst(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);//0点0分0秒
        c.add(Calendar.MONTH, 1);//加一个月
        c.set(Calendar.DATE, 1);//把日期设置为当月第一天
        return c.getTime();
    }

    public static Date[] getTheMonthBefore(Date date) {//计算之前一旬的起止时间
        Date[] ret = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);//0点0分0秒
        c.set(Calendar.DAY_OF_MONTH, 1);//本月的1号
        ret[1] = new Date(c.getTime().getTime());
        c.setTime(getTheDayBefore(c.getTime()));//往前翻一天，到上一个月
        c.set(Calendar.DAY_OF_MONTH, 1);//上月的1号
        ret[0] = new Date(c.getTime().getTime());
        return ret;
    }

    /**
     * 格式化输入的日期，格式为：yyyy.MM.dd | yyyy-MM-dd
     *
     * @return
     */
    public static String formatInputDate(Date curTime, String formate) {
        String mDateTime1 = "";
        if (null == curTime)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        mDateTime1 = formatter.format(curTime);
        return mDateTime1;
    }

    /**
     * 两个时间之间相差的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonths(Date date1, Date date2) {
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);
            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);
            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
                    .get(Calendar.DAY_OF_MONTH))
                flag = 1;
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
                    .get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
                        .get(Calendar.YEAR))
                        * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
                        - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH)
                        - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return iMonth;
    }

    /**
     * 判断当前审核的信息是否已经过期
     *
     * @param validEndDate
     * @param dbtime       数据库时间
     * @return
     */
    public static boolean expiredDate(Date validEndDate, Date dbtime) {
        Calendar today1 = Calendar.getInstance();
        today1.setTime(dbtime);
        today1.add(Calendar.DAY_OF_MONTH, 1);// 数据库“今天”后一天的取整天
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.set(today1.get(today1.YEAR), today1.get(today1.MONTH), today1
                .get(today1.DAY_OF_MONTH), 0, 0, 0);
        Calendar validPeriodEnd = Calendar.getInstance();
        validPeriodEnd.setTime(validEndDate);
        validPeriodEnd.set(Calendar.HOUR, 23); // 避免时分秒的差距
        // 注册系统传来的营业期限截至日期为整天整点，而后面的Calendar比较的是精确时间
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        if (validPeriodEnd.before(tomorrow)) {
            return false;
        }
        return true;
    }

    /**
     * 比较两个日期的时间
     *
     * @param begintime
     * @param endtime
     * @return
     */
    public static boolean compareDate(String begintime, String endtime) {
        Date begin = getDateTime(begintime);
        Date end = getDateTime(endtime);
        if (begin.getTime() > end.getTime()) {
            return false;
        } else if (begin.getTime() < end.getTime()) {
            return true;
        } else {//相等
            return false;
        }
    }

    /**
     * 获取当前分钟的long值和上一分钟的long值
     *
     * @return
     */
    public static Long[] getQueryBetweenDate() {
        Long[] longs = new Long[2];
        Date date = new Date();
        String start = DateUtils.forDatetimeQuery(date) + ":00";

        Long queryStart = DateUtils.getDateTime(start).getTime();
        longs[0] = queryStart;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);
        String end = (new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_QUERY)).format(calendar.getTime()) + ":00";
        Long queryEnd = DateUtils.getDateTime(end).getTime();
        longs[1] = queryEnd;
        return longs;
    }

    /**
     * 获取当前分钟的long值和上一分钟的long值
     *
     * @return
     */
    public static Long getQueryDate(int row) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -row);
        String end = (new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_QUERY)).format(calendar.getTime()) + ":00";
        Long queryEnd = DateUtils.getDateTime(end).getTime();
        return queryEnd;
    }

    /**
     * 获取当前分钟的long值和上一小时的long值
     *
     * @return
     */
    public static Long getQueryHourDate(int row) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -row);
        String end = (new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_QUERY)).format(calendar.getTime()) + ":00";
        Long queryEnd = DateUtils.getDateTime(end).getTime();
        return queryEnd;
    }

    /**
     * 获取之前某一天的起始时间
     *
     * @param num
     * @return
     */
    public static Long[] getYesDayTime(int num) {
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -num);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        defaultStartDate = defaultStartDate + " 00:00:00";
        String defaultEndDate = defaultStartDate.substring(0, 10) + " 23:59:59";
        Date beingDate = getDateTime(defaultStartDate);
        Date endDate = getDateTime(defaultEndDate);
        Long times[] = {beingDate.getTime(), endDate.getTime()};
        return times;
    }


    /**
     * 获取之前某一天的起始时间
     *
     * @param num
     * @return
     */
    public static Date getYesDayDate(int num) {
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -num);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        String defaultEndDate = defaultStartDate.substring(0, 10) + " 23:59:59";
        Date endDate = getDateTime(defaultEndDate);
        return endDate;
    }

    /**
     * @param num
     * @param date
     * @return
     */
    public static Long getYesDayTime(int num, Date date) {
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.HOUR, -num);  //设置为前一天
        Date newDate = calendar.getTime();
        return newDate.getTime();
    }


    /**
     * 获得当前时间后N分钟的整点时间,并且秒数和毫秒数为0，返回date
     *
     * @param amount
     * @return
     */
    public static Date getNextMinuteForInitial(int amount) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, amount);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date date = now.getTime();
        return date;
    }

    /**
     * 获取当前日期对应月的第一天
     * @param date date
     * @return 前日期对应月的第一天
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH,0);
        cale.set(Calendar.DAY_OF_MONTH,1);
        return cale.getTime();
    }
    /**
     * 获取当前日期对应月的最后一天
     * @param date  date
     * @return 前日期对应月的最后一天
     */
    public static Date getMonthTailDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH,1);
        cale.set(Calendar.DAY_OF_MONTH,0);
        return cale.getTime();
    }
    /**
     * 获取过去天数的日期
     * @return
     */
    public static String getDayDateBefor(int num){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -num);
        Date y = c.getTime();
        String year = format.format(y);
        return year;

    }
    public static void main(String strs[]) {
        Date date = getYesDayDate(1);
        Long endtime = getYesDayTime(4, date);
    }
}
