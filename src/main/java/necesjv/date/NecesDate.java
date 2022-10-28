package necesjv.date;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class NecesDate {

    public static long countDayNotSunday(Date dateStart,Date dateEnd){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(dateStart);
        cal2.setTime(dateEnd);

        long numberOfDays = 1;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE,1);
        }
        return numberOfDays;
    }
    public boolean isDateWithFormat(String value, String pattern) {
        if( pattern == null) pattern = "YYYY/MM/DD";
        if (value == null || value == "" ) return false;
        if (value != null && value != "") {
            pattern = pattern.toUpperCase();
            switch (pattern) {
                case "YYYY/MM/DD":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[/](0[1-9]|1[012])[/]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYY.MM.DD":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[.](0[1-9]|1[012])[.]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYY-MM-DD":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[-](0[1-9]|1[012])[-]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "YYYYMMDD":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})(0[1-9]|1[012])([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DD/MM/YYYY":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[/](0[1-9]|1[012])[/](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DD.MM.YYYY":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[.](0[1-9]|1[012])[.](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DD-MM-YYYY":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[-](0[1-9]|1[012])[-](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DDMMYYYY":
                    return Pattern.compile("^([123]0|[012][1-9]|31)(0[1-9]|1[012])(19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "MMDDYYYY":
                    return Pattern.compile("^(0[1-9]|1[012])([123]0|[012][1-9]|31)(19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM/DD/YYYY":
                    return Pattern.compile("^(0[1-9]|1[012])[/]([123]0|[012][1-9]|31)[/](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM-DD-YYYY":
                    return Pattern.compile("^(0[1-9]|1[012])[-]([123]0|[012][1-9]|31)[-](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM.DD.YYYY":
                    return Pattern.compile("^(0[1-9]|1[012])[.]([123]0|[012][1-9]|31)[.](19[0-9]{2}|2[0-9]{3})$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "YYYYDDMM":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})([123]0|[012][1-9]|31)(0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYY/DD/MM":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[/]([123]0|[012][1-9]|31)[/](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYY-DD-MM":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[-]([123]0|[012][1-9]|31)[-](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYY.DD.MM":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})[.]([123]0|[012][1-9]|31)[.](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "YYYYMMDD HH:MI:SS":
                case "YYYYMMDDHH:MI:SS":
                case "YYYYMMDD HHMISS":
                case "YYYYMMDDHHMISS":
                case "YYYYMMDD HH:MI":
                case "YYYYMMDDHH:MI":
                case "YYYYMMDD HHMI":
                case "YYYYMMDDHHMI":
                case "YYYY/MM/DD HH:MI:SS":
                case "YYYY/MM/DDHH:MI:SS":
                case "YYYY/MM/DD HHMISS":
                case "YYYY/MM/DDHHMISS":
                case "YYYY/MM/DD HH:MI":
                case "YYYY/MM/DDHH:MI":
                case "YYYY/MM/DD HHMI":
                case "YYYY/MM/DDHHMI":
                case "YYYY-MM-DD HH:MI:SS":
                case "YYYY-MM-DDHH:MI:SS":
                case "YYYY-MM-DD HHMISS":
                case "YYYY-MM-DDHHMISS":
                case "YYYY-MM-DD HH:MI":
                case "YYYY-MM-DDHH:MI":
                case "YYYY-MM-DD HHMI":
                case "YYYY-MM-DDHHMI":
                case "YYYY.MM.DD HH:MI:SS":
                case "YYYY.MM.DDHH:MI:SS":
                case "YYYY.MM.DD HHMISS":
                case "YYYY.MM.DDHHMISS":
                case "YYYY.MM.DD HH:MI":
                case "YYYY.MM.DDHH:MI":
                case "YYYY.MM.DD HHMI":
                case "YYYY.MM.DDHHMI":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})(\\/|.|-){0,1}(0[1-9]|1[012])(\\/|.|-){0,1}([123]0|[012][1-9]|31)([ ]{0,1}([01][0-9]|2[0-3])[:]{0,1}([0-5][0-9])[:]{0,1}([0-5][0-9]){0,1}){0,1}$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "YYYYDDMM HH:MI:SS":
                case "YYYYDDMMHH:MI:SS":
                case "YYYYDDMM HHMISS":
                case "YYYYDDMMHHMISS":
                case "YYYYDDMM HH:MI":
                case "YYYYDDMMHH:MI":
                case "YYYYDDMM HHMI":
                case "YYYYDDMMHHMI":
                case "YYYY/DD/MM HH:MI:SS":
                case "YYYY/DD/MMHH:MI:SS":
                case "YYYY/DD/MM HHMISS":
                case "YYYY/DD/MMHHMISS":
                case "YYYY/DD/MM HH:MI":
                case "YYYY/DD/MMHH:MI":
                case "YYYY/DD/MM HHMI":
                case "YYYY/DD/MMHHMI":
                case "YYYY-DD-MM HH:MI:SS":
                case "YYYY-DD-MMHH:MI:SS":
                case "YYYY-DD-MM HHMISS":
                case "YYYY-DD-MMHHMISS":
                case "YYYY-DD-MM HH:MI":
                case "YYYY-DD-MMHH:MI":
                case "YYYY-DD-MM HHMI":
                case "YYYY-DD-MMHHMI":
                case "YYYY.DD.MM HH:MI:SS":
                case "YYYY.DD.MMHH:MI:SS":
                case "YYYY.DD.MM HHMISS":
                case "YYYY.DD.MMHHMISS":
                case "YYYY.DD.MM HH:MI":
                case "YYYY.DD.MMHH:MI":
                case "YYYY.DD.MM HHMI":
                case "YYYY.DD.MMHHMI":
                    return Pattern.compile("^(19[0-9]{2}|2[0-9]{3})(\\/|.|-){0,1}([123]0|[012][1-9]|31)(\\/|.|-){0,1}(0[1-9]|1[012])([ ]{0,1}([01][0-9]|2[0-3])[:]{0,1}([0-5][0-9])[:]{0,1}([0-5][0-9]){0,1}){0,1}$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DDMMYYYY HH:MI:SS":
                case "DDMMYYYYHH:MI:SS":
                case "DDMMYYYY HHMISS":
                case "DDMMYYYYHHMISS":
                case "DDMMYYYY HH:MI":
                case "DDMMYYYYHH:MI":
                case "DDMMYYYY HHMI":
                case "DDMMYYYYHHMI":
                case "DD/MM/YYYY HH:MI:SS":
                case "DD/MM/YYYYHH:MI:SS":
                case "DD/MM/YYYY HHMISS":
                case "DD/MM/YYYYHHMISS":
                case "DD/MM/YYYY HH:MI":
                case "DD/MM/YYYYHH:MI":
                case "DD/MM/YYYY HHMI":
                case "DD/MM/YYYYHHMI":
                case "DD-MM-YYYY HH:MI:SS":
                case "DD-MM-YYYYHH:MI:SS":
                case "DD-MM-YYYY HHMISS":
                case "DD-MM-YYYYHHMISS":
                case "DD-MM-YYYY HH:MI":
                case "DD-MM-YYYYHH:MI":
                case "DD-MM-YYYY HHMI":
                case "DD-MM-YYYYHHMI":
                case "DD.MM.YYYY HH:MI:SS":
                case "DD.MM.YYYYHH:MI:SS":
                case "DD.MM.YYYY HHMISS":
                case "DD.MM.YYYYHHMISS":
                case "DD.MM.YYYY HH:MI":
                case "DD.MM.YYYYHH:MI":
                case "DD.MM.YYYY HHMI":
                case "DD.MM.YYYYHHMI":
                    return Pattern.compile("^([123]0|[012][1-9]|31)(\\/|.|-){0,1}(0[1-9]|1[012])(\\/|.|-){0,1}(19[0-9]{2}|2[0-9]{3})([ ]{0,1}([01][0-9]|2[0-3])[:]{0,1}([0-5][0-9])[:]{0,1}([0-5][0-9]){0,1}){0,1}$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MMDDYYYY HH:MI:SS":
                case "MMDDYYYYHH:MI:SS":
                case "MMDDYYYY HHMISS":
                case "MMDDYYYYHHMISS":
                case "MMDDYYYY HH:MI":
                case "MMDDYYYYHH:MI":
                case "MMDDYYYY HHMI":
                case "MMDDYYYYHHMI":
                case "MM/DD/YYYY HH:MI:SS":
                case "MM/DD/YYYYHH:MI:SS":
                case "MM/DD/YYYY HHMISS":
                case "MM/DD/YYYYHHMISS":
                case "MM/DD/YYYY HH:MI":
                case "MM/DD/YYYYHH:MI":
                case "MM/DD/YYYY HHMI":
                case "MM/DD/YYYYHHMI":
                case "MM-DD-YYYY HH:MI:SS":
                case "MM-DD-YYYYHH:MI:SS":
                case "MM-DD-YYYY HHMISS":
                case "MM-DD-YYYYHHMISS":
                case "MM-DD-YYYY HH:MI":
                case "MM-DD-YYYYHH:MI":
                case "MM-DD-YYYY HHMI":
                case "MM-DD-YYYYHHMI":
                case "MM.DD.YYYY HH:MI:SS":
                case "MM.DD.YYYYHH:MI:SS":
                case "MM.DD.YYYY HHMISS":
                case "MM.DD.YYYYHHMISS":
                case "MM.DD.YYYY HH:MI":
                case "MM.DD.YYYYHH:MI":
                case "MM.DD.YYYY HHMI":
                case "MM.DD.YYYYHHMI":
                    return Pattern.compile("^(0[1-9]|1[012])(\\/|.|-){0,1}([123]0|[012][1-9]|31)(\\/|.|-){0,1}(19[0-9]{2}|2[0-9]{3})([ ]{0,1}([01][0-9]|2[0-3])[:]{0,1}([0-5][0-9])[:]{0,1}([0-5][0-9]){0,1}){0,1}$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MMDD":
                    return Pattern.compile("^(0[1-9]|1[012])([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM/DD":
                    return Pattern.compile("^(0[1-9]|1[012])[/]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM-DD":
                    return Pattern.compile("^(0[1-9]|1[012])[-]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "MM.DD":
                    return Pattern.compile("^(0[1-9]|1[012])[-]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DDMM":
                    return Pattern.compile("^([123]0|[012][1-9]|31)(0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD/MM":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[/](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD-MM":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[-](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD.MM":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[.](0[1-9]|1[012])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "MDD":
                    return Pattern.compile("^([1-9])([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "M/DD":
                    return Pattern.compile("^([1-9])[/]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "M-DD":
                    return Pattern.compile("^([1-9])[-]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "M.DD":
                    return Pattern.compile("^([1-9])[.]([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();

                case "DDM":
                    return Pattern.compile("^([1-9])([123]0|[012][1-9]|31)$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD/M":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[/]([1-9])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD-M":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[-]([1-9])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
                case "DD.M":
                    return Pattern.compile("^([123]0|[012][1-9]|31)[.]([1-9])$", Pattern.CASE_INSENSITIVE).matcher(value).matches();
            }
        }
        return false;
    }

    public ExtractDate extracDateStr(String value) {
        ExtractDate res = new ExtractDate();
        res.setDate(false);

        if (value == null || value == "" ) {
            return res;
        };

        for(int i = 0 ; i < PatternDate.spatternDate.length; i++){
            if(isDateWithFormat(value,PatternDate.spatternDate[i])){
                int iYear = PatternDate.spatternDate[i].indexOf("YYYY"),
                    iMonth = PatternDate.spatternDate[i].indexOf("MM"),
                    iM = PatternDate.spatternDate[i].indexOf("M"),
                    iday = PatternDate.spatternDate[i].indexOf("DD"),
                    ihour = PatternDate.spatternDate[i].indexOf("HH"),
                    iminute = PatternDate.spatternDate[i].indexOf("MI"),
                    isecond = PatternDate.spatternDate[i].indexOf("SS");
                res.setYear((iYear > -1) ? value.substring(iYear,iYear+4) : "");
                res.setMonth((iMonth > -1) ? value.substring(iMonth,iMonth+2) : "");
                res.setDay((iday > -1) ? value.substring(iday,iday+2) : "");
                res.setHour((ihour > -1) ? value.substring(ihour,ihour+2) : "");
                res.setMinute((iminute > -1)?value.substring(iminute,iminute+2):"");
                res.setSecond((isecond > -1) ? value.substring(isecond,isecond+2):"");
                res.setMonth((iMonth == -1 && iM > -1)?('0'+value.substring(iM,iM+1)):"");

                return res;
            }
        }
        return res;
    }
    public static  Date parseDate(Long value) {
        try {
            Date date = new Date(value);
            return date;
        }catch (Exception e) {
            return null;
        }

    }

    public String dateStrFormat(String date, String pattern) {
        if(pattern == null) pattern = "mm/dd";
        if (date == null || date == "") {
            return "";
        };
        pattern = pattern.toLowerCase();
        ExtractDate extractDate = this.extracDateStr(date);
        if(extractDate != null){
            String dateFm = pattern;
            dateFm = dateFm.replace("yyyy",extractDate.getYear());
            dateFm = dateFm.replace("mm",extractDate.getMonth());
            dateFm = dateFm.replace("dd",extractDate.getDay());
            dateFm = dateFm.replace("hh",extractDate.getHour());
            dateFm = dateFm.replace("mi",extractDate.getMinute());
            dateFm = dateFm.replace("ss",extractDate.getSecond());
            return dateFm;
        }else{
            return "";
        }
    }

    public Date roundDate(Date date , boolean end) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        date.setTime(0);
        if (end) {
            date.setDate(date.getDate() + 1);
            date.setTime(date.getTime() - 1000);
        }
        return date;
    }

    public double getDayBetween2Date(Date date1, Date date2) {
        double diffInTime = date2.getTime() - date1.getTime();
        double diffInDays = diffInTime / (1000 * 3600 * 24);
        return diffInDays;
    }
}
