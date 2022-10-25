package necesjv.common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class NescesCmF {
    private static volatile SecureRandom numberGenerator = null;
    private static final long MSB = 0x8000000000000000L;

    public String getMd5(String pass)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String  getCookie(String name, HttpServletRequest servletRequest) {
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public String getSha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void eraseCookie(HttpServletRequest servletRequest,HttpServletResponse servletResponse) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                servletResponse.addCookie(cookie);
            }
    }

    public void setCookie(String name, String value, boolean secure,HttpServletResponse servletResponse){
        Cookie cookieEt = new Cookie(name, value);
        cookieEt.setPath("/");
        cookieEt.setMaxAge(60*60*24*365);
        if (secure) cookieEt.setSecure(true);
        servletResponse.addCookie(cookieEt);
    }

    public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public String unique() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }

    public static boolean checkByteAndLen(String value, int byteCheck, int lengthCheck) throws Exception {
        if(value != null ) {
            if(value.length() > lengthCheck) return true;
            value = value.trim();
            for(int l = 0; l< value.length();l++) {
                if(value.length() > 0 && value.substring(0,1).equals("　")) {
                    value=value.substring(1,value.length());
                }else {
                    break;
                }
            }
            for(int l = 0; l< value.length();l++) {
                if(value.length() > 0 && value.substring(value.length()-1,value.length()).equals("　")) {
                    value=value.substring(0,value.length()-1);
                }else {
                    break;
                }
            }
            byte[] utf8 = value.getBytes("UTF-8");
            if (utf8.length < byteCheck) byteCheck = utf8.length;
            int i = 0;
            while (i < utf8.length) {
                if ((utf8[i] & 0x80) == 0) i += 1;
                else if ((utf8[i] & 0xE0) == 0xC0) i += 2;
                else if ((utf8[i] & 0xF0) == 0xE0) i += 3;
                else { i += 4;}
            }
            return (i > byteCheck );
        }else {
            return true;
        }

    }

    public static boolean checkByte(String value, int b) throws Exception {
        if(value != null ) {
            value = value.trim();
            for(int l = 0; l< value.length();l++) {
                if(value.length() > 0 && value.substring(0,1).equals("　")) {
                    value=value.substring(1,value.length());
                }else {
                    break;
                }
            }
            for(int l = 0; l< value.length();l++) {
                if(value.length() > 0 && value.substring(value.length()-1,value.length()).equals("　")) {
                    value=value.substring(0,value.length()-1);
                }else {
                    break;
                }
            }
            byte[] utf8 = value.getBytes("UTF-8");
            if (utf8.length < b) b = utf8.length;
            int i = 0;
            while (i < utf8.length) {
                if ((utf8[i] & 0x80) == 0) i += 1;
                else if ((utf8[i] & 0xE0) == 0xC0) i += 2;
                else if ((utf8[i] & 0xF0) == 0xE0) i += 3;
                else { i += 4;}
            }
            return (i > b );
        }else {
            return true;
        }

    }

    private Double round(Double d, int precise) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(precise, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public ApiCall callAPIOutside(String API_URL, String method, String requestBody, String cookie) throws Exception {
        ApiCall result = new ApiCall();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cookie", cookie);
            if(method != "GET") {
                OutputStream os = conn.getOutputStream();
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                result.setError(true);
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream()), "UTF-8"));
                String output;
                while ((output = br.readLine()) != null) {
                    result.setData(result.getData() + output);
                }
            }

            conn.disconnect();
        } catch(Exception ex) {
            result.setError(true);
        }

        return result;
    }


}
