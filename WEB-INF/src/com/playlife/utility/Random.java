package com.playlife.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Random {
    public String getAccessToken(String ipAddr) throws NoSuchAlgorithmException {
        String strRetVal = "";
        String strTemp = "";

        // Get IPAddress Segment
        byte[] ipaddr = ipAddr.getBytes();
        for (int i = 0; i < ipaddr.length; i++) {
            Byte b = new Byte(ipaddr[i]);

            strTemp = Integer.toHexString(b.intValue() & 0x000000ff);
            while (strTemp.length() < 2) {
                strTemp = '0' + strTemp;
            }
            strRetVal += strTemp;
        }

        strRetVal += ':';

        //Get CurrentTimeMillis() segment
        strTemp = Long.toHexString(System.currentTimeMillis());
        while (strTemp.length() < 12) {
            strTemp = '0' + strTemp;
        }
        strRetVal += strTemp + ':';

        //Get Random Segment
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

        strTemp = Integer.toHexString(prng.nextInt());
        while (strTemp.length() < 8) {
            strTemp = '0' + strTemp;
        }

        strRetVal += strTemp.substring(4) + ':';

        //Get IdentityHash() segment
        strTemp = Long.toHexString(System.identityHashCode((Object) this));
        while (strTemp.length() < 8) {
            strTemp = '0' + strTemp;
        }
        strRetVal += strTemp;
        
        return strRetVal.toUpperCase();
    }

    public static int randomInt(){
    	SecureRandom random = new SecureRandom();
    	return random.nextInt();
    }
}
