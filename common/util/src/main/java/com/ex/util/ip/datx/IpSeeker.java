package com.ex.util.ip.datx;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class IpSeeker {
    private byte[] data;

    private long indexSize;

    public IpSeeker() throws IOException {
        String name = "17monipdb.datx";
        InputStream is = null;
        is = getClass().getResourceAsStream("/" + name);
        if (is != null) {
            try {
                data = toByteArray(is);
                indexSize = Util.bytesToLong(data[0], data[1], data[2], data[3]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public String[] find(String ips) throws IPv4FormatException {
        if (!Util.isIPv4Address(ips)) {
            throw new IPv4FormatException();
        }

        long val = Util.ip2long(ips);
        int start = 262148;
        int low = 0;
        int mid = 0;
        int high = new Long((indexSize - 262144 - 262148) / 9).intValue() - 1;
        int pos = 0;
//        System.out.println(new String(data, Charset.forName("UTF-8")));
        while (low <= high) {
            mid = new Double((low + high) / 2).intValue();
            pos = mid * 9;

            long s = 0;
            if (mid > 0) {
                int pos1 = (mid - 1) * 9;
                s = Util.bytesToLong(data[start + pos1], data[start + pos1 + 1], data[start + pos1 + 2], data[start + pos1 + 3]);
            }

            long end = Util.bytesToLong(data[start + pos], data[start + pos + 1], data[start + pos + 2], data[start + pos + 3]);
            if (val > end) {
                low = mid + 1;
            } else if (val < s) {
                high = mid - 1;
            } else {

                byte b = 0;
                long off = Util.bytesToLong(b, data[start + pos + 6], data[start + pos + 5], data[start + pos + 4]);
                long len = Util.bytesToLong(b, b, data[start + pos + 7], data[start + pos + 8]);

                int offset = new Long(off - 262144 + indexSize).intValue();

                byte[] loc = Arrays.copyOfRange(data, offset, offset + new Long(len).intValue());

                return new String(loc, Charset.forName("UTF-8")).split("\t", -1);
            }
        }

        return null;
    }
}