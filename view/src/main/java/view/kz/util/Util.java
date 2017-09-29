package view.kz.util;

import view.kz.persistence.common.IDictionary;
import view.kz.util.validation.ParamValidateException;
import view.kz.web.AppController.ApplicationManager;
import view.kz.web.AppController.LocaleModule;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
    public static XMLGregorianCalendar dateTimeToGregorianCalendar(Date date) {
        if (date == null)
            return null;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date gregorianCalendarToDateTime(XMLGregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null)
            return null;
        return gregorianCalendar.toGregorianCalendar().getTime();
    }

    public static Date gregorianCalendarStrToDateTime(String gregorianCalendarStr) {
        if (gregorianCalendarStr == null)
            return null;
        try {
            return gregorianCalendarToDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendarStr));
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    public static String calculateMD5(byte[] byteArray) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(byteArray);
        String hash = new BigInteger(1, md.digest()).toString(16);
        while (hash.length() < 32) hash = "0" + hash;
        return hash;
    }

    public static String notEmptyString(String str) {
        return str == null || str.isEmpty() ? null : str;
    }

    public static byte[] downloadAttachment(String urlString) throws Exception {
        if (urlString == null || urlString.isEmpty()) {
            return null;
        }
        System.out.println("Download file " + urlString);
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
        url = uri.toURL();
        InputStream in = url.openStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(baos);
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
        return baos.toByteArray();
    }

    public static byte[] downloadAttachmentProxy(String urlString) throws Exception {
        if (urlString == null || urlString.isEmpty()) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(baos);
        downloadAttachmentProxy(urlString, out);
        return baos.toByteArray();
    }

    public static void downloadAttachmentProxy(String urlString, OutputStream out) throws Exception {
        System.out.println("Download file " + urlString);
        URL url = new URL(urlString);
        if(!ApplicationManager.getAccessHostList().contains(url.getHost())) {
            System.err.println(VulnerabilitiesDefender.restrictLogForging("Host " + url.getHost() + " is locked"));
            throw new ParamValidateException();
        }
        URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
        url = uri.toURL();
        URLConnection conn;
        if (ApplicationManager.PROXY_IP().isEmpty()) {
            conn = url.openConnection();
        } else {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ApplicationManager.PROXY_IP(), ApplicationManager.PROXY_PORT()));
            conn = url.openConnection(proxy);
        }
        conn.connect();
        InputStream in = conn.getInputStream();
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
    }

    public static String xorKeys(String key1, String key2) {
        char[] keyChars1 = key1.toCharArray();
        char[] keyChars2 = key2.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyChars1.length; i++) {
            String par1 = "" + keyChars1[i];
            String par2 = "" + keyChars2[i];
            int i1 = Integer.parseInt(par1, 16);
            int i2 = Integer.parseInt(par2, 16);
            int xorRes = (i1 ^ i2) & 0xff;
            String hex = Integer.toHexString(xorRes).toUpperCase();
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String str) {
        char[] keyChars1 = str.toCharArray();
        byte[] bytes = new byte[keyChars1.length / 2];
        for (int i = 0; i < keyChars1.length / 2; i++) {
            String par1 = "" + keyChars1[2 * i];
            String par2 = "" + keyChars1[2 * i + 1];
            bytes[i] = (byte) (Integer.parseInt(par1 + par2, 16) & 0xff);
        }
        return bytes;
    }

    public static <T> T evaluateELExpression(String expression, Class<T> clzz) {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        return (T) getValueExpression(expression, clzz).getValue(elContext);
    }

    public static ValueExpression getValueExpression(String expression, Class clzz) {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        ExpressionFactory expressionFactory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        return expressionFactory.createValueExpression(elContext, expression, clzz);
    }

    public static void main(String[] args) {
        String key1 = "E0872976F4F98BAF8EBF1777705BAE9A";
        String key2 = "35EE927EC7AEE327DFC7DB51CDF3F141";
        System.out.println("key1 = " + key1);
        System.out.println("key2 = " + key2);
        String xorResult = xorKeys(key1, key2);
        System.out.println("rest = " + xorResult);
        byte[] bytes = hexStringToBytes(xorResult);
        System.out.println("bytes.length = " + bytes.length);
        String byteStr = bytesToStr(bytes);
        System.out.println("sb = " + byteStr);

        String ss = String.format("%1.2f", 123421345.4d).replace(",", ".");
        System.out.println("ss = " + ss);
    }

    public static String bytesToStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte).toUpperCase();
            sb.append(hex);
        }
        return sb.toString();
    }

    public static double round(double unrounded, int precision, int roundingMode) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(precision, roundingMode);
        return rounded.doubleValue();
    }

    private static void appendPart(StringBuilder paramStringBuilder, String paramString1, String paramString2) {
        if ((paramStringBuilder == null) || (paramString1 == null) || (paramString1.isEmpty())) {
            return;
        }
        if (paramStringBuilder.length() != 0) {
            paramStringBuilder.append(paramString2);
        }
        paramStringBuilder.append(paramString1.trim());
    }

    /**
     * Метод объединяет параметры в одну строку с разделителем
     *
     * @param separator разделитель
     * @param params    строки для объединения
     * @return строка с разделителями
     */
    public static String concatenateWithSeparator(String separator, String... params) {
        StringBuilder localStringBuilder = new StringBuilder();
        for (String param : params) {
            appendPart(localStringBuilder, param, separator);
        }
        return localStringBuilder.toString();
    }

    public static String getAsString(IDictionary dic) {
        if (dic == null) {
            return "";
        }
        if (LocaleModule.IS_RU()) {
            return dic.getValueRu();
        } else {
            return dic.getValueKz();
        }
    }

    /**
     * Данный метод считает, что null равен null
     *
     * @return признак равенства объектов с проверкой на null
     */
    public static boolean nvlEquals(Object o1, Object o2) {
        return o1 == null && o2 == null
                || !(o1 == null || o2 == null) && o1.equals(o2);
    }
}
