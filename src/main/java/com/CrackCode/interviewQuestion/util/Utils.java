package com.CrackCode.interviewQuestion.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class Utils {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-_]+\\.)+[\\w]+[\\w]$", Pattern.CASE_INSENSITIVE);
    public static final String PHONE_VALIDATION_REGEX = "^\\+?(?:[0-9] ?){6,14}[0-9]$";
    public static final String MASKING_REGEX = "[0-9]+[P]";
    private static final String specialCharactersRegex = "[\\~\\`\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=\\{\\}\\[\\]\\|\\\\\\/\\:\\;\\\"\\'\\<\\>\\?]";
    private static final String specialCharactersWithoutHyphenRegex = "[\\~\\`\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\{\\}\\[\\]\\|\\\\\\/\\:\\;\\\"\\'\\<\\>\\?]";
    private static final String consecutiveSpacesRegex = " +";
    private static final String maxSignatoryRegex = "([A-Z]([0-9]{1,2}))";
    private static final Pattern maxSignatoryPattern = Pattern.compile(maxSignatoryRegex);

    @Value("${maximum.file.row.size}")
    private int maximumFileRowSize;

    private static int MAX_FILE_ROW_SIZE;

    @PostConstruct
    public void init() {
        MAX_FILE_ROW_SIZE = maximumFileRowSize;
    }

    private static DecimalFormat df = new DecimalFormat("0.00");
    public static String decrypt(String hashedText){
        String text = "";
        try{
            /*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//uncomment those 2 line for use this method
            text = encoder.encode(hashedText);*/
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return text;
    }

    public static final Number getNumber(String value){
        try {
            return NumberFormat.getInstance().parse(value);
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }




    public static final String getParameter(HttpServletRequest request,String paramName) {
        if(request == null) {
            return null;
        }

        String requestParameter = request.getParameter(paramName);
        if(!isEmpty(requestParameter)) {
            requestParameter = sanitizeSpecialChars(requestParameter);
        }
        return requestParameter;
    }

    public static final String getParameterForDate(HttpServletRequest request,String paramName) {
        if(request == null) {
            return null;
        }

        String requestParameter = request.getParameter(paramName);
        if(!isEmpty(requestParameter)) {
            requestParameter = sanitizeSpecialCharsWithHyphen(requestParameter);
        }
        return requestParameter;
    }

    public static final boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static final boolean isValidMobile(String mobileNumber) {
        final Pattern pattern = Pattern.compile(PHONE_VALIDATION_REGEX, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    public static final boolean isValidPhone(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{4,6}-\\d{7}");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static final boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static final String getFormattedString(String value) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);
        return decimalFormat.format(Double.parseDouble(value));
    }

    public static final boolean isNull(Integer value) {
        return value == null || value.intValue() == 0;
    }

    public static final boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    public static final boolean isNull(Float value) {
        return value == null || value.floatValue() == 0;
    }

    public static final boolean isNull(Long value) {
        return value == null || value.longValue() == 0;
    }

    public static final boolean isNull(Double value) {
        return value == null || value.doubleValue() == 0;
    }

    public static final boolean isNull(Double value, boolean isZeroCheck) {
        if (isZeroCheck) {
            return value == null || value.doubleValue() == 0;
        }
        return value == null;
    }

    public static final boolean isNull(BigDecimal value) {
        return value == null || value.doubleValue() == 0;
    }


    public static final Date getDateParam(Date date, boolean isMax) {
        if(date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        cal.setTime(date);
        if(!isMax) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,1);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE,59);
            cal.set(Calendar.SECOND,59);
            cal.set(Calendar.MILLISECOND,999);
        }

        return cal.getTime();
    }

    public static final Date getDateParamMaxOrMin(Date date, boolean isMax) {
        if(date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        cal.setTime(date);
        if(!isMax) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE,59);
            cal.set(Calendar.SECOND,59);
            cal.set(Calendar.MILLISECOND,999);
        }

        return cal.getTime();
    }

    public static final Date finacleConvertedDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,2);
        return cal.getTime();
    }


    public static final Date stringToDate(String date,String format) {

        if(isEmpty(date)) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(date);
        } catch (Throwable t) {
            return null;
        }

    }

    public static final String dateToString(Date date,String pattern) {
        if(date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static final String getStringToStringDate(String date,String oldFormat,String newFormat) {
        DateFormat oldF = new SimpleDateFormat(oldFormat);
        DateFormat newF = new SimpleDateFormat(newFormat);
        try {
            Date d = oldF.parse(date);
            return newF.format(d);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static final Integer getInteger(Object object) {
        if(object == null) {
            return null;
        }
        if(object instanceof Integer) {
            return (Integer)object;
        }

        if(object instanceof BigDecimal) {
            return ((BigDecimal)object).intValue();
        }

        if(object instanceof Long) {
            return ((Long)object).intValue();
        }

        if(object instanceof Number) {
            return ((Number)object).intValue();
        }

        if(object instanceof BigInteger) {
            return ((BigInteger)object).intValue();
        }
        if(object instanceof String) {
            return Integer.parseInt((String)object);
        }

        return null;
    }

    public static final Double getDouble(Object object) {
        if(object instanceof Integer) {
            return new Double((Integer)object);
        }

        if(object instanceof BigDecimal) {
            return ((BigDecimal)object).doubleValue();
        }

        if(object instanceof Long) {
            return ((Long)object).doubleValue();
        }

        if(object instanceof Number) {
            return ((Number)object).doubleValue();
        }

        if(object instanceof BigInteger) {
            return ((BigInteger)object).doubleValue();
        }

        if(object instanceof Double) {
            return ((Double)object).doubleValue();
        }

        return null;
    }

    public static final Long getLong(Object object) {
        if(object instanceof Integer) {
            return new Long((Integer)object);
        }

        if(object instanceof BigDecimal) {
            return ((BigDecimal)object).longValue();
        }

        if(object instanceof Long) {
            return (Long)object;
        }

        if(object instanceof Number) {
            return ((Number)object).longValue();
        }
        if(object instanceof Double) {
            return ((Double)object).longValue();
        }

        return null;
    }

    public static String randomString( int len ){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }





    public static final Date getDateFromTimestamp(Timestamp value){
        if(value == null) {
            return null;
        }

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        c.setTimeInMillis(value.getTime());
        return c.getTime();
    }

    public static final String stringFromDate(Date date,String format) {
        if(date == null || format==null) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date).toUpperCase();
        } catch (Throwable t) {
            return null;
        }
    }

    public static final Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static final boolean indexExists(Object[] value,int index) {
        if(value == null || value.length ==0 || value.length <index) {
            return false;
        }
        try {
            if (value.length > index && value[index] != null) {
                return true;
            }
        } catch (Throwable t) {
            return false;
        }
        return false;
    }

    public static final boolean isInList(String value,String ...values) {
        if (isEmpty(value) || values == null || values.length == 0) {
            return false;
        }
        for (String v: values) {
            if(isEmpty(v)) {
                continue;
            }
            if(value.equalsIgnoreCase(v.trim())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInList(Integer value,Integer ...values) {
        if (values == null || values.length == 0) {
            return false;
        }
        for (Integer v: values) {
            if(v == null) {
                continue;
            }
            if(value.intValue() == v.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isInArray(int value, int [] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        for (Integer v: values) {
            if(v == null) {
                continue;
            }
            if(value == v) {
                return true;
            }
        }
        return false;
    }

    public static final Integer getIntegerFromString(String value) {
        if(isEmpty(value)) {
            return 0;
        }

        try {
            return Integer.parseInt(value);
        } catch (Throwable t) {
            return 0;
        }
    }

    public static final double getDoubleFromString(String value) {
        if(isEmpty(value)) {
            return 0.0;
        }

        try {
            return Double.parseDouble(value);
        } catch (Throwable t) {
            return 0.0;
        }
    }

    public static final Integer[] getIntegerArray(String[] values) {
        if(values == null || values.length <=0) {
            return null;
        }
        Integer[] results = new Integer[values.length];
        int i=0;
        for (String val : values) {
            results[i] = getIntegerFromString(val);
            i++;
        }
        return results;
    }

    public static final double[] getDoubleArray(String[] values) {
        if(values == null || values.length <=0) {
            return null;
        }
        double[] results = new double[values.length];
        int i=0;
        for (String val : values) {
            results[i] = getDoubleFromString(val);
            i++;
        }
        return results;
    }

    public static int calculateSignatory(String signatoryText) {
        try {
            String values[] = signatoryText.split("\\&");
            int count = 0;
            for (String str : values) {
                String[] orPart = str.split("\\|");
                if (orPart == null || orPart.length <= 0) {
                    orPart = new String[]{str};
                }
                int subCount = 0;
                for (String or : orPart) {
                    if (or != null && or.length() > 0) {
                        or = or.replaceAll("[a-zA-Z]", "");
                    }
                    if (Integer.parseInt(or) > subCount) {
                        subCount = Integer.parseInt(or);
                    }
                }
                count += subCount;
            }
            return count;
        } catch (Throwable t) {
            return 0;
        }
    }

    public static final List<String> getGroupFromRule(String signatoryText) {
        List<String> list = new ArrayList<>();
        try {
            signatoryText = signatoryText.replaceAll("[\\|\\&]", "").replaceAll("[0-9]", "");
            if(signatoryText != null && signatoryText.length() >0) {
                for(Character c : signatoryText.toCharArray()) {
                    if(c != null) {
                        list.add(c+"");
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return list;
    }

    public static int getMaxSignatoryOfGroup(String rule, String group) {
        String minTerms[] = rule.split("\\|");
        int maxSignatory = 0;
        for (String minTerm : minTerms) {
            String ands[] = minTerm.split("&");
            for (String and : ands) {
                if (and.contains(group)) {
                    Matcher matcher = maxSignatoryPattern.matcher(and);
                    if (matcher.find()) {
                        String resultGroup1 = matcher.group(1);
                        String resultGroup2 = matcher.group(2);
                        Integer signatory = Integer.valueOf(resultGroup2);
                        if (signatory > maxSignatory) {
                            maxSignatory = signatory;
                        }
                     }
                }
            }

        }
        return maxSignatory;
    }

    public static final List<String> getUniqueGroupFromRule(String signatoryText) {
        List<String> uniqueGroup = new ArrayList<>();
        try {
            String[] groups = signatoryText.split("&|\\||\\(|\\)");

            Set<String> uniqueGroupSet = uniqueGroup(groups);
            uniqueGroup.addAll(uniqueGroupSet);

        } catch (Throwable t){
            t.printStackTrace();
        }


        return uniqueGroup;
    }

    private static Set<String> uniqueGroup(String[] groups){
        Set<String> uniqueGroup = new HashSet<>();
        for(String s : groups){
            if(!s.isEmpty() && s.length() >= 2){
                uniqueGroup.add(s.substring(0,1));
            }
        }
        return uniqueGroup;
    }

    public static Integer numberOfSignatory(String rule,String groupName) {
        if(rule == null || groupName == null) {
            return null;
        }
        StringBuilder num = new StringBuilder();
        int index = rule.indexOf(groupName);
        if(rule.length() == index+2) {
            for (int i = index+1; i < index+2; i++) {
                num.append(rule.charAt(i));
            }
        } else if(rule.length() > index+2){
            for (int i = index+1; i < index+3; i++) {
                if(i == index+2) {
                    if(((int)rule.charAt(i) >= 43 && (int)rule.charAt(i)<=52)) {
                        num.append(rule.charAt(i));
                    }
                } else {
                    num.append(rule.charAt(i));
                }
            }
        }
        return Integer.parseInt(num.toString());
    }


    public static final String getDate(Integer month,Integer year) {

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        c.set(Calendar.MONTH,month-1);
        c.set(Calendar.YEAR,year);

        return new SimpleDateFormat("MMMMMM YYYY").format(c.getTime());
    }

    public static final int getCurrentYear() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        int year = c.get(Calendar.YEAR);
        System.out.print("---YEAR:"+year);
        return year;

    }

    public static final int getHour() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        int hour = c.get(Calendar.HOUR_OF_DAY);
        System.out.print("---HOUR:"+hour);
        return hour;

    }

    public static final int getMinute() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+6"));
        int minute = c.get(Calendar.MINUTE);
        System.out.print("---MINUTE:"+minute);
        return minute;

    }

    public static List<String> getUniqueList(List<String> list) {
        if(list == null || list.size() <=1) {
            return list;
        }

        Map<String,String> map = new HashMap<>();
        for (String st : list) {
            if(Utils.isEmpty(st)) {
                continue;
            }
            map.put(st.trim().toUpperCase(),st.trim().toUpperCase());
        }

        return new ArrayList<>(map.keySet());
    }

    public static String padLeft(String value, int length) {
        if(isEmpty(value)) {
            value = new String();
            for (int i=0;i<length;i++) {
                value +=" ";
            }
            return value;
        }
        if (value.length() >= length) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - value.length()) {
            sb.append(' ');
        }
        sb.append(value);

        return sb.toString();
    }




    /*
    public static final boolean isWarning(Double amount) {
        if(isNull(amount)) {
            return false;
        }

        int hour = getHour();
        if(amount.intValue() >50000000) {
            return true;
        }
        if(amount.intValue() >= 50000000 && hour >= 19) {
            return true;
        }
        if(amount.intValue() >= 20000000 && (hour >=20 && hour <= 21)) {
            return true;
        }
        if(amount.intValue() >= 10000000 && (hour >=21 && hour <= 22)) {
            return true;
        }
        if(amount.intValue() >= 5000000 && hour == 22 && getMinute() <= 30) {
            return true;
        }
        if((hour >= 22 && getMinute() >=31) || hour >22) {
            return true;
        }

        return false;
    }
    */
    public static final BigDecimal[] getBigDecimalArray(String[] values) {
        if(values == null || values.length <=0) {
            return null;
        }
        BigDecimal[] results = new BigDecimal[values.length];
        int i=0;
        for (String val : values) {
            results[i] = getBigDecimalFromString(val);
            i++;
        }
        return results;
    }

    public static final BigDecimal getBigDecimalFromString(String value) {
        if(isEmpty(value)) {
            return new BigDecimal(0.0);
        }

        try {
            return new BigDecimal(value);
        } catch (Throwable t) {
            return new BigDecimal(0.0);
        }
    }


    public static final Date minusDay(Date date,int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -day);
        return c.getTime();
    }

    public static final String getLoginDateTime() {
        DateFormat df = new SimpleDateFormat("EEEE dd, MMMMM yyyy 'at' HH:mm a",Locale.ENGLISH);
        return df.format(new Date());
    }

    public static final String formattedAccount(String accountNumber) {
        String number = "";
        if(accountNumber != null && accountNumber.length() ==16) {
            number = accountNumber.substring(0,3);
        }
        number += "XXXXXXXXXX";
        number += accountNumber.substring(14);

        return number;
    }

    public static final Long getLongValue(String value) {
        try {
            return new Long(value);
        } catch (Throwable t) {
            return new Long(0);
        }
    }

    public static final String getEscaped(String value) {
        if(isEmpty(value)) {
            return value;
        }
        if(isHtml(value)) {
            return HtmlUtils.htmlEscape(value);
        }
        return value;
    }

    public static final String getUnEscaped(String value) {
        if(isEmpty(value)) {
            return value;
        }
        if(isHtml(value)) {
            return HtmlUtils.htmlUnescape(value);
        }
        return value;
    }

    public static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }

    public static final List<String> getGroupListToApprove(String existingGroup,String rule) {
        List<String> values = new ArrayList<>();
        String[] parts = rule.split("&");
        if(parts == null || parts.length ==1 ){
            parts[0] = rule;
        }
        for (String part : parts) {
            if(part.length() <=3) {
                continue;
            }
            String[] orParts = part.split("\\|");
            if(orParts != null && orParts.length >=2) {
                for (String orPart : orParts) {
                    if(orPart == null || orPart.contains(existingGroup)) {
                        continue;
                    }
                    String group = orPart.replaceAll("[0-9]", "");
                    values.add(group);
                }
            }
        }
        return values;
    }

    public static final String getUniqueNumber() {
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            return bytesToHex(salt.digest());
        }catch (Throwable t) {
            return null;
        }
    }

    public static final String getUniqueReferenceForCompanyMember(String memberId){
        String uniqueReference = memberId + "-" + System.currentTimeMillis();
        return uniqueReference;
    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            String hex = Integer.toHexString(0xff & hashInBytes[i]);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();

    }

    public static final boolean isInsideAnd(String rule,String group) {
        String[] values = rule.split("&");
        for (String value : values) {
            if(value.length() <=3 && value.contains(group)) {
                return true;
            }
        }
        return false;
    }

    public static final String trim(String value) {
        if(!isEmpty(value)) {
            return value.trim();
        }

        return value;
    }







    public static final String doMaskReference(String value) {
        Pattern pattern = Pattern.compile(MASKING_REGEX);
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            if (value.length() >= 15) {
                return value.replaceAll("[0-9]+[P]", value.substring(matcher.start(), matcher.start() + 4) + "**" + value.substring(matcher.end() - 4, matcher.end()));
            }
        }
        return value;
    }

    public static String getFormattedDecimalNumber(double number) {
        return df.format(number);
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String sanitizeSpecialChars(String request) {
        if (request != null) {
            request = request.replaceAll(specialCharactersRegex, " ");
            request = Utils.getEscaped(request);
            request = request.replaceAll("&nbsp;", " ");
            request = request.replaceAll(consecutiveSpacesRegex, " ");
            request = request.trim();
        }
        return request;
    }  public static String sanitizeSpecialCharsWithHyphen (String request) {
        if (request != null) {
            request = request.replaceAll(specialCharactersWithoutHyphenRegex, " ");
            request = Utils.getEscaped(request);
            request = request.replaceAll("&nbsp;", " ");
            request = request.replaceAll(consecutiveSpacesRegex, " ");
            request = request.trim();
        }
        return request;
    }




    public static boolean isValidDateStr(String date, String dateFormat){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            sdf.parse(date);
        } catch (ParseException e){
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e){
            return false;
        }
        return true;
    }





    public static String getRandomUniqueId(){
        return UUID.randomUUID().toString();
    }

    public static Integer getDaysDifferenceFromwoDates(String fromDate, String toDate,String format){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);

        long daysBetween;

        try {
            LocalDate date1 = LocalDate.parse(fromDate, dtf);
            LocalDate date2 = LocalDate.parse(toDate, dtf);
            daysBetween = ChronoUnit.DAYS.between(date1, date2);
            return new Integer(Math.toIntExact(daysBetween));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static <T> T[] concatTwoArray(T[] array1, T[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(size -> (T[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }


}
