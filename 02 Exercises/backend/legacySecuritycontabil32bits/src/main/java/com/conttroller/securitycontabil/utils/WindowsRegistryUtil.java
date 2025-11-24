package com.conttroller.securitycontabil.utils;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class WindowsRegistryUtil {

    public static void writeString(String key, String name, String value) {
        Advapi32Util.registryCreateKey(WinReg.HKEY_LOCAL_MACHINE, key);
        Advapi32Util.registrySetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, name, value);
    }

    public static String readString(String key, String name) {
        if (Advapi32Util.registryKeyExists(WinReg.HKEY_LOCAL_MACHINE, key)) {
            return Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, name);
        }
        return null;
    }
}